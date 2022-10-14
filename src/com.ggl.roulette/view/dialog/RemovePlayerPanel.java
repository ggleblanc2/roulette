package com.ggl.roulette.view.dialog;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.ggl.roulette.model.RouletteModel;
import com.ggl.roulette.view.RouletteFrame;

public class RemovePlayerPanel {
	
	private RouletteModel model;
	
	private JPanel panel;
	
	private JTextField nameField;
	private JTextField cashOutAmountField;

	public RemovePlayerPanel(RouletteFrame frame, RouletteModel model) {
		if (model.getSelectedPlayer() == null) {
			String s = "Select a player to cash out.";
			JOptionPane.showMessageDialog(frame.getFrame(), s, "No Player Selected", 
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		this.model = model;
		this.panel = createMainPanel();
	}
	
	private JPanel createMainPanel() {
		JPanel panel = new JPanel(new GridBagLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		Font titleFont = panel.getFont().deriveFont(Font.BOLD, 24);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.gridwidth = 2;
		gbc.weightx = 1.0;
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		JLabel label = new JLabel("Player Cash Out");
		label.setFont(titleFont);
		panel.add(label, gbc);
		
		gbc.gridwidth = 1;
		gbc.gridy++;
		label = new JLabel("Name:");
		panel.add(label, gbc);
		
		gbc.gridx++;
		nameField = new JTextField(20);
		nameField.setEditable(false);
		nameField.setText(model.getSelectedPlayer().getName());
		panel.add(nameField, gbc);
		
		gbc.gridx = 0;
		gbc.gridy++;
		label = new JLabel("Cash Out Amount:");
		panel.add(label, gbc);
		
		gbc.gridx++;
		cashOutAmountField = new JTextField(20);
		cashOutAmountField.setEditable(false);
		String text = "$" + String.format("%,.2f", 
				(double) model.getSelectedPlayer().getBalance());
		cashOutAmountField.setText(text);
		panel.add(cashOutAmountField, gbc);
		
		return panel;
	}

	public JPanel getPanel() {
		return panel;
	}

}
