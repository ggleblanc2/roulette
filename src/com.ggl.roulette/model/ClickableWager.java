package com.ggl.roulette.model;

import java.awt.Point;
import java.awt.Rectangle;

public class ClickableWager {
	
	private final Rectangle bounds;
	
	private final String[] numbers;
	
	private final WagerType wagerType;

	public ClickableWager(Rectangle bounds, WagerType wagerType, String... numbers) {
		this.bounds = bounds;
		this.wagerType = wagerType;
		this.numbers = numbers;
	}
	
	public Point getCenterPoint() {
		int x = bounds.x + bounds.width / 2;
		int y = bounds.y + bounds.height / 2;
		return new Point(x, y);
	}
	
	/**
	 * <p>This method calculates a Point based on the position.</p>
	 * @param position - Either 1, 2, or 3 in a four segment width rectangle
	 * @return position Point
	 */
	public Point getPoint(int position) {
		int x = bounds.x + position * bounds.width / 4;
		int y = bounds.y + bounds.height / 2;
		return new Point(x, y);
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public String[] getNumbers() {
		return numbers;
	}

	public WagerType getWagerType() {
		return wagerType;
	}

}
