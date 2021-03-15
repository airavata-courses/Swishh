package com.swishh.ImageStorage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swishh.ImageStorage.models.FolderDao;
import com.swishh.ImageStorage.repository.IFolderRepository;

@Service
public class FolderService {
	
	@Autowired
	IFolderRepository folderRepository;
	
	public FolderDao getFolderDetails(String foldername) {
		return folderRepository.findById(foldername).orElse(null);
	}
	
	public List<FolderDao> getFoldersByOwner(String ownerName) {
		return folderRepository.getFoldersByOwnerName(ownerName);
	}
	
	public List<FolderDao> getFoldersBySharedOwner(String ownerName) {
		return folderRepository.getFoldersBySharedOwner(ownerName);
	}

}
