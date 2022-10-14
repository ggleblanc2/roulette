package com.ggl.roulette.model;

public class PlayerWagerError {
	
	private final int errorCode;
	
	private final Player player;

	public PlayerWagerError(int errorCode, Player player) {
		this.errorCode = errorCode;
		this.player = player;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public Player getPlayer() {
		return player;
	}

}
