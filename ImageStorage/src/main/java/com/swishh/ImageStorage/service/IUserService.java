package com.swishh.ImageStorage.service;

import org.springframework.stereotype.Component;

import com.swishh.ImageStorage.models.UserDAO;

@Component
public interface IUserService {
	
	public void saveUserRecord(UserDAO user);
	public UserDAO getUserRecord(String userid);

}
