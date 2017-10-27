package com.wtoldt.mememagic.domain;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

public class PlayerReadyRequest {
	
	@NotBlank
	@Size(min=1, max=50, message=" Player name must be between 1 and 50 characters")
	private String player;

	public String getPlayer() {
		return player;
	}

	public void setPlayer(final String player) {
		this.player = player;
	}

}
