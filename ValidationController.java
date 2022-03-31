package com.password.passwordvalidator.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.password.passwordvalidator.exceptions.InvalidPasswordException;
import com.password.passwordvalidator.response.ValidatePasswordResponse;
import com.password.passwordvalidator.service.ValidationService;

/**
 * The Class ValidationController.
 */
@RestController
@RequestMapping("/validate")
public class ValidationController {
	
	@Autowired
	ValidationService validationService;

	/**
	 * Validate password.
	 * 
	 * @param loginInputData the login input data
	 * @return the response entity
	 */
	@RequestMapping(method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<ValidatePasswordResponse> validatePassword(@RequestParam(name = "password")  final String inputPassword) {
		return validationService.validatePassword(inputPassword);
	}
}
