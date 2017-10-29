package com.wtoldt.mememagic.domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.wtoldt.mememagic.domain.converter.ApiResponseConverter;

@JsonSerialize(converter=ApiResponseConverter.class)
public class ApiResponse {
	private final Map<String, Object> responseMap = new HashMap<>();

	public void setSuccess(boolean success) {
		responseMap.put("success", success);
	}
	
	public Boolean getSuccess() {
		return (Boolean) responseMap.get("success");
	}
	
	public void setValue(String key, Object value) {
		responseMap.put(key, value);
	}
	
	public Object getValue(String key) {
		return responseMap.get(key);
	}
	
	public Map<String, Object> getMap() {
		return Collections.unmodifiableMap(responseMap);
	}
	
	public static ApiResponse successfulSingleton(String key, Object value) {
		final ApiResponse apiResponse = new ApiResponse();
		apiResponse.setSuccess(true);
		apiResponse.setValue(key, value);
		return apiResponse;
	}
	
	public static ApiResponse failureSingleton(String key, Object value) {
		final ApiResponse apiResponse = new ApiResponse();
		apiResponse.setSuccess(true);
		apiResponse.setValue(key, value);
		return apiResponse;
	}
}
