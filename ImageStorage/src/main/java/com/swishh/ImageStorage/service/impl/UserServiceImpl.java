package com.swishh.ImageStorage.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swishh.ImageStorage.models.UserDAO;
import com.swishh.ImageStorage.repository.IUserRepository;
import com.swishh.ImageStorage.service.IUserService;

@Service
public class UserServiceImpl implements IUserService{

	@Autowired
	IUserRepository userRepository;
	
	@Override
	public void saveUserRecord(UserDAO user) {
		userRepository.save(user);
		
	}

	

	@Override
	public UserDAO getUserRecord(String username) {
		if(username!=null)
		{
			return userRepository.findByUsername(username);
		}
		return null;
	}

}
