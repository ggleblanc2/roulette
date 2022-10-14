package com.ggl.roulette.model;

import java.awt.Color;
import java.awt.image.BufferedImage;

import com.ggl.roulette.view.ChipImage;

public class RouletteChip {
	
	private final BufferedImage chipImage;
	
	private final Color chipColor;
	private final Color accentColor;
	
	public RouletteChip(Color chipColor, Color accentColor) {
		this.chipColor = chipColor;
		this.accentColor = accentColor;
		this.chipImage = new ChipImage(chipColor, accentColor).getChipImage();
	}

	public BufferedImage getChipImage() {
		return chipImage;
	}

	public Color getChipColor() {
		return chipColor;
	}

	public Color getAccentColor() {
		return accentColor;
	}

}
