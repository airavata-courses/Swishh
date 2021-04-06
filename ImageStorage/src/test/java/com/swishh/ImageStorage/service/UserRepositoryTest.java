package com.swishh.ImageStorage.service;

import static org.mockito.Mockito.doNothing;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.swishh.ImageStorage.repository.IUserRepository;
import com.swishh.ImageStorage.service.impl.UserServiceImpl;

@SpringBootTest
public class UserRepositoryTest {
	
	
	@MockBean
	IUserRepository userrepo;
	@Autowired
	UserServiceImpl userservice;
	
	@Test
	public void saveUserTest() {
		//doNothing().when(userrepo.save(null));
		userservice.saveUserRecord(null);
		
	}

}
