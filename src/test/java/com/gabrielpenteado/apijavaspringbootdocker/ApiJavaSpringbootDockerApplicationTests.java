package com.gabrielpenteado.apijavaspringbootdocker;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.gabrielpenteado.apijavaspringbootdocker.models.UserModel;
import com.gabrielpenteado.apijavaspringbootdocker.services.UserService;

@SpringBootTest
class ApiJavaSpringbootDockerApplicationTests {

	@Autowired
	UserService userService;

	@Test
	void testCreateUser() {
		var user = UserModel
				.builder()
				.name("testname")
				.email("testemail")
				.address("testaddress")
				.urlAvatar(null)
				.build();

		userService.saveUserService(user);

		assertEquals(user, userService.getUserByEmailService(user.getEmail()));
	}

}
