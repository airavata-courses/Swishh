package com.swishh.ImageStorage.service;

import org.springframework.stereotype.Component;

import com.swishh.ImageStorage.models.FilesDao;

@Component
public interface IFileService {
	
	public void updateUserFiles(FilesDao filesDao);
	public FilesDao getUserFiles(String userId);

}
