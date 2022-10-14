package com.ggl.roulette.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import com.ggl.roulette.controller.ChipListener;
import com.ggl.roulette.model.Player;
import com.ggl.roulette.model.RouletteModel;
import com.ggl.roulette.model.RouletteTableDrawingConstants;
import com.ggl.roulette.model.Wager;

public class RouletteTablePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private final int margin;

//	private final RouletteFrame frame;
	
	private final RouletteModel model;
	
	private final RouletteTableImage image;

	public RouletteTablePanel(RouletteFrame frame, RouletteModel model, 
			RouletteTableImage image) {
//		this.frame = frame;
		this.model = model;
		this.image = image;
		RouletteTableDrawingConstants rtdc = new RouletteTableDrawingConstants(); 
		this.margin = rtdc.getImageMargin();
		this.setPreferredSize(new Dimension(image.getImage().getWidth() + margin + margin, 
				image.getImage().getHeight() + margin + margin));
		this.addMouseListener(new ChipListener(frame, model));
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image.getImage(), margin, margin, this);
		
		for (Wager wager : model.getWagers()) {
			Player player = wager.getPlayer();
			BufferedImage chipImage = player.getChipImage();
			Point point = wager.getBoardLocation();
			int x = point.x - chipImage.getWidth() / 2;
			int y = point.y - chipImage.getHeight() / 2;
			g.drawImage(chipImage, x, y, this);
		}
	}
	
}
