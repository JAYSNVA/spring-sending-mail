package com.springmail.yasin.util;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springmail.yasin.dto.UserDetailsImpl;
import com.springmail.yasin.entities.User;

@Component
public class MyUtil {

	@Autowired
	public MyUtil(MessageSource messageSource) {
		MyUtil.messageSource = messageSource;
	}

	public static void flash(RedirectAttributes redirectAttributes, 
			String kind, String messageKey) {

		redirectAttributes.addAttribute("flasKind", kind);
		redirectAttributes.addAttribute("flashMessage", MyUtil.getMessage(messageKey));

		// flashKind ve flashMessage yazdığımız alanları doğru takip etmek gerek
		// jsp tarafında çağırırken bura ile aynı olması gerekli yoksa hata
		// alınır.
	}
	
	private static String hostAndPort;
	
	@Value("${hostAndPort}")
	public void setHostAndPort(String hostAndPort) {
		MyUtil.hostAndPort=hostAndPort;
	}
	
	public static String activeProfiles;
	
	@Value("${spring.profiles.active}")
	public void setActiveProfiles(String activeProfiles) {
		MyUtil.activeProfiles=activeProfiles;
	}
	
	public static boolean isDev() {
		return activeProfiles.equals("dev");
	}
	
	public static String hostUrl() {
		return (isDev() ? "http://" : "https://") + hostAndPort;
	}

	private static MessageSource messageSource;

	public static String getMessage(String messageKey, Object... args) {
		return messageSource.getMessage(messageKey, args, Locale.getDefault());
	}

	public static void validate(boolean valid, String msgContent,
			Object... args) {
		if (!valid)
			throw new RuntimeException(getMessage(msgContent,args));
		
	}

	public static User getSessionUser() {
		UserDetailsImpl auth = getAuth();
		return auth == null ? null : auth.getUser();
	}

	public static UserDetailsImpl getAuth() {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	
	    if (auth != null) {
	      Object principal = auth.getPrincipal();
	      if (principal instanceof UserDetailsImpl) {
	        return (UserDetailsImpl) principal;
	      }
	    }
	    return null;	  
	}}
