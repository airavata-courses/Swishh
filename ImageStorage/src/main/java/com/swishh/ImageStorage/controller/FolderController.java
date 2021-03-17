package com.swishh.ImageStorage.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FolderController {
	
	@PostMapping("/folder")
	public void createFolder(@RequestParam("username") String username,
			@RequestParam("foldername") String folderName,@RequestParam("parentfolder") String parentFolder) {
		
	}
	
	@GetMapping("/folders/{username}")
	public void landingPageFolder(String username) {
		
	}

}
