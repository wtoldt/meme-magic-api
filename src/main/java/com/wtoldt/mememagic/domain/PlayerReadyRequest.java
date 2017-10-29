package com.wtoldt.mememagic.domain;

public class PlayerReadyRequest {

	private boolean ready;

	public boolean getReady() {
		return ready;
	}

	public void setReady(final boolean ready) {
		this.ready = ready;
	}

	@Override
	public String toString() {
		return "PlayerReadyRequest [ready=" + ready + "]";
	}
}
