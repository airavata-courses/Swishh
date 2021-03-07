package com.swishh.ImageStorage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.swishh.ImageStorage.models.UserDAO;

public interface IUserRepository extends JpaRepository<UserDAO, String> {

	@Query(value="select * from userdao where username=? limit 1",nativeQuery = true)
	public UserDAO findByUsername(String username);
	
}
