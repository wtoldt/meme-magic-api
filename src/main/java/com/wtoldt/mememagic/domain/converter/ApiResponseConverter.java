package com.wtoldt.mememagic.domain.converter;

import java.util.Map;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import com.wtoldt.mememagic.domain.ApiResponse;

public class ApiResponseConverter implements Converter<ApiResponse, Map<String, Object>> {

	@Override
	public Map<String, Object> convert(ApiResponse value) {
		
		return value.getMap();
	}

	@Override
	public JavaType getInputType(TypeFactory typeFactory) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JavaType getOutputType(TypeFactory typeFactory) {
		// TODO Auto-generated method stub
		return null;
	}

}
