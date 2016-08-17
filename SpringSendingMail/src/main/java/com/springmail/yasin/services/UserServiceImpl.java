package com.springmail.yasin.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.springmail.yasin.dto.SignupForm;
import com.springmail.yasin.entities.User;
import com.springmail.yasin.repositories.UserRepository;

@Service
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	
	
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		
		this.userRepository = userRepository;
	}
	
	
	@Override
	@Transactional(propagation=Propagation.SUPPORTS, readOnly=false)
	public void signup(SignupForm signupForm) {
		
		User user = new User();
		user.setName(signupForm.getName());
		user.setEmail(signupForm.getEmail());
		user.setPassword(signupForm.getPassword());
		userRepository.save(user);
	}

}
