package com.password.passwordvalidator.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.password.passwordvalidator.exceptions.InvalidPasswordException;
import com.password.passwordvalidator.model.GenericError;
import com.password.passwordvalidator.response.ValidatePasswordResponse;

/**
 * The Class ValidationService.
 */
@Service
public class ValidationService {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ValidationService.class);

	private String passwordRegEx = "^(?=.*[a-z])(?=.*[0-9]).{5,12}$";

	private String passwordSeqRegEx = "([^\\\\x00-\\\\x1F])\\\\1{2}";

	public ResponseEntity<ValidatePasswordResponse> validatePassword(final String inputPassword) {
		LOGGER.info("Input password: " + inputPassword);
		if (!inputPassword.matches(passwordRegEx)) {
			List<GenericError> errorList = new ArrayList<>();
			GenericError genericError = new GenericError();
			errorList.add(genericError);
			ValidatePasswordResponse validatePasswordResponse = new ValidatePasswordResponse();
			validatePasswordResponse.setMessage("Invalid password");
			validatePasswordResponse.setErrors(errorList);
			validatePasswordResponse.setPassword(inputPassword);
			return new ResponseEntity<ValidatePasswordResponse>(validatePasswordResponse, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<ValidatePasswordResponse>(new ValidatePasswordResponse(inputPassword), HttpStatus.OK);
	}
}
