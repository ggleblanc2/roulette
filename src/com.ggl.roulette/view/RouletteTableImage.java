package com.ggl.roulette.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import com.ggl.roulette.model.RouletteTableDrawingConstants;
import com.ggl.roulette.model.RouletteWheel;

public class RouletteTableImage {
	
	private final int width, height, segmentWidth, margin, lineMargin;
	
	private final BufferedImage image;
	
	private final Color greenColor;
	
	private final RouletteWheel rouletteWheel;
	
	public RouletteTableImage() {
		RouletteTableDrawingConstants rtdc = new RouletteTableDrawingConstants(); 
		this.margin = rtdc.getMargin();
		this.lineMargin = rtdc.getLineMargin();
		this.segmentWidth = rtdc.getSegmentWidth();
		int padding = lineMargin + lineMargin + margin + margin;
		this.width = 14 * segmentWidth + padding;
		this.height = 5 * segmentWidth + padding;
		this.greenColor = new Color(38, 160, 145);
		this.rouletteWheel = new RouletteWheel();
		this.image = createRouletteTable();
	}

	private BufferedImage createRouletteTable() {
		BufferedImage image = new BufferedImage(height, width, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = (Graphics2D) image.getGraphics();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, 
				RenderingHints.VALUE_FRACTIONALMETRICS_ON);
		g2d.setColor(greenColor);
		g2d.fillRect(0, 0, image.getWidth(), image.getHeight());
		
		drawNumbers(g2d);
		drawHorizontalLines(g2d);
		drawVertialLines(g2d);
		
		g2d.dispose();
		
		BufferedImage rotatedImage = rotateImage(image);
		g2d = (Graphics2D) rotatedImage.getGraphics();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, 
				RenderingHints.VALUE_FRACTIONALMETRICS_ON);
		
		drawOutsideBets(g2d);
		
		g2d.dispose();
		
