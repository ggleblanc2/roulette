package com.ggl.roulette.controller;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.ggl.roulette.model.Player;
import com.ggl.roulette.model.RouletteModel;
import com.ggl.roulette.view.RouletteFrame;

public class PlayerSelectionListener implements ListSelectionListener {
	
	private final RouletteFrame frame;
	
	private final RouletteModel model;
	
	public PlayerSelectionListener(RouletteFrame frame, RouletteModel model) {
		this.frame = frame;
		this.model = model;
	}

	@Override
	public void valueChanged(ListSelectionEvent event) {
		ListSelectionModel lsm = (ListSelectionModel) event.getSource();
		JTable table = frame.getPlayerTable();
		if (!event.getValueIsAdjusting()) {
			int row = lsm.getMinSelectionIndex();
			if (table.isRowSelected(row)) {
				String name = (String) table.getValueAt(row, 0);
				Player player = model.getPlayer(name);
				model.setSelectedPlayer(player);
			} else {
				model.setSelectedPlayer(null);
			}
		}
	}

}
