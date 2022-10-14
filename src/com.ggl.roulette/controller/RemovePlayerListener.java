package com.ggl.roulette.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.ggl.roulette.model.Player;
import com.ggl.roulette.model.RouletteModel;
import com.ggl.roulette.view.RouletteFrame;
import com.ggl.roulette.view.dialog.RemovePlayerDialog;

public class RemovePlayerListener implements ActionListener {
	
	private final RouletteFrame frame;
	
	private final RouletteModel model;
	
	private final RemovePlayerDialog dialog;

	public RemovePlayerListener(RouletteFrame frame, RouletteModel model, 
			RemovePlayerDialog dialog) {
		this.frame = frame;
		this.model = model;
		this.dialog = dialog;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		Player player = model.getSelectedPlayer();
		model.setSelectedPlayer(null);
		model.removePlayer(player);
		frame.updatePlayerPanel();
		dialog.dispose();
	}

}
