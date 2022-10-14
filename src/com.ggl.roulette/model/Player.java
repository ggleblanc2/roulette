package com.ggl.roulette.model;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Player {
	
	private int balance;
	private int buyInAmount;
	private int chipCount;
	
	private ChipValue chipValue;
	
	private RouletteChip rouletteChip;
	
	private final String name;

	public Player(String name) {
		this.name = name;
	}
	
	public void buyIn(int buyInAmount, ChipValue chipValue) {
		this.buyInAmount = buyInAmount;
		this.balance = buyInAmount;
		this.chipValue = chipValue;
		this.chipCount = buyInAmount / getChipValue();
	}
	
	public void removeChips(int amount) {
		this.chipCount -= amount;
		this.balance -= amount * getChipValue();
	}
	
	public void addChips(int amount) {
		this.chipCount += amount;
		this.balance += amount * getChipValue();
	}

	public RouletteChip getRouletteChip() {
		return rouletteChip;
	}

	public void setRouletteChip(RouletteChip rouletteChip) {
		this.rouletteChip = rouletteChip;
	}

	public int getBalance() {
		return balance;
	}

	public int getBuyInAmount() {
		return buyInAmount;
	}

	public int getChipCount() {
		return chipCount;
	}

	public int getChipValue() {
		return chipValue.getValue();
	}

	public Color getChipColor() {
		return rouletteChip.getChipColor();
	}

	public BufferedImage getChipImage() {
		return rouletteChip.getChipImage();
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Player [name=");
		builder.append(name);
		builder.append(", balance=");
		builder.append(balance);
		builder.append(", buyInAmount=");
		builder.append(buyInAmount);
		builder.append(", chipCount=");
		builder.append(chipCount);
		builder.append(", chipValue=");
		builder.append(chipValue);
		builder.append("]");
		return builder.toString();
	}

}
