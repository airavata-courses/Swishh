package com.swishh.usermanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.swishh.usermanagement.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String>{

}
