package com.springmail.yasin.services;

import com.springmail.yasin.dto.SignupForm;

public interface UserService {

	public abstract void signup(SignupForm signupForm);

	public abstract void verify(String verificationCode);
}
