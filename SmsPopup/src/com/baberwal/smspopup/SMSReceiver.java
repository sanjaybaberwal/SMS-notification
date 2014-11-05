package com.baberwal.smspopup;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.gsm.SmsMessage;

@SuppressWarnings("deprecation")
public class SMSReceiver extends BroadcastReceiver {

	@SuppressWarnings("deprecation")
	@Override
	public void onReceive(Context context, Intent intent) {
		Bundle bundle = intent.getExtras(); 

		// get the SMS received
		Object[] pdus = (Object[]) bundle.get("pdus");
		SmsMessage[] msgs = new SmsMessage[pdus.length];
		
		/** sms sender phone*/
		String smsSender ="";

		/** body of received sms*/
		String smsBody = "";

		/** timerstamp */
		long timestamp = 0L;

		for (int i=0; i<msgs.length; i++){
		      msgs[i] = SmsMessage.createFromPdu((byte[])pdus[i]);
		      smsSender += msgs[i].getOriginatingAddress();
		      smsBody += msgs[i].getMessageBody().toString();
		      timestamp += msgs[i].getTimestampMillis();
		}
		
		PopMessage pop_msg = new PopMessage();
	    
		pop_msg.setBody(smsBody);
		pop_msg.setSender(smsSender);
		pop_msg.setTimestamp(timestamp);

	    // start a new task before dying
	    intent.setClass(context, SmsPopUpActivity.class);
	    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

	    // pass Serializable object
	    intent.putExtra("msg", pop_msg);

	    // start UI
	    context.startActivity(intent);

	    // keep this broadcast to ourselves
	    abortBroadcast();

	}

}
