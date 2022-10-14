package com.ggl.roulette.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTable;

import com.ggl.roulette.model.RouletteModel;

public class ControlPanel {
	
	private final CallBoardPanel callBoardPanel;
	
	private final MenuPanel menuPanel;
	
	private final PlayerPanel playerPanel;
	
//	private final RouletteFrame frame;
	
//	private final RouletteModel model;
	
	private final JPanel panel;

	public ControlPanel(RouletteFrame frame, RouletteModel model) {
//		this.frame = frame;
//		this.model = model;
		this.callBoardPanel = new CallBoardPanel(model);
		this.menuPanel = new MenuPanel(frame, model);
		this.playerPanel = new PlayerPanel(frame, model);
		this.panel = createControlPanel();
	}
	
	private JPanel createControlPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		
		JPanel innerPanel = new JPanel(new FlowLayout(FlowLayout.LEADING, 5, 5));
		innerPanel.add(callBoardPanel.getPanel());
		innerPanel.add(playerPanel.getPanel());
//		innerPanel.add(bettingPanel.getPanel());
		
		panel.add(innerPanel, BorderLayout.BEFORE_LINE_BEGINS);
		panel.add(menuPanel.getPanel(), BorderLayout.AFTER_LINE_ENDS);
		return panel;
	}

	public JPanel getPanel() {
		return panel;
	}

	public void addCall(String numberString, Color backgroundColor, boolean isBlack) {
		callBoardPanel.addCall(numberString, backgroundColor, isBlack);
	}
	
	public void updatePlayerPanel() {
		playerPanel.updateTableModel();
	}
	
	public JTable getPlayerTable() {
		return playerPanel.getTable();
	}

}
