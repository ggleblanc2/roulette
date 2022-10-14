package com.ggl.roulette.view;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import com.ggl.roulette.model.RouletteModel;
import com.ggl.roulette.model.WheelSegment;

public class RouletteTableWheelPanel {
	
//	private final RouletteFrame frame;
	
//	private final RouletteModel model;
	
	private final JPanel panel;
	
	private final RouletteTableImage rouletteTableImage;
	
	private final RouletteTablePanel rouletteTablePanel;
	
	private final RouletteWheelImage rouletteWheelImage;
	
	private final RouletteWheelPanel rouletteWheelPanel;

	public RouletteTableWheelPanel(RouletteFrame frame, RouletteModel model) {
//		this.frame = frame;
//		this.model = model;
		this.rouletteTableImage = new RouletteTableImage();
		this.rouletteTablePanel = new RouletteTablePanel(frame, model, rouletteTableImage);
		this.rouletteWheelImage = new RouletteWheelImage(model);
		this.rouletteWheelPanel = new RouletteWheelPanel(frame, model, rouletteWheelImage);
		this.panel = createSpinControlPanel();
	}
	
	private JPanel createSpinControlPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(rouletteWheelPanel, BorderLayout.BEFORE_LINE_BEGINS);
		panel.add(rouletteTablePanel, BorderLayout.AFTER_LINE_ENDS);
		
		return panel;
	}
	
	public void initializeWheel() {
		rouletteWheelPanel.initalizeWheel();
	}
	
	public void repositionWheel(WheelSegment wheelSegment, int index) {
		rouletteWheelPanel.repositionWheel(wheelSegment, index);
		rouletteWheelPanel.repaint();
	}
	
	public void rotateWheel(double angle) {
		rouletteWheelPanel.rotateWheel(angle);
		rouletteWheelPanel.repaint();
	}
	
	public void repaintRouletteTable() {
		rouletteTablePanel.repaint();
	}

	public JPanel getPanel() {
		return panel;
	}

}
