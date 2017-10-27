package com.wtoldt.mememagic.exception;

/**
 * Created by Emily Li on 21/10/2017.
 */
public class NoSuchGameException extends Exception {
	private static final long serialVersionUID = 8832247073412936205L;

	public NoSuchGameException(final int gameId) {
		super(String.format("Game %s not found!", gameId));
	}

}
