package com.ggl.roulette.controller;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import com.ggl.roulette.model.ClickableWager;
import com.ggl.roulette.model.Player;
import com.ggl.roulette.model.RouletteModel;
import com.ggl.roulette.model.Wager;
import com.ggl.roulette.view.RouletteFrame;

public class ChipListener extends MouseAdapter {

	private final RouletteFrame frame;
	
	private final RouletteModel model;
	
	public ChipListener(RouletteFrame frame, RouletteModel model) {
		this.frame = frame;
		this.model = model;
	}

	@Override
	public void mouseReleased(MouseEvent event) {
		Player player = model.getSelectedPlayer();
		if (player == null) {
			return;
		}
		Point point = event.getPoint();
//		System.out.print(point);
		
		if (MouseEvent.BUTTON1 == event.getButton()) {
			addWager(player, point);
		} else if (MouseEvent.BUTTON3 == event.getButton()) {
			removeWager(player, point);
		}
	}

	private void addWager(Player player, Point point) {
		ClickableWager clickableWager = model.containsClickableWager(point);
		if (clickableWager != null) {
			Wager wager = new Wager(clickableWager.getCenterPoint(), player, 
					clickableWager.getWagerType(), clickableWager.getNumbers());
			wager.placeWagerAmount(1, player.getChipValue());
//			System.out.print(Arrays.toString(clickableWager.getNumbers()));
			model.addWager(wager);
			frame.repaintRouletteTable();
		} 
	}
	
	private void removeWager(Player player, Point point) {
		ClickableWager clickableWager = model.containsClickableWager(point);
		if (clickableWager != null) {
			Point centerPoint = clickableWager.getCenterPoint();
			List<Wager> wagers = model.getWagers();
			for (int index = wagers.size() - 1; index >= 0; index--) {
				Wager wager = wagers.get(index);
				if (wager.getPlayer().getName().equals(player.getName()) 
						&& wager.getBoardLocation().equals(centerPoint)) {
					if (wager.getChipCount() > 1) {
						wager.incrementChipCount(-1);
					} else {
						wagers.remove(index);
					}
				}
			}
			frame.repaintRouletteTable();
		}
	}

}
