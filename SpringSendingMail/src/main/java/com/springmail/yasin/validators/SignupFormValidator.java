package com.springmail.yasin.validators;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.springmail.yasin.dto.SignupForm;
import com.springmail.yasin.entities.User;
import com.springmail.yasin.repositories.UserRepository;

@Component
public class SignupFormValidator extends LocalValidatorFactoryBean {

	private UserRepository userRepository;
	
	@Resource
	public void setUserRepository (UserRepository userRepository){
		this.userRepository = userRepository;
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
			return clazz.isAssignableFrom(SignupForm.class) ;
	}
	
	@Override
	public void validate(Object obj, Errors errors, final Object... validationHints) { //vlidationHints objesini final olarak yiyor.
		
		super.validate(obj, errors, validationHints);
		
		if(!errors.hasErrors()){
			SignupForm signupForm = (SignupForm) obj ;
			User user = userRepository.findByEmail(signupForm.getEmail());
			if(user != null)
					errors.rejectValue("email","emailNotUnique");          //Burada hala hata olabilir
		}
	}
}
