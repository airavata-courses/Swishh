package com.swishh.ImageStorage.models;

import org.springframework.web.multipart.MultipartFile;

public class ImagestoreRequest {

	MultipartFile[]  file;
	String username;
	String foldername;
	public MultipartFile[] getFile() {
		return file;
	}
	public void setFile(MultipartFile[] file) {
		this.file = file;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFoldername() {
		return foldername;
	}
	public void setFoldername(String foldername) {
		this.foldername = foldername;
	}
}
