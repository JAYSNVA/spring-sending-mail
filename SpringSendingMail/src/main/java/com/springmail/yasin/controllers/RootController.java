package com.springmail.yasin.controllers;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springmail.yasin.dto.SignupForm;
import com.springmail.yasin.mail.MailSender;
import com.springmail.yasin.services.UserService;
import com.springmail.yasin.util.MyUtil;
import com.springmail.yasin.validators.SignupFormValidator;

@Controller
public class RootController {

	private static final Logger logger = LoggerFactory.getLogger(RootController.class);
	
	private MailSender mailSender;
	private  UserService userService;
	private SignupFormValidator signupFormValidator;

	@Autowired                                                                     // public RootController(@Qualifier("smtpMailSender")MailSender mailSender)
	public RootController(MailSender mailSender, UserService userService, 
			SignupFormValidator signupFormValidator) {                            // @Qualifier("smtpMailSender")
																                 // bu şekilde de
		this.mailSender = mailSender;			   								// çalışabilir. yada
		this.userService = userService;
		this.signupFormValidator=signupFormValidator;
	}

	
	@InitBinder("signupForm")
	protected void initSignupBinder (WebDataBinder binder) {
		binder.setValidator(signupFormValidator);
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

}
