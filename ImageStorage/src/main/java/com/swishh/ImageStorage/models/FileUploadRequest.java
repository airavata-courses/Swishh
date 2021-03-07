package com.swishh.ImageStorage.models;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadRequest implements Serializable{
	MultipartFile[] files;
	String username;
	String userid;
	public MultipartFile[] getFiles() {
		return files;
	}
	public void setFiles(MultipartFile[] files) {
		this.files = files;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}

}
