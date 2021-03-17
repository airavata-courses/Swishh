package com.swishh.ImageStorage.service;

import java.nio.file.Path;
import java.util.ArrayList;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.swishh.ImageStorage.models.request.FilesResponse;

@Component
public interface StorageService {
	void init();

	void store(MultipartFile[] file,String username,String folderName);

	ArrayList<FilesResponse> loadAll(String username) throws Exception;

	Path load(String filename);

	Resource loadAsResource(String filename);

	void deleteAll();

}
