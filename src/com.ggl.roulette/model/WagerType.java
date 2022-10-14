package com.ggl.roulette.model;

public enum WagerType {
	NUMBER(36, true, "Number bet"), 
	SPLIT_BET(18, true, "Split between two numbers bet"), 
	THREE_CORNER_00(12, true, "00, 1, 2 combination bet"), 
	THREE_CORNER_0(12, true, "0, 2, 3 combination bet"), 
	STREET_BET(12, true, "Any single row of three numbers bet"),
	FOUR_CORNER(9, true, "Four number corner bet"), 
	LINE_BET(6, true, "Any pair of rows bet"), 
	COLUMN_BET(3, false, "Column bet"), 
	FIRST_12(3, false, "The numbers 1 - 12 bet"), 
	SECOND_12(3, false, "The numbers 13 - 24 bet"), 
	THIRD_12(3, false, "The numbers 25 - 36 bet"),
	FIRST_18(2, false, "The numbers 1 - 18 bet"), 
	LAST_18(2, false, "The numbers 19 - 36 bet"), 
	ODD(2, false, "The odd numbers bet"), 
	EVEN(2, false, "The even numbers bet"), 
	RED(2, false, "The red numbers bet"), 
	BLACK(2, false, "The black numbers bet");

	private final boolean innerWager;
	
	private final int payoff;
	
	private final String description;

	private WagerType(int payoff, boolean innerWager, String description) {
		this.payoff = payoff;
		this.innerWager = innerWager;
		this.description = description;
	}

	public int getPayoff() {
		return payoff;
	}

	public boolean isInnerWager() {
		return innerWager;
	}

	public String getDescription() {
		return description;
	}

}
