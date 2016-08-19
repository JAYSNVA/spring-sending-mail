package com.springmail.yasin.repositories;
	

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springmail.yasin.entities.User;


public interface UserRepository extends JpaRepository<User, Long> {

	//SQL Query @Query(value = "SELECT * FROM USERS WHERE EMAIL_ADDRESS = ?1", nativeQuery = true)
	@Query("select u from User u where u.email = ?1")
	User findByEmail (String email); //hata buradan kaynaklanıyor.
	
}
