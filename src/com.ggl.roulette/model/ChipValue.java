package com.ggl.roulette.model;

public class ChipValue {
	
	private final int value;
	
	public ChipValue(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	@Override
	public String toString() {
		return "$" + String.format("%,d", value) + ".00";
	}
	
}
