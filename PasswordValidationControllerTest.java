package com.password.passwordvalidator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.password.passwordvalidator.controller.PasswordValidationController;
import com.password.passwordvalidator.response.ValidatePasswordResponse;

@SpringBootTest
class PasswordValidationControllerTest {

	@Test
	void contextLoads() {
	}

	@Autowired
	PasswordValidationController validationController;

	/**
	 * Test validate password minimum exact.
	 */
	@Test
	public void testValidatePasswordMinimumLength() {
		String password = "zawe4";// 5 characters
		ResponseEntity<ValidatePasswordResponse> response = validationController.validatePassword(password);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	public void testValidatePasswordMaximumLength() {
		String password = "zawe24546789";// 12 characters
		ResponseEntity<ValidatePasswordResponse> response = validationController.validatePassword(password);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	public void testValidatePasswordMoreThanMaximumLength() {
		String password = "zawe24546789t";// 13 characters
		ResponseEntity<ValidatePasswordResponse> response = validationController.validatePassword(password);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}
	
	@Test
	public void testValidatePasswordLessthanMinLength() {
		String password = "zaw";// 3 characters
		ResponseEntity<ValidatePasswordResponse> response = validationController.validatePassword(password);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}
	
	@Test
	public void testValidatePasswordCaptialLetters() {
		String password = "j872845tgA";// 6 characters
		ResponseEntity<ValidatePasswordResponse> response = validationController.validatePassword(password);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}
	
	@Test
	public void testValidatePasswordSequenceLetters() {
		String password = "foofoofoo1";// 6 characters
		ResponseEntity<ValidatePasswordResponse> response = validationController.validatePassword(password);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}
}
