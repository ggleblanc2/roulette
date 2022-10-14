package com.ggl.roulette.model;

import java.awt.Color;
import java.awt.Point;

public class WheelSegment {
	
	private final Color backgroundColor;
	
	private final Point correctionDelta;
	
	private final String wheelNumber;

	public WheelSegment(String wheelNumber, Color backgroundColor, Point correctionDelta) {
		this.wheelNumber = wheelNumber;
		this.backgroundColor = backgroundColor;
		this.correctionDelta = correctionDelta;
	}

	public Color getBackgroundColor() {
		return backgroundColor;
	}

	public Point getCorrectionDelta() {
		return correctionDelta;
	}

	public String getWheelNumber() {
		return wheelNumber;
	}

}
