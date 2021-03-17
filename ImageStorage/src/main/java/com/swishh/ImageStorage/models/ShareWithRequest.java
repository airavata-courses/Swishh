package com.swishh.ImageStorage.models;

import java.io.Serializable;
import java.util.List;

public class ShareWithRequest implements Serializable{

	String username;
	List<String> fileids;
	List<String> sharewith;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public List<String> getSharewith() {
		return sharewith;
	}
	public void setSharewith(List<String> sharewith) {
		this.sharewith = sharewith;
	}
	public List<String> getFileids() {
		return fileids;
	}
	public void setFileids(List<String> fileids) {
		this.fileids = fileids;
	}
}
