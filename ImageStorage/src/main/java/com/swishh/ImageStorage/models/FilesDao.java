package com.swishh.ImageStorage.models;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class FilesDao {
	
	@Id
	String userId;
	
	@ElementCollection
	List<String> fileids;
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<String> getFileids() {
		return fileids;
	}

	public void setFileids(List<String> fileids) {
		this.fileids = fileids;
	}

	

}
