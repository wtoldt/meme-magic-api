package com.wtoldt.mememagic.domain;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

public class JoinGameRequest {

	@NotBlank
	@Size(min=1, max=50, message=" Player name must be between 1 and 50 characters")
	private String playerName;

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(final String playerName) {
		this.playerName = playerName;
	}

}
