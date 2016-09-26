package com.springmail.yasin.controllers;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springmail.yasin.dto.ForgotPasswordForm;
import com.springmail.yasin.dto.ResetPasswordForm;
import com.springmail.yasin.dto.SignupForm;
import com.springmail.yasin.mail.MailSender;
import com.springmail.yasin.services.UserService;
import com.springmail.yasin.util.MyUtil;
import com.springmail.yasin.validators.ForgotPasswordFormValidator;
import com.springmail.yasin.validators.ResetPasswordFormValidator;
import com.springmail.yasin.validators.SignupFormValidator;


@Controller
public class RootController {

	private static final Logger logger = LoggerFactory.getLogger(RootController.class);
	
	private MailSender mailSender;
	private  UserService userService;
	private SignupFormValidator signupFormValidator;
	private ForgotPasswordFormValidator forgotPasswordFormValidator;
	private ResetPasswordFormValidator resetPasswordFormValidator;
	@Autowired                                                                     // public RootController(@Qualifier("smtpMailSender")MailSender mailSender)
	public RootController(MailSender mailSender, UserService userService, 
			SignupFormValidator signupFormValidator,
			ForgotPasswordFormValidator forgotPasswordFormValidator,
			ResetPasswordFormValidator resetPasswordFormValidator) {          // @Qualifier("smtpMailSender")
																                 // bu şekilde de
		this.mailSender = mailSender;			   								// çalışabilir. yada
		this.userService = userService;
		this.signupFormValidator = signupFormValidator;
		this.forgotPasswordFormValidator = forgotPasswordFormValidator;
		this.resetPasswordFormValidator = resetPasswordFormValidator;
	}

	
	@InitBinder("signupForm")
	protected void initSignupBinder (WebDataBinder binder) {
		binder.setValidator(signupFormValidator);
	} 
	
	@InitBinder("forgotPasswordForm")
	protected void initForgotPasswordBinder (WebDataBinder binder) {
		binder.setValidator(forgotPasswordFormValidator);
	} 
	
	@InitBinder("resetPasswordForm")
	protected void initResetPasswordBinder (WebDataBinder binder) {
		binder.setValidator(resetPasswordFormValidator);
	} 
	
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup(Model model) {

		model.addAttribute(new SignupForm());

		return "signup";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signup(@ModelAttribute("signupForm") @Valid SignupForm signupForm,
			BindingResult result, RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) 
			return "signup";
		
		userService.signup(signupForm);
		
		MyUtil.flash(redirectAttributes, "success", "signupSuccess");
		

	
		return "redirect:/";
	}
	
	

	@RequestMapping(value = "/forgot-password", method = RequestMethod.GET)
	public String forgotPassword(Model model) {

		model.addAttribute(new ForgotPasswordForm());

		return "forgot-password";
	}


	/**
	 * Forgot password
	 */
	@RequestMapping(value = "/forgot-password", method = RequestMethod.POST)
	public String forgotPassword(
			@ModelAttribute("forgotPasswordForm") @Valid ForgotPasswordForm forgotPasswordForm,
			BindingResult result, RedirectAttributes redirectAttributes) {

		if (result.hasErrors())
			return "forgot-password";

		userService.forgotPassword(forgotPasswordForm);
		MyUtil.flash(redirectAttributes, "info", "checkMailResetPassword");

		return "redirect:/";
	}
	
	/**
	 * Reset password
	 */
	@RequestMapping(value = "/reset-password/{forgotPasswordCode}")
	public String resetPassword(@PathVariable("forgotPasswordCode") String forgotPassword, Model model) {
		
		model.addAttribute(new ResetPasswordForm());
		return "reset-password";
	}
	
	
	@RequestMapping(value = "/reset-password/{forgotPasswordCode}",
			method = RequestMethod.POST)
	public String resetPassword(
			@PathVariable("forgotPasswordCode") String forgotPasswordCode,
			@ModelAttribute("resetPasswordForm")
				@Valid ResetPasswordForm resetPasswordForm,
			BindingResult result,
			RedirectAttributes redirectAttributes) {
		
		userService.resetPassword(forgotPasswordCode, resetPasswordForm, result);
		
		if (result.hasErrors())
			return "reset-password";

		MyUtil.flash(redirectAttributes, "success", "passwordChanged");

		return "redirect:/login";
}
}
