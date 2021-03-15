package com.swishh.ImageStorage.models.request;

public class FilesResponse {

	String filename;
	
	byte[] file;

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}
	
}
