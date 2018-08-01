package com.innovativeintelli.authandautherization.payload;

public class GlobalResponse {
	
	String message;
	
	Object data;

	public GlobalResponse(Object data) {
		this.data =data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
}
