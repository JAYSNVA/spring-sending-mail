package com.springmail.yasin.util;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

	private static MessageSource messageSource;

	private static String getMessage(String messageKey, Object... args) {
		return messageSource.getMessage(messageKey, args, Locale.getDefault());
	}
}
