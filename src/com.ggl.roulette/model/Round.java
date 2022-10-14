package com.ggl.roulette.model;

import java.util.ArrayList;
import java.util.List;

public class Round {
	
	private final List<Wager> wagers;
	
	public Round() {
		this.wagers = new ArrayList<>();
	}
	
	public List<Wager> getWagers() {
		return wagers;
	}
	
	public List<Wager> getWinningWagers(String number) {
		List<Wager> winners = new ArrayList<>();
		for (Wager wager : wagers) {
			if (wager.isWinner(number)) {
				wager.setWinningWager(true);
				winners.add(wager);
			}
		}
		
		return winners;
	}

	public void addWager(Wager wager) {
		Player player = wager.getPlayer();
		for (Wager previousWager : wagers) {
			Player wagerPlayer = previousWager.getPlayer();
			if (player.getName().equals(wagerPlayer.getName())) {
				if (previousWager.isSameWager(wager)) {
					previousWager.incrementChipCount(wager.getChipCount());
					return;
				}
			}
		}
		
		this.wagers.add(wager);
	}
	
	public void clearWagers() {
		for (Wager wager : wagers) {
			Player player = wager.getPlayer();
			int chipCount = wager.getChipCount();
			player.removeChips(chipCount);
		}
		
		for (int index = wagers.size() - 1; index >= 0; index--) {
			Wager wager = wagers.get(index);
			if (wager.isWinningWager()) {
				wager.setWinningWager(false);
			} else {
				wagers.remove(index);
			}
		}
	}
	
	public List<Wager> getWagers(Player player) {
		List<Wager> playerWagers = new ArrayList<>();
		for (Wager wager : wagers) {
			if (wager.getPlayer().getName().equals(player.getName())) {
				playerWagers.add(wager);
			}
		}
		
		return playerWagers;
	}

}
