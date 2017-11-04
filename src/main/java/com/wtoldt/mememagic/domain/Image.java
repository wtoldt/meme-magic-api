package com.wtoldt.mememagic.domain;

public class Image {

	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(final String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "Image [url=" + url + "]";
	}

}
