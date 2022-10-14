package com.ggl.roulette.model;

import java.awt.Point;

public class Wager {
	
	private boolean isWinningWager;
	
	private int chipCount;
	private int chipValue;
	
	private final Point boardLocation;
	
	private final Player player;
	
	private final String[] wagerNumbers;
	
	private final WagerType wagerType;

	public Wager(Point boardLocation, Player player, WagerType wagerType, 
			String... wagerNumbers) {
		this.boardLocation = boardLocation;
		this.player = player;
		this.wagerType = wagerType;
		this.wagerNumbers = wagerNumbers;
		this.isWinningWager = false;
	}
	
	public boolean isSameWager(Wager otherWager) {
		return boardLocation.x == otherWager.boardLocation.x &&
				boardLocation.y == otherWager.boardLocation.y;
	}

	public void placeWagerAmount(int chipCount, int chipValue) {
		this.chipCount = chipCount;
		this.chipValue = chipValue;
	}
	
	public boolean isWinner(String number) {
		for (String s : wagerNumbers) {
			if (s.equals(number)) {
				return true;
			}
		}
		
		return false;
	}

	public boolean isWinningWager() {
		return isWinningWager;
	}

	public void setWinningWager(boolean isWinningWager) {
		this.isWinningWager = isWinningWager;
	}

	public Player getPlayer() {
		return player;
	}

	public void incrementChipCount(int increment) {
		this.chipCount += increment;
	}
	
	public int getChipCount() {
		return chipCount;
	}

	public int getChipValue() {
		return chipValue;
	}

	public Point getBoardLocation() {
		return boardLocation;
	}

	public WagerType getWagerType() {
		return wagerType;
	}

}
