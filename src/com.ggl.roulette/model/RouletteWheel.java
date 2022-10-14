package com.ggl.roulette.model;

import java.awt.Color;
import java.awt.Point;

public class RouletteWheel {
	
	private Color redColor;
	private Color greenColor;
	
	private final WheelSegment[] wheelSegments;
	
	public RouletteWheel() {
		this.redColor = new Color(205, 29, 33);
		this.greenColor = new Color(56, 173, 2);
		this.wheelSegments = defineWheelSegments();
	}
	
	private WheelSegment[] defineWheelSegments() {
		WheelSegment[] wheelSegments = new WheelSegment[38];
		wheelSegments[0] = new WheelSegment("00", greenColor, new Point(12, 10));
		wheelSegments[1] = new WheelSegment("27", redColor, new Point(12, 10));
		wheelSegments[2] = new WheelSegment("10", Color.BLACK, new Point(12, 10));
		wheelSegments[3] = new WheelSegment("25", redColor, new Point(12, 10));
		wheelSegments[4] = new WheelSegment("29", Color.BLACK, new Point(12, 10));
		wheelSegments[5] = new WheelSegment("12", redColor, new Point(11, 11));
		wheelSegments[6] = new WheelSegment("8", Color.BLACK, new Point(7, 10));
		wheelSegments[7] = new WheelSegment("19", redColor, new Point(10, 10));
		wheelSegments[8] = new WheelSegment("31", Color.BLACK, new Point(12, 10));
		wheelSegments[9] = new WheelSegment("18", redColor, new Point(10, 12));
		wheelSegments[10] = new WheelSegment("6", Color.BLACK, new Point(6, 12));
		wheelSegments[11] = new WheelSegment("21", redColor, new Point(12, 10));
		wheelSegments[12] = new WheelSegment("33", Color.BLACK, new Point(12, 10));
		wheelSegments[13] = new WheelSegment("16", redColor, new Point(12, 10));
		wheelSegments[14] = new WheelSegment("4", Color.BLACK, new Point(6, 10));
		wheelSegments[15] = new WheelSegment("23", redColor, new Point(10, 10));
		wheelSegments[16] = new WheelSegment("35", Color.BLACK, new Point(10, 10));
		wheelSegments[17] = new WheelSegment("14", redColor, new Point(10, 10));
		wheelSegments[18] = new WheelSegment("2", Color.BLACK, new Point(4, 10));
		wheelSegments[19] = new WheelSegment("0", greenColor, new Point(4, 10));
		wheelSegments[20] = new WheelSegment("28", Color.BLACK, new Point(10, 10));
		wheelSegments[21] = new WheelSegment("9", redColor, new Point(4, 8));
		wheelSegments[22] = new WheelSegment("26", Color.BLACK, new Point(10, 8));
		wheelSegments[23] = new WheelSegment("30", redColor, new Point(9, 9));
		wheelSegments[24] = new WheelSegment("11", Color.BLACK, new Point(10, 8));
		wheelSegments[25] = new WheelSegment("7", redColor, new Point(6, 10));
		wheelSegments[26] = new WheelSegment("20", Color.BLACK, new Point(10, 8));
		wheelSegments[27] = new WheelSegment("32", redColor, new Point(10, 8));
		wheelSegments[28] = new WheelSegment("17", Color.BLACK, new Point(12, 10));
		wheelSegments[29] = new WheelSegment("5", redColor, new Point(6, 10));
		wheelSegments[30] = new WheelSegment("22", Color.BLACK, new Point(12, 10));
		wheelSegments[31] = new WheelSegment("34", redColor, new Point(12, 10));
		wheelSegments[32] = new WheelSegment("15", Color.BLACK, new Point(12, 10));
		wheelSegments[33] = new WheelSegment("3", redColor, new Point(6, 10));
		wheelSegments[34] = new WheelSegment("24", Color.BLACK, new Point(12, 10));
		wheelSegments[35] = new WheelSegment("36", redColor, new Point(12, 10));
		wheelSegments[36] = new WheelSegment("13", Color.BLACK, new Point(12, 10));
		wheelSegments[37] = new WheelSegment("1", redColor, new Point(8, 10));
		
		return wheelSegments;
	}
	
	public int length() {
		return wheelSegments.length;
	}
	
	public WheelSegment getWheelSegment(int index) {
		return wheelSegments[index];
	}

	public WheelSegment[] getWheelSegments() {
		return wheelSegments;
	}
	
	public String[] getNumberRange(int start, int end) {
		String[] output = new String[end - start + 1]; 
		int index = 0;
		for (int value = start; value <= end; value++) {
			output[index++] = Integer.toString(value);
		}
		
		return output;
	}
	
	public String[] getOddNumbers() {
		String[] output = new String[18];
		int index = 0;
		for (int value = 1; value < 36; value += 2) {
			output[index++] = Integer.toString(value);
		}
		
		return output;
	}
	
	public String[] getEvenNumbers() {
		String[] output = new String[18];
		int index = 0;
		for (int value = 2; value <= 36; value += 2) {
			output[index++] = Integer.toString(value);
		}
		
		return output;
	}
	
	public String[] getRedNumbers() {
		String[] output = new String[18];
		int index = 0;
		for (WheelSegment wheelSegment : wheelSegments) {
			if (wheelSegment.getBackgroundColor().equals(redColor)) {
				output[index++] = wheelSegment.getWheelNumber();
			}
		}
		
		return output;
	}
	
	public String[] getBlackNumbers() {
		String[] output = new String[18];
		int index = 0;
		for (WheelSegment wheelSegment : wheelSegments) {
			if (wheelSegment.getBackgroundColor().equals(Color.BLACK)) {
				output[index++] = wheelSegment.getWheelNumber();
			}
		}
		
		return output;
	}
	
	public Color getRedColor() {
		return redColor;
	}
	
	public Color getGreenColor() {
		return greenColor;
	}
	
	public Color getBackgroundColor(int number) {
		String numberString = Integer.toString(number);
		for (WheelSegment wheelSegment : wheelSegments) {
			if (numberString.equals(wheelSegment.getWheelNumber())) {
				return wheelSegment.getBackgroundColor();
			}
		}
		
		return greenColor;
	}

}
