package com.wtoldt.mememagic.domain;

import javax.validation.constraints.NotNull;

public class PlayerReadyRequest {

	@NotNull(message="Ready must not be null!")
	private Boolean ready;

	public Boolean getReady() {
		return ready;
	}

	public void setReady(final Boolean ready) {
		this.ready = ready;
	}

	@Override
	public String toString() {
		return "PlayerReadyRequest [ready=" + ready + "]";
	}
}
