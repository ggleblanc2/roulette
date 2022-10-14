package com.ggl.roulette.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.ggl.roulette.controller.WheelSpinListener;
import com.ggl.roulette.model.RouletteModel;
import com.ggl.roulette.view.dialog.AddPlayerDialog;
import com.ggl.roulette.view.dialog.BettingDialog;
import com.ggl.roulette.view.dialog.InstructionsDialog;
import com.ggl.roulette.view.dialog.RemovePlayerDialog;

public class MenuPanel {
	
	private final RouletteFrame frame;
	
	private final RouletteModel model;
	
	private final JPanel panel;

	public MenuPanel(RouletteFrame frame, RouletteModel model) {
		this.frame = frame;
		this.model = model;
		this.panel = createMenuPanel();
	}
	
	private JPanel createMenuPanel() {
		JPanel panel = new JPanel(new GridBagLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(5, 5, 25, 5);
		gbc.gridwidth = 1;
		gbc.weightx = 1.0;
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		JButton button = new JButton("Spin Wheel");
		button.addActionListener(new WheelSpinListener(frame, model));
		panel.add(button, gbc);
		
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.gridy++;
		button = new JButton("Player Join");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (model.getPlayers().size() >= 8) {
					JOptionPane.showMessageDialog(frame.getFrame(),
						    "A maximum of 8 players may play",
						    "Maximum Players Error",
						    JOptionPane.ERROR_MESSAGE);
				} else {
					new AddPlayerDialog(frame, model, "Player Join");
				}
			}
		});
		panel.add(button, gbc);
		
		gbc.insets = new Insets(5, 5, 25, 5);
		gbc.gridy++;
		button = new JButton("Player Cash Out");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				new RemovePlayerDialog(frame, model, "Player Cash Out");
			}
		});
		panel.add(button, gbc);
		
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.gridy++;
		button = new JButton("Instructions");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new InstructionsDialog(frame, "Instructions");
			}
		});
		panel.add(button, gbc);
		
		gbc.gridy++;
		button = new JButton("Betting Odds");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new BettingDialog(frame, "Betting Odds");
			}
		});
		panel.add(button, gbc);
		
		return panel;
	}

	public JPanel getPanel() {
		return panel;
	}

}
