package com.springmail.yasin;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringSendingMailApplication.class)
@WebAppConfiguration
public class SpringSendingMailApplicationTests {

	@Test
	public void contextLoads() {
	}

}
