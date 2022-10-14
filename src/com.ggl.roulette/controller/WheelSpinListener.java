package com.ggl.roulette.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.Timer;

import com.ggl.roulette.model.Player;
import com.ggl.roulette.model.PlayerWagerError;
import com.ggl.roulette.model.RouletteModel;
import com.ggl.roulette.model.Wager;
import com.ggl.roulette.model.WheelSegment;
import com.ggl.roulette.view.RouletteFrame;
import com.ggl.roulette.view.dialog.WinningsDialog;

public class WheelSpinListener implements ActionListener {
	
	private int index;
	
	private final RouletteFrame frame;
	
	private final RouletteModel model;
	
	private Timer timer;
	
	private WheelSegment wheelSegment;

	public WheelSpinListener(RouletteFrame frame, RouletteModel model) {
		this.frame = frame;
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		PlayerWagerError pwe = model.areWagersValid();
		
		if (pwe.getErrorCode() == 0) {
			this.index = model.getRandomIndex();
			this.wheelSegment = model.getWheelSegment(index);
			WheelListener listener = new WheelListener();
			timer = new Timer(40, listener);
			listener.setTimer(timer);
			timer.start();
			return;
		}
		
		if (pwe.getErrorCode() == 1) {
			String s = "No bets have been placed.";
			JOptionPane.showMessageDialog(frame.getFrame(), s, "No Bets Placed", 
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		if (pwe.getErrorCode() == 2) {
			Player player = pwe.getPlayer();
			String s = player.getName() + " has not placed a minimum bet of $" +
					String.format("%,.2f", (double) model.getMinimumBet()) + ".";
			JOptionPane.showMessageDialog(frame.getFrame(), s, "No Bet Placed", 
					JOptionPane.ERROR_MESSAGE);
			model.clearWagers();
			frame.repaintRouletteTable();
			return;
		}
		
		if (pwe.getErrorCode() == 3) {
			Player player = pwe.getPlayer();
			String s = player.getName() + ", you can bet a maximum of $" +
					String.format("%,.2f", (double) model.getMaximumInnerBet()) +
					" on an inner bet.";
			JOptionPane.showMessageDialog(frame.getFrame(), s, "Maximum Inner Bet Exceeded", 
					JOptionPane.ERROR_MESSAGE);
			model.clearWagers();
			frame.repaintRouletteTable();
			return;
		}
		
		if (pwe.getErrorCode() == 4) {
			Player player = pwe.getPlayer();
			String s = player.getName() + ", you can bet a maximum of $" +
					String.format("%,.2f", (double) model.getMaximumOuterBet()) +
					" on an outer bet.";
			JOptionPane.showMessageDialog(frame.getFrame(), s, "Maximum Outer Bet Exceeded", 
					JOptionPane.ERROR_MESSAGE);
			model.clearWagers();
			frame.repaintRouletteTable();
			return;
		}
		
		if (pwe.getErrorCode() == 5) {
			Player player = pwe.getPlayer();
			String s = player.getName() + ", you may bet only as much as you have.";
			JOptionPane.showMessageDialog(frame.getFrame(), s, "Balance Exceeded", 
					JOptionPane.ERROR_MESSAGE);
			model.clearWagers();
			frame.repaintRouletteTable();
			return;
		}
	}
	
	private class WheelListener implements ActionListener {
		
		private int count, limit;
		
		private Timer timer;

		private WheelListener() {
			Random random = new Random();
			this.count = 0;
			this.limit = 200 + random.nextInt(100);
		}
		
		public void setTimer(Timer timer) {
			this.timer = timer;
		}

		@Override
		public void actionPerformed(ActionEvent event) {
			frame.initializeWheel();
			frame.rotateWheel(8.0);
			count++;
			if (count >= limit) {
				frame.repositionWheel(wheelSegment, index);
				String number = wheelSegment.getWheelNumber();
				frame.addCall(number, wheelSegment.getBackgroundColor(), 
						wheelSegment.getBackgroundColor().equals(Color.BLACK));
				
				List<Wager> winners = model.getWinningWagers(number);
				model.clearWagers();
				
				if (winners.size() > 0) {
					new WinningsDialog(frame, winners, number, "Winners");
				}

				for (Wager winner : winners) {
					Player player = winner.getPlayer();
					int chipCount = winner.getChipCount();
					int payoff = winner.getWagerType().getPayoff();
					int totalPayoff = payoff * chipCount;
					player.addChips(totalPayoff);
				}
				
				frame.updatePlayerPanel();
				frame.repaintRouletteTable();
				timer.stop();
			}
		}
		
	}

}
