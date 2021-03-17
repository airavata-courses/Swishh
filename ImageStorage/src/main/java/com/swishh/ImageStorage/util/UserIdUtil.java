package com.swishh.ImageStorage.util;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.swishh.ImageStorage.models.UserDAO;
import com.swishh.ImageStorage.service.impl.UserServiceImpl;

@Component
public class UserIdUtil {
	
	@Autowired
	UserServiceImpl userService;
	
	public String getUserIdfromUserName(String username) {
		String userId=null;
		UserDAO user=userService.getUserRecord(username);
		if(user==null||user.getUserid()==null) {
			userId=UUID.randomUUID().toString();
			user=new UserDAO();
			user.setUserid(userId);
			user.setUsername(username);
			userService.saveUserRecord(user);
		}else {
			userId=user.getUserid();
		}
		return userId;
		
	}

}
