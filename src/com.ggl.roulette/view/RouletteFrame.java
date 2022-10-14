package com.ggl.roulette.view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JTable;

import com.ggl.roulette.model.RouletteModel;
import com.ggl.roulette.model.WheelSegment;

public class RouletteFrame {
	
	private final ControlPanel controlPanel;
	
//	private final RouletteModel model;
	
	private final RouletteTableWheelPanel rouletteTableWheelPanel;
	
	private final JFrame frame;

	public RouletteFrame(RouletteModel model) {
//		this.model = model;
		this.controlPanel = new ControlPanel(this, model);
		this.rouletteTableWheelPanel = new RouletteTableWheelPanel(this, model);
		this.frame = createAndShowGUI();
	}
	
	private JFrame createAndShowGUI() {
		JFrame frame = new JFrame("Roulette");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.add(rouletteTableWheelPanel.getPanel(), BorderLayout.BEFORE_FIRST_LINE);
		frame.add(controlPanel.getPanel(), BorderLayout.AFTER_LAST_LINE);
		
		frame.pack();
		frame.setResizable(false);
		frame.setLocationByPlatform(true);
		frame.setVisible(true);
		
		System.out.println(frame.getSize());
		
		return frame;
	}
	
	public void addCall(String numberString, Color backgroundColor, boolean isBlack) {
		controlPanel.addCall(numberString, backgroundColor, isBlack);
	}
	
	public void updatePlayerPanel() {
		controlPanel.updatePlayerPanel();
	}
	
	public JTable getPlayerTable() {
		return controlPanel.getPlayerTable();
	}
	
	public void initializeWheel() {
		rouletteTableWheelPanel.initializeWheel();
	}
	
	public void repositionWheel(WheelSegment wheelSegment, int index) {
		rouletteTableWheelPanel.repositionWheel(wheelSegment, index);
	}
	
	public void rotateWheel(double angle) {
		rouletteTableWheelPanel.rotateWheel(angle);
	}
	
	public void repaintRouletteTable() {
		rouletteTableWheelPanel.repaintRouletteTable();
	}

	public JFrame getFrame() {
		return frame;
	}

}
