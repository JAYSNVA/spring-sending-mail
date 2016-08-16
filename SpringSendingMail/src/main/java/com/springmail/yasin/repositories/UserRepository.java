package com.springmail.yasin.repositories;
	

import org.springframework.data.jpa.repository.JpaRepository;

import com.springmail.yasin.entities.User;


public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail (String email);
	
}
