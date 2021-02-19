package com.swishh.ImageStorage.service;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.stream.Stream;


import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public interface StorageService {
	void init();

	void store(MultipartFile[] file);

	ArrayList<byte[]> loadAll() throws Exception;

	Path load(String filename);

	Resource loadAsResource(String filename);

	void deleteAll();

}
