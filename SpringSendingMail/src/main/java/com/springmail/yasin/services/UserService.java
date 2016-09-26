package com.springmail.yasin.services;

import org.springframework.validation.BindingResult;

import com.springmail.yasin.dto.ForgotPasswordForm;
import com.springmail.yasin.dto.ResetPasswordForm;
import com.springmail.yasin.dto.SignupForm;

public interface UserService {

	public abstract void signup(SignupForm signupForm);

	public abstract void verify(String verificationCode);

	public abstract void forgotPassword(ForgotPasswordForm forgotPasswordForm);

	public abstract void resetPassword(String forgotPasswordCode, ResetPasswordForm resetPasswordForm,
			BindingResult result);
}
