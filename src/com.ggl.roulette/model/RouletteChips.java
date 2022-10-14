package com.ggl.roulette.model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class RouletteChips {
	
	private final List<RouletteChip> rouletteChips;
	
	public RouletteChips() {
		this.rouletteChips = generateChips();
	}
	
	private List<RouletteChip> generateChips() {
		List<RouletteChip> rouletteChips = new ArrayList<>();
		//TODO Temporarily commented out so I don't have to enter my information
		// over and over while testing
		rouletteChips.add(new RouletteChip(Color.BLUE, Color.YELLOW));
		rouletteChips.add(new RouletteChip(Color.ORANGE, Color.BLACK));
		rouletteChips.add(new RouletteChip(Color.CYAN, Color.BLUE));
		rouletteChips.add(new RouletteChip(Color.LIGHT_GRAY, Color.BLACK));
		rouletteChips.add(new RouletteChip(Color.BLACK, Color.WHITE));
		rouletteChips.add(new RouletteChip(Color.PINK, Color.BLACK));
		rouletteChips.add(new RouletteChip(Color.YELLOW, Color.BLUE));
		rouletteChips.add(new RouletteChip(Color.GREEN, Color.BLUE));
		
		return rouletteChips;
	}
	
	public void addRouletteChip(RouletteChip rouletteChip) {
		this.rouletteChips.add(rouletteChip);
	}
	
	public void removeRouletteChip(RouletteChip rouletteChip) {
		this.rouletteChips.remove(rouletteChip);
	}

	public List<RouletteChip> getRouletteChips() {
		return rouletteChips;
	}

}
