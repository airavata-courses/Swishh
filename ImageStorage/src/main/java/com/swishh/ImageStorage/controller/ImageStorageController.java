package com.swishh.ImageStorage.controller;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.swishh.ImageStorage.exception.StorageException;
import com.swishh.ImageStorage.exception.StorageFileNotFoundException;
import com.swishh.ImageStorage.models.request.FilesResponse;
import com.swishh.ImageStorage.service.impl.FileSystemStorageService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class ImageStorageController {

	@Autowired
	FileSystemStorageService storageService;
	
	@GetMapping("/files")
	public ArrayList<FilesResponse> listUploadedFiles(Model model,String username) throws IOException {
		return 	storageService.loadAll(username);
	}

	@PostMapping("/upload")
	public ResponseEntity handleFileUpload(@RequestParam("file") MultipartFile[] file,
			@RequestParam("username") String username,
			@RequestParam("foldername") String foldername){

		try{
			storageService.store(file,username,foldername);
		}
		catch(StorageException E) {
			ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@ExceptionHandler(StorageFileNotFoundException.class)
	public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
		return ResponseEntity.notFound().build();
	}
	
}
