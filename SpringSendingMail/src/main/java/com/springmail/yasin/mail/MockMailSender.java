package com.springmail.yasin.mail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MockMailSender implements MailSender {
	// Log tutulacak olan classda bir adet logger adında bir Log nesnesi
	// üretiliyor.
	// Üretildiği sınıf: Logger() Alt Sınıf: LoggerFactory() Çağrılan Method:
	// getLogger()

	private static final Logger logger = LoggerFactory.getLogger(MockMailSender.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.springex.yasin.mail.MailSender#send(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public void send(String to, String subject, String body) {
		logger.info("Sending Mail To : " + to);
		logger.info("Subject:" + subject);
		logger.info("Body: " + body);
	}

}
