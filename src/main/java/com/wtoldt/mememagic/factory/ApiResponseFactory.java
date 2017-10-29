package com.wtoldt.mememagic.factory;

import com.wtoldt.mememagic.domain.ApiResponse;

public class ApiResponseFactory {

	public static ApiResponse of(final boolean success) {
		final ApiResponse apiResponse = new ApiResponse();
		apiResponse.setSuccess(success);
		return apiResponse;
	}

	public static ApiResponse successful() {
		return of(true);
	}

	public static ApiResponse failure() {
		return of(false);
	}

}
