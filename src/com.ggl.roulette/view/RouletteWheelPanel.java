package com.ggl.roulette.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import com.ggl.roulette.model.RouletteModel;
import com.ggl.roulette.model.WheelSegment;

public class RouletteWheelPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private double angleAccumulator;
	
	private final int margin;
	
	private BufferedImage originalImage;
	private BufferedImage rotatedImage;
	
	private final Color goldColor;
	
//	private final RouletteFrame frame;
	
//	private final RouletteModel model;

	public RouletteWheelPanel(RouletteFrame frame, RouletteModel model, 
			RouletteWheelImage image) {
//		this.frame = frame;
//		this.model = model;
		this.margin = 10;
		this.goldColor = new Color(185, 125, 35);
		this.angleAccumulator = 0.0;
		this.originalImage = image.getImage();
		this.rotatedImage = image.getImage();
		this.setPreferredSize(new Dimension(image.getImage().getWidth() + margin + margin, 
				image.getImage().getHeight() + margin + margin));
	}
	
	public void rotateWheel(double angle) {
		this.angleAccumulator += angle;
		rotateWheelPrivate(rotatedImage, angle);
	}
	
	public void repositionWheel(WheelSegment wheelSegment, int index) {
		BufferedImage copyImage = new BufferedImage(originalImage.getWidth(),
				originalImage.getHeight(), originalImage.getType());
		Graphics2D g2d = (Graphics2D) copyImage.getGraphics();
		g2d.drawImage(originalImage, 0, 0, this);
		double segmentAngle = 360.0 / 38.0;
		double angle = index * segmentAngle + segmentAngle * 0.5  - 90.0;
		Point point = toCartesian(angle, 140.0, new Point(copyImage.getWidth() / 2, 
				copyImage.getHeight() / 2));
		g2d.setColor(Color.WHITE);
		g2d.fillOval(point.x - 8, point.y - 8, 16, 16);
		
		this.angleAccumulator %= 360.0;
		rotateWheelPrivate(copyImage, angleAccumulator);
	}
	
	public void initalizeWheel() {
		this.angleAccumulator %= 360.0;
		rotateWheelPrivate(originalImage, angleAccumulator);
	}
	
	private Point toCartesian(double angle, double radius, Point centerPoint) {
		double theta = Math.toRadians(angle);
		int x = (int) Math.round(Math.cos(theta) * radius) + centerPoint.x;
		int y = (int) Math.round(Math.sin(theta) * radius) + centerPoint.y;
		return new Point(x, y);
	}

	private void rotateWheelPrivate(BufferedImage bufferedImage, double angle) {
		double theta = Math.toRadians(angle);
		double locationX = bufferedImage.getWidth() / 2;
		double locationY = bufferedImage.getHeight() / 2;
		AffineTransform tx = AffineTransform.getRotateInstance(theta, locationX, locationY);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BICUBIC);
		BufferedImage outputImage = new BufferedImage(bufferedImage.getWidth(),
				bufferedImage.getHeight(), bufferedImage.getType());
		Graphics g = outputImage.getGraphics();
		g.setColor(goldColor);
		g.fillRect(0, 0, outputImage.getWidth(), outputImage.getHeight());
		g.dispose();
		
		op.filter(bufferedImage, outputImage);
		this.rotatedImage = outputImage;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(rotatedImage, margin, margin, this);
	}

}
