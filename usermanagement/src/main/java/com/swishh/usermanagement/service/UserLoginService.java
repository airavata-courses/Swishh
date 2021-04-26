package com.swishh.usermanagement.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.swishh.usermanagement.entity.UserEntity;
import com.swishh.usermanagement.entity.UserLoginDTO;
import com.swishh.usermanagement.repository.UserRepository;

@Service
public class UserLoginService {

	@Autowired
	private UserRepository userRepository;

	public ResponseEntity registerUser(UserEntity user) {
		System.out.println("Registering user ....");
		Optional<UserEntity> u = userRepository.findById(user.getUsername());
		if (u.isPresent()) {
			System.out.println("User already exists");
			return new ResponseEntity(HttpStatus.OK).status(HttpStatus.OK)
					.body("User with username already exists");
		}
		try {
			userRepository.save(user);
			System.out.println("User has been registered");
			return new ResponseEntity(HttpStatus.CREATED).status(HttpStatus.CREATED)
					.body("User created with given details");

		} catch (Exception e) {
			System.out.println("encountered " + e.getMessage() + " while creating user");
			return new ResponseEntity(HttpStatus.OK).status(HttpStatus.OK)
					.body("An error encountered while registering the user");
		}

	}

	public ResponseEntity loginUser(UserLoginDTO user) {
		System.out.println("user Login....");
		if(user==null) {
			System.out.println("user is null");
		}
		else {
			System.out.println("username "+user.getUsername());
		}
		Optional<UserEntity> u = userRepository.findById(user.getUsername());
		if (!u.isPresent()) {
			System.out.println("User not registered");
			return new ResponseEntity(HttpStatus.OK).status(HttpStatus.OK)
					.body("User not registered. Please register");
		}
		try {
			System.out.println(u.get().getPassword());
			if (u.get().getPassword().equals(user.getPassword())) {
				System.out.println("User has been registered");
				return new ResponseEntity(HttpStatus.OK).status(HttpStatus.OK).body("User verified");
			}
			else {
				System.out.println("wrong credentaisl");
				return new ResponseEntity(HttpStatus.OK).status(HttpStatus.OK)
						.body("User credentials are invalid. PLease enter valid credentials");
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("encountered " + e.getMessage() + " while user login");
			return new ResponseEntity(HttpStatus.OK).status(HttpStatus.OK)
					.body("An error occurred while loggin in user");
		}

	}
}
