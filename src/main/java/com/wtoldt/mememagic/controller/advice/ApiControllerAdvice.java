package com.wtoldt.mememagic.controller.advice;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice()
public class ApiControllerAdvice {

	@ExceptionHandler(BindException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Map<String, Object> handleBindException(final BindException e) {
		final Map<String, Object> response = new HashMap<>();

		response.put("success", "false");
		response.put("error", "true");

		// TODO: better message from bindingResult

		final BindingResult errors = e.getBindingResult();
		final StringBuilder errorMessage = new StringBuilder();
		for (FieldError fieldError : errors.getFieldErrors()) {
			errorMessage.append(fieldError.getDefaultMessage());
		}
		response.put("message", errorMessage.toString());

		return response;
	}

}
