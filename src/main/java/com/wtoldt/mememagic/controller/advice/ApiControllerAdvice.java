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

import com.wtoldt.mememagic.domain.ApiResponse;
import com.wtoldt.mememagic.exception.GameException;
import com.wtoldt.mememagic.factory.ApiResponseFactory;

@RestControllerAdvice
public class ApiControllerAdvice {

	@ExceptionHandler(BindException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiResponse handleBindException(final BindException e) {
		final List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
		final List<Map<String, Object>> errorsList = new ArrayList<>();
		for (final FieldError field : fieldErrors) {
			final Map<String, Object> errorMap = new HashMap<>();
			errorMap.put("field", field.getField());
			errorMap.put("value", field.getRejectedValue());
			errorMap.put("message", field.getDefaultMessage());

			errorsList.add(errorMap);
		}

		final ApiResponse response = ApiResponseFactory.failure();
		response.put("message", "Request was rejected because there were errors.");
		response.put("requestErrors", errorsList);

		return response;
	}

	@ExceptionHandler(GameException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiResponse handleGameException(final GameException e) {

		final ApiResponse response = ApiResponseFactory.failure();
		response.put("message", e.getMessage());
		return response;
	}

}
