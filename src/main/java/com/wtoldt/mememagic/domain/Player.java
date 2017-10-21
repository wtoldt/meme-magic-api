package com.wtoldt.mememagic.domain;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

public class Player {

	@NotBlank
	@Size(min=1, max=50, message="Player name has to be between 1 and 50 characters long")
	private String name;

	//TODO: dont let players set this (@null?)
	private int score;

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public int getScore() {
		return score;
	}

	public void setScore(final int score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "Player [name=" + name + ", score=" + score + "]";
	}

}
