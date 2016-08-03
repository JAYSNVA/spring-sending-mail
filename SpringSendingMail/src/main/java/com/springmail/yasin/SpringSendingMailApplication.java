package com.springmail.yasin;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import scala.App;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class SpringSendingMailApplication {

	private static final Logger logger = LoggerFactory.getLogger(SpringSendingMailApplication.class);

	public static void main(String[] args) {

		ApplicationContext ctx = SpringApplication.run(SpringSendingMailApplication.class, args);

		logger.info("Beans in application context: ");

		String beanNames[] = ctx.getBeanDefinitionNames();
		Arrays.sort(beanNames);

		for (String beanName : beanNames) {
			logger.info(beanName);
		}
	}
}
