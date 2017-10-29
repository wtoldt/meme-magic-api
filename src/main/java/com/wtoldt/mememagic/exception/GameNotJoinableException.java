package com.wtoldt.mememagic.exception;

public class GameNotJoinableException extends GameException {
	private static final long serialVersionUID = 5452567459415694368L;

	public GameNotJoinableException(final int gameId) {
		super(String.format("%s is not currently joinable!", gameId));
	}

}