		return rotatedImage;
		
//		return image;
	}
	
	private void drawNumbers(Graphics2D g2d) {
		Font font = new Font(Font.SANS_SERIF, Font.BOLD, 36);
		g2d.setFont(font);
		
		String numberString = "00";
		int zeroWidth = 3 * segmentWidth / 2;
		int x = 2 * segmentWidth + margin + lineMargin;
		int y = margin + lineMargin;
		drawNumber(g2d, numberString, 0, x, y, zeroWidth, segmentWidth);
		
		numberString = "0";
		zeroWidth = 3 * segmentWidth / 2;
		x = 2 * segmentWidth + margin + lineMargin + zeroWidth;
		y = margin + lineMargin;
		drawNumber(g2d, numberString, 0, x, y, zeroWidth, segmentWidth);
		
		int number = 1;
		y = segmentWidth + margin + lineMargin;
		for (int row = 0; row < 12; row++) {
			x = segmentWidth + margin + lineMargin;
			for (int column = 0; column < 3; column++) {
				x += segmentWidth;
				numberString = Integer.toString(number);
				drawNumber(g2d, numberString, number++, x, y, segmentWidth, segmentWidth);
			}
			y += segmentWidth;
		}
		
		String text = "3 for 1";
		font = new Font(Font.SANS_SERIF, Font.BOLD, 16);
		g2d.setFont(font);
		x = segmentWidth + margin + lineMargin;
		for (int column = 0; column < 3; column++) {
			x += segmentWidth;
			drawText(g2d, text, x, y, segmentWidth, segmentWidth);
		}
	}
	
	private void drawNumber(Graphics2D g2d, String numberString, int number, 
			int x, int y, int segmentWidth, int segmentHeight) {
		FontMetrics metrics = g2d.getFontMetrics(g2d.getFont());
		int ascent = metrics.getAscent();
		
		int width = metrics.stringWidth(numberString);
		
		g2d.setColor(rouletteWheel.getBackgroundColor(number));
		g2d.fillRect(x, y, segmentWidth, segmentHeight);
		
		x += (segmentWidth - width) / 2;
		y += (segmentHeight - ascent) / 2 + ascent;
		
		g2d.setColor(Color.WHITE);
		g2d.drawString(numberString, x, y);
	}
	
	private void drawOutsideBets(Graphics2D g2d) {
		// Draw text for the 12 number bets
		Font font = new Font(Font.SANS_SERIF, Font.BOLD, 32);
		g2d.setFont(font);
		
		int x = segmentWidth + margin + lineMargin;
		int y = 3 * segmentWidth + margin + lineMargin;
		int extraWidth = 4 * segmentWidth;	
		drawText(g2d, "1st 12", x, y, extraWidth, segmentWidth);
		x += extraWidth;
		drawText(g2d, "2nd 12", x, y, extraWidth, segmentWidth);
		x += extraWidth;
		drawText(g2d, "3rd 12", x, y, extraWidth, segmentWidth);
		
		// Draw text for the even odds bets
		font = new Font(Font.SANS_SERIF, Font.BOLD, 24);
		g2d.setFont(font);
		
		x = segmentWidth + margin + lineMargin;
		y = 4 * segmentWidth + margin + lineMargin;
		extraWidth = 2 * segmentWidth;
		drawText(g2d, "1 to 18", x, y, extraWidth, segmentWidth);
		x += extraWidth;
		drawText(g2d, "EVEN", x, y, extraWidth, segmentWidth);
		x += 3 * extraWidth;
		drawText(g2d, "ODD", x, y, extraWidth, segmentWidth);
		x += extraWidth;
		drawText(g2d, "19 to 36", x, y, extraWidth, segmentWidth);
		
		// Draw colors for the red / black bets
		int doubleMargin = 2 * lineMargin;
		x = 5 * segmentWidth + margin + doubleMargin;
		y = 4 * segmentWidth + margin + doubleMargin;
		drawDiamond(g2d, rouletteWheel.getBackgroundColor(1), x, y, 
				extraWidth - doubleMargin, segmentWidth - doubleMargin);
		x += 2 * segmentWidth;
		drawDiamond(g2d, rouletteWheel.getBackgroundColor(2), x, y, 
				extraWidth - doubleMargin, segmentWidth - doubleMargin);
	}
	
	private void drawText(Graphics2D g2d, String text,  
			int x, int y, int segmentWidth, int segmentHeight) {
		FontMetrics metrics = g2d.getFontMetrics(g2d.getFont());
		int ascent = metrics.getAscent();
		int width = metrics.stringWidth(text);
		
		x += (segmentWidth - width) / 2;
		y += (segmentHeight - ascent) / 2 + ascent;
		
		g2d.setColor(Color.WHITE);
		g2d.drawString(text, x, y);
	}
	
	private void drawDiamond(Graphics2D g2d, Color color, int x, int y, int width, int height) {
		Polygon polygon = new Polygon();
		polygon.addPoint(x + width / 2, y);
		polygon.addPoint(x + width, y + height / 2);
		polygon.addPoint(x + width / 2 , y + height);
		polygon.addPoint(x, y + height / 2);
		
		g2d.setColor(color);
		g2d.fillPolygon(polygon);
	}
	
	private void drawHorizontalLines(Graphics2D g2d) {
		g2d.setStroke(new BasicStroke(5f));
		g2d.setColor(Color.WHITE);
		
		int y = margin + lineMargin;
		drawHorizontalLineSegment(g2d, 2, 5, y);
		y = drawVerticalBlock(g2d, y);
		y = drawVerticalBlock(g2d, y);
		y = drawVerticalBlock(g2d, y);
		drawHorizontalLineSegment(g2d, 2, 5, y);
		y += segmentWidth;
		drawHorizontalLineSegment(g2d, 0, 5, y);
		y += segmentWidth;
		drawHorizontalLineSegment(g2d, 2, 5, y);
	}

	private int drawVerticalBlock(Graphics2D g2d, int y) {
		y += segmentWidth;
		drawHorizontalLineSegment(g2d, 0, 5, y);
		y += segmentWidth;
		drawHorizontalLineSegment(g2d, 2, 5, y);
		y += segmentWidth;
		drawHorizontalLineSegment(g2d, 0, 1, y);
		drawHorizontalLineSegment(g2d, 2, 5, y);
		y += segmentWidth;
		drawHorizontalLineSegment(g2d, 2, 5, y);
		return y;
	}
	
	private void drawHorizontalLineSegment(Graphics2D g2d, int c1, int c2, int y) {
		int x1 = c1 * segmentWidth + margin + lineMargin;
		int x2 = c2 * segmentWidth + margin + lineMargin;
		g2d.drawLine(x1, y, x2, y);
	}
	
	private void drawVertialLines(Graphics2D g2d) {
		g2d.setStroke(new BasicStroke(5f));
		g2d.setColor(Color.WHITE);
		
		// Draw longest lines
		int x = 2 * segmentWidth + margin + lineMargin;
		drawVerticalLineSegment(g2d, x, 0, 14);
		x += 3 * segmentWidth;
		drawVerticalLineSegment(g2d, x, 0, 14);
		
		// Draw shorter lines
		x = margin + lineMargin;
		drawVerticalLineSegment(g2d, x, 1, 13);
		x += segmentWidth;
		drawVerticalLineSegment(g2d, x, 1, 13);
		x += 2 * segmentWidth;
		drawVerticalLineSegment(g2d, x, 1, 14);
		x += segmentWidth;
		drawVerticalLineSegment(g2d, x, 1, 14);
		
		// Draw 0 / 00 separator
		x = 7 * segmentWidth / 2 + margin + lineMargin;
		drawVerticalLineSegment(g2d, x, 0, 1);
	}
	
	private void drawVerticalLineSegment(Graphics2D g2d, int x, int c1, int c2) {
		int y1 = c1 * segmentWidth + margin + lineMargin;
		int y2 = c2 * segmentWidth + margin + lineMargin;
		g2d.drawLine(x, y1, x, y2);
	}
	
	private BufferedImage rotateImage(BufferedImage image) {
		BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		
		// Rotate image 90 degrees counter-clockwise
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				outputImage.setRGB(j, height - 1 - i, image.getRGB(i, j));
			}
		}
	
		return outputImage;
	}

	public BufferedImage getImage() {
		return image;
	}
	
}
