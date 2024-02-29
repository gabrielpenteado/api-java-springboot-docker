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
		var userRecordDto = new UserRecordDto("testname", "testemail", "testaddress", null);

		assertEquals(userService.saveUserService(userRecordDto).getBody(),
				userService.getUserByEmailService("testemail").getBody());

		userService.deleteUserByEmailService("testemail");
	}

}
