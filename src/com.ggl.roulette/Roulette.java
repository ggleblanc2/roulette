package com.ggl.roulette;

import javax.swing.SwingUtilities;

import com.ggl.roulette.model.RouletteModel;
import com.ggl.roulette.view.RouletteFrame;

public class Roulette implements Runnable {
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Roulette());
	}

	@Override
	public void run() {
		new RouletteFrame(new RouletteModel());
	}

}
