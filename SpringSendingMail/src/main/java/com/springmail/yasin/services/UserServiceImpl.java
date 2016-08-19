package com.springmail.yasin.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.springmail.yasin.dto.SignupForm;
import com.springmail.yasin.dto.UserDetailsImpl;
import com.springmail.yasin.entities.User;
import com.springmail.yasin.repositories.UserRepository;

@Service
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class UserServiceImpl implements UserService, UserDetailsService {

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


	@Override
	public UserDetails loadUserByUsername(String username) 
			throws UsernameNotFoundException {
		User user = userRepository.findByEmail(username); //findByEmail üzerinde tekrar bir değişilik yapılacak ve o hata giderilecek.
		if (user == null)
			throw new UsernameNotFoundException(username);
		
		return new UserDetailsImpl(user);
	}

}
