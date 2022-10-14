package com.ggl.roulette.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import com.ggl.roulette.model.ChipValue;
import com.ggl.roulette.model.Player;
import com.ggl.roulette.model.RouletteChip;
import com.ggl.roulette.model.RouletteModel;
import com.ggl.roulette.view.RouletteFrame;
import com.ggl.roulette.view.dialog.AddPlayerDialog;
import com.ggl.roulette.view.dialog.AddPlayerPanel;

public class AddPlayerListener implements ActionListener {
	
	private final AddPlayerDialog dialog;
	
	private final RouletteFrame frame;
	
	private final RouletteModel model;

	public AddPlayerListener(RouletteFrame frame, RouletteModel model, AddPlayerDialog dialog) {
		this.frame = frame;
		this.model = model;
		this.dialog = dialog;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		AddPlayerPanel panel = dialog.getAddPlayerPanel();
		JTextField buyInAmountField = panel.getBuyInAmountField();
		double amount = valueOf(buyInAmountField.getText().trim());
		
		//TODO Minimum buy in of 20 chips
		if (amount == Double.MIN_VALUE) {
			buyInAmountField.setForeground(Color.RED);
			panel.getMessageLabel().setText("The buy in amount must be in even dollars");
			return;
		} else {
			buyInAmountField.setForeground(Color.BLACK);
			panel.getMessageLabel().setText(" ");
		}
		
		JTextField nameField = panel.getNameField();
		String name = panel.getNameField().getText().trim();
		if (model.getPlayer(name) == null) {
			nameField.setForeground(Color.BLACK);
			panel.getMessageLabel().setText(" ");
		} else {
			nameField.setForeground(Color.RED);
			panel.getMessageLabel().setText("You can't have the same name as another player");
			return;
		}
		
		JComboBox<RouletteChip> chipComboBox = panel.getChipImageComboBox();
		
		int buyInAmount = (int) Math.round(amount);
		ChipValue chipValue = (ChipValue) panel.getChipValueComboBox().getSelectedItem();
		RouletteChip rouletteChip = (RouletteChip) chipComboBox.getSelectedItem();
		
		model.removeRouletteChip(rouletteChip);
		
		Player player = new Player(name);
		player.buyIn(buyInAmount, chipValue);
		player.setRouletteChip(rouletteChip);
		model.addPlayer(player);
		frame.updatePlayerPanel();
		
		dialog.dispose();
	}
	
	public double valueOf(String number) {
		try {
			return Double.valueOf(number);
		} catch (NumberFormatException e) {
			return Double.MIN_VALUE;
		}
	}

}
