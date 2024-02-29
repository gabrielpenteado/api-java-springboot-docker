package com.gabrielpenteado.apijavaspringbootdocker;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.gabrielpenteado.apijavaspringbootdocker.dto.UserRecordDto;
import com.gabrielpenteado.apijavaspringbootdocker.services.UserService;

@SpringBootTest
class ApiJavaSpringbootDockerApplicationTests {

	@Autowired
	UserService userService;

	@Test
	void testCreateUser() {
		var user = new UserRecordDto("testName", "testEmail", "testAddress", "testUrlAvatar");

		userService.saveUserService(user);

		assertEquals(user, userService.getUserByEmailService(user.email()));
	}

}
