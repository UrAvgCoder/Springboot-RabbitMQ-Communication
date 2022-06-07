package com.example.rabbitmqdemo.constants;

public enum FormatData {
	CSV("CSV"),
	XML("XML");

	private String fileType;
	FormatData(String fileType) {
		this.fileType = fileType;
	}
	
	public String getFileType() { return this.fileType; }	
	
}
