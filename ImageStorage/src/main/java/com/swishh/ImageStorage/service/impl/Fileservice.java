package com.swishh.ImageStorage.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swishh.ImageStorage.models.FilesDao;
import com.swishh.ImageStorage.repository.IFilesRepository;
import com.swishh.ImageStorage.service.IFileService;

@Service
public class Fileservice implements IFileService{
	@Autowired
	IFilesRepository filesRepository;
	
	
	@Override
	public FilesDao getUserFiles(String userId) {
		return filesRepository.findById(userId).orElse(null);
	}

	@Override
	public void updateUserFiles(FilesDao filesDao) {
		filesRepository.save(filesDao);
		
	}

}
