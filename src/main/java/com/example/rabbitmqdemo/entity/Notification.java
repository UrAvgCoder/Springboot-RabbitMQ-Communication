package com.example.rabbitmqdemo.entity;

public class Notification {
	public Notification() {
	}

	public Notification(String message, String status) {
		super();
		this.message = message;
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	private String message;
	private String status;

	@Override
	public String toString() {
		return "Notification [message=" + message + ", status=" + status + "]";
	}

}
