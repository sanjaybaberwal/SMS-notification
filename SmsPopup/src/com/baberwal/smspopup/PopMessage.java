package com.baberwal.smspopup;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@SuppressWarnings("serial")
public class PopMessage implements Serializable {

	private String sender;
    private String body;
    private long timestamp;
    private String phone;

    // getters and setters go here
    // ...

   public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

/**
     * Utility method
     * Display a shorten, more user-friendly readable date from the original timestamp
     */
   public String getShortDate(){
        Date date = new Date(timestamp);
        Calendar cal = new GregorianCalendar();
		SimpleDateFormat sdf = new SimpleDateFormat("E MMM dd hh:mmaa");
		sdf.setCalendar(cal);
		cal.setTime(date);
		return sdf.format(date);
	   }
}
