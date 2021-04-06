package com.swishh.ImageStorage.models;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class FilesDao {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	String userId;
	
	@ElementCollection
	List<String> sharedwith;
	
	
	String fileid;
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFileids() {
		return fileid;
	}

	public void setFileids(String fileid) {
		this.fileid = fileid;
	}

	public List<String> getSharedwith() {
		return sharedwith;
	}

	public void setSharedwith(List<String> sharedwith) {
		this.sharedwith = sharedwith;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFileid() {
		return fileid;
	}

	public void setFileid(String fileid) {
		this.fileid = fileid;
	}

	

}
