package com.ggl.roulette.view.dialog;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.ggl.roulette.model.WagerType;

public class BettingPanel {
	
	private final JPanel panel;

	public BettingPanel() {
		this.panel = createBettingPanel();
	}
	
	private JPanel createBettingPanel() {
		JPanel panel = new JPanel(new GridBagLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		Font titleFont = panel.getFont().deriveFont(Font.BOLD, 24);
		Font headerFont = panel.getFont().deriveFont(Font.BOLD, 16);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(5, 5, 5, 25);
		gbc.gridwidth = 2;
		gbc.weightx = 1.0;
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		JLabel label = new JLabel("Betting Odds");
		label.setFont(titleFont);
		panel.add(label, gbc);
		
		gbc.gridwidth = 1;
		gbc.gridy++;
		label = new JLabel("Bet Type");
		label.setFont(headerFont);
		panel.add(label, gbc);
		
		gbc.gridx++;
		label = new JLabel("Odds");
		label.setFont(headerFont);
		panel.add(label, gbc);
		
		for (WagerType wagerType : WagerType.values()) {
			gbc.gridx = 0;
			gbc.gridy++;
			label = new JLabel(wagerType.getDescription());
			panel.add(label, gbc);
			
			gbc.gridx++;
			String text = "" + wagerType.getPayoff() + " for 1";
			label = new JLabel(text);
			panel.add(label, gbc);
		}
		
		return panel;
	}

	public JPanel getPanel() {
		return panel;
	}

}
