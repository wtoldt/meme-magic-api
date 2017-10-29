package com.wtoldt.mememagic.domain;

import java.util.HashMap;

public class ApiResponse extends HashMap<String, Object>{
	private static final long serialVersionUID = -8703407069510207576L;

	public void setSuccess(final boolean success) {
		put("success", success);
	}

	public Boolean getSuccess() {
		return (Boolean) get("success");
	}

	public void setValue(final String key, final Object value) {
		put(key, value);
	}

	public Object getValue(final String key) {
		return get(key);
	}

}
