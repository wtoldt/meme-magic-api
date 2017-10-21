package com.wtoldt.mememagic.controller.advice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.wtoldt.mememagic.exception.NoSuchGameException;

@RestControllerAdvice()
public class ApiControllerAdvice {

	@ExceptionHandler(BindException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Map<String, Object> handleBindException(final BindException e) {
		final List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
		final List<Map<String, Object>> errorsList = new ArrayList<>();
		for (final FieldError field : fieldErrors) {
			final Map<String, Object> errorMap = new HashMap<>();
			errorMap.put("field", field.getField());
			errorMap.put("value", field.getRejectedValue());
			errorMap.put("message", field.getDefaultMessage());

			errorsList.add(errorMap);
		}

		final Map<String, Object> responseMap = new HashMap<>();
		responseMap.put("error", "true");
		responseMap.put("success", "false");
		responseMap.put("message", "Request was rejected because there were errors.");
		responseMap.put("requestErrors", errorsList);

		return responseMap;
	}

	@ExceptionHandler(NoSuchGameException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Map<String, Object> handleNoSuchGameException(final NoSuchGameException e) {
		final Map<String, Object> responseMap = new HashMap<>();
		responseMap.put("error", "true");
		responseMap.put("success", "false");
		responseMap.put("message", e.getMessage());

		return responseMap;
	}

}
