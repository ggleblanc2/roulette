package com.ggl.roulette.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;

import com.ggl.roulette.model.RouletteModel;
import com.ggl.roulette.model.WheelSegment;

public class RouletteWheelImage {
	
	private BufferedImage image;
	
	private Color goldColor;
	private Color tanColor;
	
	private WheelSegment[] wheelSegments;
	
	public RouletteWheelImage(RouletteModel model) {
		this.goldColor = new Color(185, 125, 35);
		this.tanColor = new Color(223, 215, 184);
		this.wheelSegments = model.getWheelSegments();
		this.image = createRouletteWheel();
	}

	private BufferedImage createRouletteWheel() {
		BufferedImage image = new BufferedImage(400, 400, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = (Graphics2D) image.getGraphics();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, 
				RenderingHints.VALUE_FRACTIONALMETRICS_ON);
		g2d.setColor(goldColor);
		g2d.fillRect(0, 0, image.getWidth(), image.getHeight());
		
		paintWheelSegments(g2d);
		
		g2d.setColor(goldColor);
		g2d.setStroke(new BasicStroke(5f));
		g2d.fillOval(75, 75, 250, 250);
		
		g2d.setColor(tanColor);
		g2d.setStroke(new BasicStroke(9f));
		g2d.drawOval(5, 5, 388, 388);
		g2d.setStroke(new BasicStroke(5f));
		g2d.drawOval(35, 35, 330, 330);
		g2d.setStroke(new BasicStroke(13f));
		g2d.drawOval(75, 75, 250, 250);

		g2d.dispose();
		
		return image;
	}

	private void paintWheelSegments(Graphics2D g2d) {
		double angleIncrement = 360.0 / wheelSegments.length;
		double angle = 270.0;
		Point lastInnerPoint = null;
		Point lastOuterPoint = null;
		Point originalInnerPoint = null;
		Point originalOuterPoint = null;
		Point centerPoint = new Point(200, 200);
		
		for (int i = 0; i < wheelSegments.length; i++) {
			Point innerPoint = toCartesian(angle, 125.0, centerPoint);
			Point outerPoint = toCartesian(angle, 194.0, centerPoint);
			if (i > 0) {
				paintPolygon(g2d, lastInnerPoint, lastOuterPoint, i, innerPoint, outerPoint);
			} else {
				originalInnerPoint = new Point(innerPoint.x, innerPoint.y);
				originalOuterPoint = new Point(outerPoint.x, outerPoint.y);
			}
			angle += angleIncrement;
			lastInnerPoint = new Point(innerPoint.x, innerPoint.y);
			lastOuterPoint = new Point(outerPoint.x, outerPoint.y);
		}
		
		paintPolygon(g2d, lastInnerPoint, lastOuterPoint, wheelSegments.length, 
				originalInnerPoint, originalOuterPoint);
		
		angle = angleIncrement * 0.5;
		for (int index = 0; index < wheelSegments.length; index++) {
			BufferedImage image = createWheelNumber(wheelSegments[index].getWheelNumber(), 
					wheelSegments[index].getBackgroundColor(), angle);
			double radius = 178.0;
			Point point = toCartesian(270.0 + angle, 
					radius, centerPoint);
			Point correction = wheelSegments[index].getCorrectionDelta();
			g2d.drawImage(image, point.x - correction.x, point.y - correction.y, null);
			angle += angleIncrement;
		}
		
		angle = 270.0;
		g2d.setStroke(new BasicStroke(3f));
		g2d.setColor(tanColor);
		for (int i = 0; i <= wheelSegments.length; i++) {
			Point innerPoint = toCartesian(angle, 125.0, centerPoint);
			Point outerPoint = toCartesian(angle, 195.0, centerPoint);
			g2d.drawLine(innerPoint.x, innerPoint.y, outerPoint.x, outerPoint.y);
			angle += angleIncrement;
		}
	}

	private void paintPolygon(Graphics2D g2d, Point lastInnerPoint, Point lastOuterPoint, 
			int index, Point innerPoint, Point outerPoint) {
		Polygon polygon = new Polygon();
		polygon.addPoint(lastInnerPoint.x, lastInnerPoint.y);
		polygon.addPoint(lastOuterPoint.x, lastOuterPoint.y);
		polygon.addPoint(outerPoint.x, outerPoint.y);
		polygon.addPoint(innerPoint.x, innerPoint.y);
		g2d.setColor(wheelSegments[index - 1].getBackgroundColor());
		g2d.fillPolygon(polygon);
	}
	
	private BufferedImage createWheelNumber(String number, Color backgroundColor, 
			double angle) {
		Font font = new Font(Font.SANS_SERIF, Font.BOLD, 18);

		BufferedImage dummyImage = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = (Graphics2D) dummyImage.getGraphics();
		FontMetrics metrics = g2d.getFontMetrics(font);
		int height = metrics.getAscent();
		int width = metrics.stringWidth(number);
		g2d.dispose();
		
		BufferedImage image = new BufferedImage(width + 2, height + 2,
				BufferedImage.TYPE_INT_RGB);
		g2d = (Graphics2D) image.getGraphics();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, 
				RenderingHints.VALUE_FRACTIONALMETRICS_ON);
		g2d.setColor(backgroundColor);
		g2d.fillRect(0, 0, image.getWidth(), image.getHeight());
		g2d.setColor(Color.WHITE);
		g2d.setFont(font);
		g2d.drawString(number, 2, image.getHeight() - 5);
		g2d.dispose();
		
		AffineTransform xfrm = new AffineTransform();
        xfrm = AffineTransform.getRotateInstance(Math.toRadians(angle), 
        		image.getWidth() / 2, image.getHeight() / 2);
        BufferedImageOp op = new AffineTransformOp(xfrm,
        		AffineTransformOp.TYPE_BILINEAR);
        BufferedImage outputImage = op.filter(image, null);
		
        return outputImage;
	}
	
	private Point toCartesian(double angle, double radius, Point centerPoint) {
		double theta = Math.toRadians(angle);
		int x = (int) Math.round(Math.cos(theta) * radius) + centerPoint.x;
		int y = (int) Math.round(Math.sin(theta) * radius) + centerPoint.y;
		return new Point(x, y);
	}
	
	public BufferedImage getImage() {
		return image;
	}

}
