package com.wtoldt.mememagic.domain;

import java.net.URI;

public class Image {

	private URI uri;

	public URI getUri() {
		return uri;
	}

	public void setUri(final URI uri) {
		this.uri = uri;
	}

	@Override
	public String toString() {
		return "Image [uri=" + uri + "]";
	}
}
