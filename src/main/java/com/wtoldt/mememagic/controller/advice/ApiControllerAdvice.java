package com.wtoldt.mememagic.controller.advice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice(basePackages= {"com.wtoldt.controller"})
public class ApiControllerAdvice {

	@ResponseStatus(code=HttpStatus.BAD_REQUEST)
	@ExceptionHandler(BindException.class)
	public Map<String, Object> handleBindException() {
		final Map<String, Object> response = new HashMap<>();

		response.put("success", "false");
		response.put("error", "true");
		response.put("message", "There was a bind exception with your request.");

		return response;
	}

}
