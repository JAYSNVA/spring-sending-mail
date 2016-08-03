package com.springmail.yasin.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springmail.yasin.dto.SignupForm;
import com.springmail.yasin.entities.User;
import com.springmail.yasin.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		
		this.userRepository = userRepository;
	}
	
	
	@Override
	public void signup(SignupForm signupForm) {
		
		User user = new User();
		user.setName(signupForm.getName());
		user.setEmail(signupForm.getEmail());
		user.setPassword(signupForm.getPassword());
		userRepository.save(user);
	}

}
