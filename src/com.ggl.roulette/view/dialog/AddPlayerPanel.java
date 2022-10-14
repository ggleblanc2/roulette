package com.ggl.roulette.view.dialog;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;

import com.ggl.roulette.model.ChipValue;
import com.ggl.roulette.model.RouletteChip;
import com.ggl.roulette.model.RouletteModel;

public class AddPlayerPanel {
	
	private final RouletteModel model;
	
	private final JPanel panel;
	
	private DefaultComboBoxModel<RouletteChip> imageComboBoxModel;
	
	private JComboBox<ChipValue> chipValueComboBox;
	private JComboBox<RouletteChip> chipImageComboBox;
	
	private JLabel messageLabel;
	
	private JTextField buyInAmountField;
	private JTextField nameField;

	public AddPlayerPanel(RouletteModel model) {
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
		JLabel label = new JLabel("Player Join");
		label.setFont(titleFont);
		panel.add(label, gbc);
		
		gbc.gridwidth = 1;
		gbc.gridy++;
		label = new JLabel("Name:");
		panel.add(label, gbc);
		
		gbc.gridx++;
		nameField = new JTextField(20);
		String text = "Player " + (model.getPlayers().size() + 1);
		nameField.setText(text);
		nameField.setSelectionStart(0);
		nameField.setSelectionEnd(text.length());
		panel.add(nameField, gbc);
		
		gbc.gridx = 0;
		gbc.gridy++;
		label = new JLabel("Chip Color:");
		panel.add(label, gbc);
		
		gbc.gridx++;
		imageComboBoxModel = new DefaultComboBoxModel<>();
		imageComboBoxModel.addAll(model.getRouletteChips().getRouletteChips());
		chipImageComboBox = new JComboBox<>(imageComboBoxModel);
		chipImageComboBox.setSelectedIndex(0);
		ChipCellRenderer renderer = new ChipCellRenderer();
		chipImageComboBox.setRenderer(renderer);
		panel.add(chipImageComboBox, gbc);
		
		gbc.gridx = 0;
		gbc.gridy++;
		label = new JLabel("Chip Value:");
		panel.add(label, gbc);
		
		gbc.gridx++;
		DefaultComboBoxModel<ChipValue> valueComboBoxModel = new DefaultComboBoxModel<>();
		valueComboBoxModel.addAll(model.getChipValues().getValues());
		chipValueComboBox = new JComboBox<>(valueComboBoxModel);
		chipValueComboBox.setSelectedIndex(0);
		panel.add(chipValueComboBox, gbc);
		
		gbc.gridx = 0;
		gbc.gridy++;
		label = new JLabel("Buy In Amount:");
		panel.add(label, gbc);
		
		gbc.gridx++;
		buyInAmountField = new JTextField(20);
		buyInAmountField.setHorizontalAlignment(JTextField.TRAILING);
		panel.add(buyInAmountField, gbc);
		
		gbc.gridwidth = 2;
		gbc.gridx = 0;
		gbc.gridy++;
		messageLabel = new JLabel(" ");
		messageLabel.setForeground(Color.RED);
		panel.add(messageLabel, gbc);
		
		return panel;
	}

	public JPanel getPanel() {
		return panel;
	}
	
	public JComboBox<ChipValue> getChipValueComboBox() {
		return chipValueComboBox;
	}

	public JComboBox<RouletteChip> getChipImageComboBox() {
		return chipImageComboBox;
	}

	public JTextField getBuyInAmountField() {
		return buyInAmountField;
	}

	public JTextField getNameField() {
		return nameField;
	}

	public JLabel getMessageLabel() {
		return messageLabel;
	}

	private class ChipCellRenderer implements ListCellRenderer<RouletteChip> {
		
		private JLabel label = new JLabel();

		@Override
		public Component getListCellRendererComponent(JList<? extends RouletteChip> list, 
				RouletteChip value, int index,
				boolean isSelected, boolean cellHasFocus) {
			label.setIcon(new ImageIcon(value.getChipImage()));
			return label;
		}

	}

}
