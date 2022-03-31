package com.password.passwordvalidator.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

	private String passwordRegEx = "^(?=.*[a-z])(?=.*[0-9]).{5,12}$";

	public ResponseEntity<ValidatePasswordResponse> validatePassword(final String password) {
		boolean validPasswordCheck = isValidPasswordCheck(password);
		if (validPasswordCheck) {
			List<GenericError> errorList = new ArrayList<GenericError>();
			GenericError genericError = new GenericError();
			genericError.setErrorMessage("Invalid password entered...");
			errorList.add(genericError);
			ValidatePasswordResponse validatePasswordResponse = new ValidatePasswordResponse();
			validatePasswordResponse.setMessage("Invalid password");
			validatePasswordResponse.setErrors(errorList);
			validatePasswordResponse.setPassword(password);
			return new ResponseEntity<ValidatePasswordResponse>(new ValidatePasswordResponse(password),
					HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<ValidatePasswordResponse>(new ValidatePasswordResponse(password), HttpStatus.OK);
	}

	private boolean isValidPasswordCheck(String str) {
		boolean isRepeatedCheracter = false;
		Pattern pattern = Pattern.compile("(\\w)\\1+");
		Matcher matcher = pattern.matcher(str);

		Pattern pattern1 = Pattern.compile(passwordRegEx);
		Matcher matcher1 = pattern1.matcher(str);

		if (matcher.find() || !matcher1.matches()) {
			isRepeatedCheracter = true;
		}
		return isRepeatedCheracter;
	}

}
