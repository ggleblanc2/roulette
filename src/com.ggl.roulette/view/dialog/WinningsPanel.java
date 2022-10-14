package com.ggl.roulette.view.dialog;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.ggl.roulette.model.Wager;

public class WinningsPanel {
	
	private final JPanel panel;
	
	private final List<Wager> winnings;
	
	private final String number;
	
	public WinningsPanel(List<Wager> winnings, String number) {
		this.winnings = winnings;
		this.number = number;
		this.panel = createMainPanel();
	}
	
	private JPanel createMainPanel() {
		JPanel panel = new JPanel(new GridBagLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		Font titleFont = panel.getFont().deriveFont(Font.BOLD, 24);
		Font headerFont = panel.getFont().deriveFont(Font.BOLD, 16);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.gridwidth = 3;
		gbc.weightx = 1.0;
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		JLabel label = new JLabel("Winning Bets");
		label.setFont(titleFont);
		panel.add(label, gbc);
		
		gbc.gridy++;
		label = new JLabel("Number: " + number);
		label.setFont(headerFont);
		panel.add(label, gbc);
		
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy++;
		label = new JLabel("Name");
		label.setFont(headerFont);
		panel.add(label, gbc);
		
		gbc.gridx++;
		label = new JLabel("Bet Type");
		label.setFont(headerFont);
		panel.add(label, gbc);
		
		gbc.gridx++;
		label = new JLabel("Odds");
		label.setFont(headerFont);
		panel.add(label, gbc);
		
		for (Wager wager : winnings) {
			gbc.gridx = 0;
			gbc.gridy++;
			label = new JLabel(wager.getPlayer().getName());
			panel.add(label, gbc);
			
			gbc.gridx++;
			label = new JLabel(wager.getWagerType().getDescription());
			panel.add(label, gbc);
			
			gbc.gridx++;
			String text = "" + wager.getWagerType().getPayoff() + " for 1";
			label = new JLabel(text);
			panel.add(label, gbc);
		}
		
		return panel;
	}

	public JPanel getPanel() {
		return panel;
	}

}
