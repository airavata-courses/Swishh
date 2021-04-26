package com.swishh.usermanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.swishh.usermanagement.entity.UserEntity;
import com.swishh.usermanagement.entity.UserLoginDTO;
import com.swishh.usermanagement.service.UserLoginService;

@RestController
public class LoginController {
	
	@Autowired
	UserLoginService loginService;
	
	@PostMapping("/register")
	public ResponseEntity userRegister(@RequestBody UserEntity user) {
		return loginService.registerUser(user);
	}
	
	@PostMapping("/login")
	public ResponseEntity userLogin(@RequestBody UserLoginDTO user) {
		return loginService.loginUser(user);		
	}

}
