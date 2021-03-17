package com.swishh.ImageStorage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.swishh.ImageStorage.models.ShareWithRequest;
import com.swishh.ImageStorage.service.impl.ShareService;

@RestController
public class ShareController {
	
	@Autowired
	ShareService shareService;
	
	
	@PostMapping("/share")
	public ResponseEntity shareWithUsers(@RequestBody ShareWithRequest request) {
		boolean status=shareService.shareWithUsers(request.getUsername(), request.getFileids(),request.getSharewith());
		if(status)
			return ResponseEntity.ok().build();
		else
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}

}
