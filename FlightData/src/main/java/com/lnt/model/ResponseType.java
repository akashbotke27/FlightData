package com.lnt.model;

import java.util.Date;

public class ResponseType {

	private String status;
	private String code;
	private String message;
	private Date timeStamp;
	private Flight[] flights;
	
	
	
	
	public Flight[] getFlights() {
		return flights;
	}
	public void setFlights(Flight[] flights) {
		this.flights = flights;
	}
	public ResponseType() {
		super();
		
	}
	public ResponseType(String status, String code, String message) {
		super();
		this.status = status;
		this.code = code;
		this.message = message;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	
}
