package com.swishh.ImageStorage.models;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class FolderDao {
	
	String folderId;
	@Id
	String folderName;
	String ownerName;
	@ElementCollection
	List<String> sharedwith;
	public String getFolderId() {
		return folderId;
	}
	public void setFolderId(String folderId) {
		this.folderId = folderId;
	}
	public String getFolderName() {
		return folderName;
	}
	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public List<String> getSharedwith() {
		return sharedwith;
	}
	public void setSharedwith(List<String> sharedwith) {
		this.sharedwith = sharedwith;
	}

}
