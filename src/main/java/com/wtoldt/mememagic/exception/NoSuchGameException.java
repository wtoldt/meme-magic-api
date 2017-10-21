package com.wtoldt.mememagic.exception;

/**
 * Created by Emily Li on 21/10/2017.
 */
public class NoSuchGameException extends Exception {

	public NoSuchGameException(final int gameId) {
		super(String.format("Game %s not found!", gameId));
	}

}
