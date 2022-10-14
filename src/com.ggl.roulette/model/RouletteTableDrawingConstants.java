package com.ggl.roulette.model;

public class RouletteTableDrawingConstants {
	
	private final int segmentWidth, margin, lineMargin, imageMargin;
	
	public RouletteTableDrawingConstants() {
		this.imageMargin = 10;
		this.margin = 30;
		this.lineMargin = 3;
		this.segmentWidth = 64;
	}

	public int getSegmentWidth() {
		return segmentWidth;
	}

	public int getMargin() {
		return margin;
	}

	public int getLineMargin() {
		return lineMargin;
	}

	public int getImageMargin() {
		return imageMargin;
	}

}
