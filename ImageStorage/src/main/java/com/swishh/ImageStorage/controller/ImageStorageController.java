package com.swishh.ImageStorage.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.swishh.ImageStorage.exception.StorageFileNotFoundException;
import com.swishh.ImageStorage.service.StorageService;
import com.swishh.ImageStorage.service.impl.FileSystemStorageService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class ImageStorageController {

	@Autowired
	FileSystemStorageService storageService;
	
	@GetMapping("/files")
	public ArrayList<byte[]> listUploadedFiles(Model model) throws IOException {

		

		return 	storageService.loadAll();
	}

	@PostMapping("/upload")
	public String handleFileUpload(@RequestParam("file") MultipartFile[] file,@RequestParam("username") String username,
			RedirectAttributes redirectAttributes) {

		System.out.println(file.length);
		String str="";
		for(MultipartFile files:file) {
			str+=files.getOriginalFilename();
		}
		System.out.println("files tobe stored"+str);
		storageService.store(file,username);
		redirectAttributes.addFlashAttribute("message",
				"You successfully uploaded " + str+  "!");

		return "redirect:/";
	}

	@ExceptionHandler(StorageFileNotFoundException.class)
	public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
		return ResponseEntity.notFound().build();
	}
	
}
