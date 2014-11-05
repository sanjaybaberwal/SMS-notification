package com.baberwal.smspopup;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

public class SmsPopUpActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
   public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
        
        // no need for XML layouts right now
        // we will use a dialog instead
        //setContentView(R.layout.main); 

        // retrieve Serializable sms message object
        // by the key "msg" used to pass it
        Intent in = this.getIntent();
        PopMessage msg = (PopMessage)in.getSerializableExtra("msg");

        // Case where we launch the app to test the UI
        // i.e. no incoming SMS
        if(msg == null){
         msg = new PopMessage();
         msg.setSender("0123456789");
		 msg.setTimestamp( System.currentTimeMillis() );
		 msg.setBody(" this is a test SMS message!");
        }
        showDialog(msg);
    }
    
    private void showDialog(PopMessage msg){

    	final String sender = msg.getSender();
    	final String body = msg.getBody();

    	final String display = sender+"\n"+ msg.getShortDate()+ "\n"
                + body + "\n";

        // Display in Alert Dialog
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
    	builder.setMessage(display)
    	.setCancelable(false)
    	.setPositiveButton("Reply", new DialogInterface.OnClickListener() {
    		public void onClick(DialogInterface dialog, int id) {
                      // reply by calling SMS program
    		      //smsReply(sender, body);
    		}
    	})
    	.setNegativeButton("Close", new DialogInterface.OnClickListener() {
    		public void onClick(DialogInterface dialog, int id) {
                      // go back to the phone home screen
    	             // goHome();
    		}
    	});
    	AlertDialog alert = builder.create();
    	alert.show();
    }
}
