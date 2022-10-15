package com.ggl.roulette.model;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class ClickableWagers {
	
	private final int halfWidth, quarterWidth, segmentWidth;
	private final int margin, imageMargin, lineMargin, totalMargin;
	
	private final List<ClickableWager> clickableWagers;
	
	private final RouletteWheel rouletteWheel;
	
	public ClickableWagers() {
		RouletteTableDrawingConstants rtdc = new RouletteTableDrawingConstants(); 
		this.margin = rtdc.getMargin();
		this.lineMargin = rtdc.getLineMargin();
		this.segmentWidth = rtdc.getSegmentWidth();
		this.halfWidth = segmentWidth / 2;
		this.quarterWidth = halfWidth / 2;
		this.imageMargin = rtdc.getImageMargin();
		this.totalMargin = margin + lineMargin + imageMargin;
		this.rouletteWheel = new RouletteWheel();
		this.clickableWagers = createClickableWagers();
	}
	
	private List<ClickableWager> createClickableWagers() {
		List<ClickableWager> clickableWagers = new ArrayList<>();
		
		String[][] numbers = { 
				{ "3", "6", "9", "12", "15", "18", "21", "24", "27", "30", "33", "36" },
				{ "2", "5", "8", "11", "14", "17", "20", "23", "26", "29", "32", "35" },
				{ "1", "4", "7", "10", "13", "16", "19", "22", "25", "28", "31", "34" }
		};
		
		createZeroBets(clickableWagers);
		createNumberBets(clickableWagers, numbers);
		createColumnBets(clickableWagers, numbers);
		createLineBets(clickableWagers, numbers);
		createStreetBets(clickableWagers, numbers);
		createFourCornerBets(clickableWagers, numbers);
		createHorizontalDoubleBets(clickableWagers, numbers);
		createVerticalDoubleBets(clickableWagers, numbers);
		createOutsideBets(clickableWagers);
		
		return clickableWagers;
	}

	private void createZeroBets(List<ClickableWager> clickableWagers) {
		int x = totalMargin + quarterWidth;
		int y = totalMargin + quarterWidth;
		int extraHeight = 3 * segmentWidth / 2;
		Rectangle bounds = new Rectangle(x, y, halfWidth, segmentWidth);
		clickableWagers.add(new ClickableWager(bounds, WagerType.NUMBER, "0"));
		
		y += extraHeight;
		bounds = new Rectangle(x, y, halfWidth, segmentWidth);
		clickableWagers.add(new ClickableWager(bounds, WagerType.NUMBER, "00"));
		
		y = totalMargin + extraHeight - quarterWidth;
		bounds = new Rectangle(x, y, halfWidth, halfWidth);
		String[] numbers = { "0", "00" };
		clickableWagers.add(new ClickableWager(bounds, WagerType.SPLIT_BET, numbers));
		
		x = totalMargin + segmentWidth - quarterWidth;
		y = totalMargin + segmentWidth - quarterWidth;
		bounds = new Rectangle(x, y, halfWidth, halfWidth);
		numbers = new String[] { "0", "2", "3" };
		clickableWagers.add(new ClickableWager(bounds, WagerType.THREE_CORNER_0, 
				numbers));
		
		y += segmentWidth;
		bounds = new Rectangle(x, y, halfWidth, halfWidth);
		numbers = new String[] { "00", "1", "2" };
		clickableWagers.add(new ClickableWager(bounds, WagerType.THREE_CORNER_00, 
				numbers));
	}
	
	private void createFourCornerBets(List<ClickableWager> clickableWagers, 
			String[][] numbers) {
		int x = totalMargin + 2 * segmentWidth - quarterWidth;
		int y = totalMargin + segmentWidth - quarterWidth;
		testFourCornersRow(clickableWagers, numbers, x, y);
		y += segmentWidth;
		testFourCornersRow(clickableWagers, numbers, x, y);
	}

	private void testFourCornersRow(List<ClickableWager> clickableWagers, 
			String[][] numbers, int x, int y) {
		for (int i = 0; i < numbers[1].length - 1; i++) {
			Rectangle bounds = new Rectangle(x, y, halfWidth, halfWidth);
			int start = Integer.valueOf(numbers[1][i]);
			int next1 = start + 1;
			int next2 = start + 3;
			int next3 = start + 4;
			String[] range = { Integer.toString(start), Integer.toString(next1),
					Integer.toString(next2), Integer.toString(next3)}; 
			clickableWagers.add(new ClickableWager(bounds, WagerType.FOUR_CORNER, 
					range));
			x += segmentWidth;
		}
	}
	
	private void createNumberBets(List<ClickableWager> clickableWagers, 
			String[][] numbers) {
		int x = totalMargin + segmentWidth + quarterWidth;
		for (int i = 0; i < numbers[0].length; i++) {
			int y = totalMargin + quarterWidth;
			for (int j = 0; j < numbers.length; j++) {
				Rectangle bounds = new Rectangle(x, y, halfWidth, halfWidth);
				clickableWagers.add(new ClickableWager(bounds, WagerType.NUMBER, 
						numbers[j][i]));
				y += segmentWidth;
			}
			x += segmentWidth;
		}
	}
	
	private void createLineBets(List<ClickableWager> clickableWagers, 
			String[][] numbers) {
		int x = totalMargin + 2 * segmentWidth - quarterWidth;
		int y = totalMargin + 3 * segmentWidth - quarterWidth;
		for (int index = 0; index < numbers[2].length - 1; index++) {
			Rectangle bounds = new Rectangle(x, y, halfWidth, halfWidth);
			int start = Integer.valueOf(numbers[2][index]);
			String[] range = createLineBetRange(start);
			clickableWagers.add(new ClickableWager(bounds, WagerType.LINE_BET, range));
			x += segmentWidth;
		}
	}

	private String[] createLineBetRange(int start) {
		int next = start + 3;
		String[] range1 = rouletteWheel.getNumberRange(start, start + 2);
		String[] range2 = rouletteWheel.getNumberRange(next, next + 2);
		String[] range = new String[range1.length + range2.length];
		
		int j = 0;
		for (int i = 0; i < range1.length; i++) {
			range[j++] = range1[i];
		}
		for (int i = 0; i < range2.length; i++) {
			range[j++] = range2[i];
		}
		
		return range;
	}
	
	private void createStreetBets(List<ClickableWager> clickableWagers, 
			String[][] numbers) {
		int x = totalMargin + segmentWidth + quarterWidth;
		int y = totalMargin + 3 * segmentWidth - quarterWidth;
		for (int index = 0; index < numbers[2].length; index++) {
			Rectangle bounds = new Rectangle(x, y, halfWidth, halfWidth);
			int start = Integer.valueOf(numbers[2][index]);
			String[] range = rouletteWheel.getNumberRange(start, start + 2);
			clickableWagers.add(new ClickableWager(bounds, WagerType.STREET_BET, range));
			x += segmentWidth;
		}
	}
	
	private void createColumnBets(List<ClickableWager> clickableWagers, 
			String[][] numbers) {
		int x = totalMargin + 13 * segmentWidth;
		int y = totalMargin;
		Rectangle bounds = new Rectangle(x, y, segmentWidth, segmentWidth);
		clickableWagers.add(new ClickableWager(bounds, WagerType.COLUMN_BET, 
				numbers[0]));
		
		y += segmentWidth;
		bounds = new Rectangle(x, y, segmentWidth, segmentWidth);
		clickableWagers.add(new ClickableWager(bounds, WagerType.COLUMN_BET, 
				numbers[1]));
		
		y += segmentWidth;
		bounds = new Rectangle(x, y, segmentWidth, segmentWidth);
		clickableWagers.add(new ClickableWager(bounds, WagerType.COLUMN_BET, 
				numbers[2]));
	}
	
	private void createHorizontalDoubleBets(List<ClickableWager> clickableWagers, 
			String[][] numbers) {
		int x = totalMargin + segmentWidth + quarterWidth;
		int y = totalMargin + segmentWidth - quarterWidth;
		testDoubleRow(clickableWagers, numbers, x, y, 0, numbers[0].length, -1);
		y += segmentWidth;
		testDoubleRow(clickableWagers, numbers, x, y, 1, numbers[1].length, -1);
	}
	
	private void createVerticalDoubleBets(List<ClickableWager> clickableWagers, 
			String[][] numbers) {
		int x = totalMargin + 2 * segmentWidth - quarterWidth;
		int y = totalMargin + quarterWidth;
		testDoubleRow(clickableWagers, numbers, x, y, 0, numbers[0].length - 1, 3);
		y += segmentWidth;
		testDoubleRow(clickableWagers, numbers, x, y, 1, numbers[1].length - 1, 3);
		y += segmentWidth;
		testDoubleRow(clickableWagers, numbers, x, y, 2, numbers[2].length - 1, 3);
	}
	
	private void testDoubleRow(List<ClickableWager> clickableWagers,
			String[][] numbers, int x, int y, int level, int length, 
			int increment) {
		for (int index = 0; index < length; index++) {
			Rectangle bounds = new Rectangle(x, y, halfWidth, halfWidth);
			int start = Integer.valueOf(numbers[level][index]);
			int next = start + increment;
			String[] range = { Integer.toString(start), Integer.toString(next) };
			clickableWagers.add(new ClickableWager(bounds, WagerType.SPLIT_BET, 
					range));
			x += segmentWidth;
		}
	}
	
	private void createOutsideBets(List<ClickableWager> clickableWagers) {
		int x = totalMargin + segmentWidth;
		int y = totalMargin + 3 * segmentWidth + quarterWidth;
		int extraWidth = 4 * segmentWidth;
		Rectangle bounds = new Rectangle(x, y, extraWidth, halfWidth);
		clickableWagers.add(new ClickableWager(bounds, WagerType.FIRST_12, 
				rouletteWheel.getNumberRange(1, 12)));
		
		x += extraWidth;
		bounds = new Rectangle(x, y, extraWidth, halfWidth);
		clickableWagers.add(new ClickableWager(bounds, WagerType.SECOND_12, 
				rouletteWheel.getNumberRange(13, 24)));
		
		x += extraWidth;
		bounds = new Rectangle(x, y, extraWidth, halfWidth);
		clickableWagers.add(new ClickableWager(bounds, WagerType.THIRD_12, 
				rouletteWheel.getNumberRange(25, 36)));
		
		x = totalMargin + segmentWidth;
		y = totalMargin + 4 * segmentWidth;
		extraWidth = 2 * segmentWidth;
		bounds = new Rectangle(x, y, extraWidth, segmentWidth);
		clickableWagers.add(new ClickableWager(bounds, WagerType.FIRST_18, 
				rouletteWheel.getNumberRange(1, 18)));
		
		x += extraWidth;
		bounds = new Rectangle(x, y, extraWidth, segmentWidth);
		clickableWagers.add(new ClickableWager(bounds, WagerType.EVEN, 
				rouletteWheel.getEvenNumbers()));
		
		x += extraWidth;
		bounds = new Rectangle(x, y, extraWidth, segmentWidth);
		clickableWagers.add(new ClickableWager(bounds, WagerType.RED, 
				rouletteWheel.getRedNumbers()));
		
		x += extraWidth;
		bounds = new Rectangle(x, y, extraWidth, segmentWidth);
		clickableWagers.add(new ClickableWager(bounds, WagerType.BLACK, 
				rouletteWheel.getBlackNumbers()));
		
		x += extraWidth;
		bounds = new Rectangle(x, y, extraWidth, segmentWidth);
		clickableWagers.add(new ClickableWager(bounds, WagerType.ODD, 
				rouletteWheel.getOddNumbers()));
		
		x += extraWidth;
		bounds = new Rectangle(x, y, extraWidth, segmentWidth);
		clickableWagers.add(new ClickableWager(bounds, WagerType.LAST_18,
				rouletteWheel.getNumberRange(19, 36)));
	}
	
	public ClickableWager containsClickableWager(Point point) {
//		System.out.println("Actual point: " + point);
		for (ClickableWager clickableWager : clickableWagers) {
			Rectangle bounds = clickableWager.getBounds();
			if (bounds.contains(point)) {
				return clickableWager;
			}
		}
			
		return null;
	}

}
