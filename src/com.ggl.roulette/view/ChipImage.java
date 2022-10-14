package com.ggl.roulette.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

public class ChipImage {
	
	private final int radius;
	
	private final BufferedImage chipImage;
	
	private final Color chipColor;
	private final Color inlayColor;

	public ChipImage(Color chipColor, Color inlayColor) {
		this.chipColor = chipColor;
		this.inlayColor = inlayColor;
		this.radius = 21;
		this.chipImage = createChipImage();
	}
	
	private BufferedImage createChipImage() {
		int diameter = radius + radius;
		BufferedImage image = new BufferedImage(diameter, diameter, BufferedImage.TYPE_INT_ARGB);
		
		Graphics2D g2d = (Graphics2D) image.getGraphics();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, 
				RenderingHints.VALUE_FRACTIONALMETRICS_ON);
		g2d.setColor(new Color(255, 255, 255, 0));
		g2d.fillRect(0, 0, image.getWidth(), image.getHeight());
		
		int x = image.getWidth() / 2;
		int y = image.getHeight() / 2;
		g2d.setColor(chipColor);
		g2d.fillOval(x - radius, y - radius, diameter, diameter);
		
		int smallRadius = radius / 3;
		int largeRadius = 2 * radius / 3;
		
		float[] dash1 = { 2f, 0f, 0f, 2f };
		BasicStroke bs = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 
				1.0f, dash1, 2f);
		g2d.setStroke(bs);
		g2d.setColor(inlayColor);
		g2d.drawOval(x - smallRadius, y - smallRadius, 
				smallRadius + smallRadius, smallRadius + smallRadius);
		g2d.drawOval(x - largeRadius, y - largeRadius, 
				largeRadius + largeRadius, largeRadius + largeRadius);
		
		g2d.dispose();
		
		return image;
	}

	public BufferedImage getChipImage() {
		return chipImage;
	}

	public Color getChipColor() {
		return chipColor;
	}
	
}
