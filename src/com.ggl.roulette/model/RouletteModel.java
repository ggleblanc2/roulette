package com.ggl.roulette.model;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RouletteModel {
	
	private final int minimumBet;
	private final int maximumInnerBet;
	private final int maximumOuterBet;
	
	private final ChipValues chipValues;
	
	private final ClickableWagers clickableWagers;
	
	private final List<Player> players;
	
	private Player selectedPlayer;
	
	private final Random random;
	
	private final Round round;
	
	private final RouletteChips rouletteChips;
	
	private final RouletteWheel rouletteWheel;
	
	public RouletteModel() {
		this.chipValues = new ChipValues();
		this.rouletteChips = new RouletteChips();
		this.rouletteWheel = new RouletteWheel();
		this.random = new Random();
		this.round = new Round();
		this.minimumBet = 4;
		this.maximumInnerBet = 25;
		this.maximumOuterBet = 200;
		this.players = new ArrayList<>();
		this.selectedPlayer = null;	
//		addPlayer(createPlayer());
		this.clickableWagers = new ClickableWagers();
	}
	
//	private Player createPlayer() {
//		//TODO Temporary code to create one player so I can work on the betting first.
//		Player player = new Player("Gilbert");
//		player.setRouletteChip(new RouletteChip(Color.BLUE, Color.YELLOW));
//		player.buyIn(10000, new ChipValue(25));
//		
//		return player;
//	}
	
	public ClickableWager containsClickableWager(Point point) {
		return clickableWagers.containsClickableWager(point);
	}
	
	public void addPlayer(Player player) {
		this.players.add(player);
	}
	
	public void removePlayer(Player player) {
		RouletteChip rouletteChip = player.getRouletteChip();
		this.rouletteChips.addRouletteChip(rouletteChip);
		this.players.remove(player);
	}
	
	public List<Player> getPlayers() {
		return players;
	}
	
	public Player getPlayer(String name) {
		for (Player player : players) {
			if (player.getName().equals(name)) {
				return player;
			}
		}
		
		return null;
	}
	
	/**
	 * <p>This method returns a <code>PlayerWagerError</code> instance so the
	 * calling controller class can respond properly.</p>
	 * 
	 * @return PlayerWagerError instance where the error codes are:
	 * 
	 * <table>
	 * <tr><th>ErrorCode</th><th>Reason</th></tr>
	 * <tr><td style="text-align:center">0</td><td>No errors found</td></tr>
	 * <tr><td style="text-align:center">1</td><td>No bets placed</td></tr>
	 * <tr><td style="text-align:center">2</td><td>Player didn't make minimum bet</td></tr>
	 * <tr><td style="text-align:center">3</td><td>Player exceeded maximum inner bet</td></tr>
	 * <tr><td style="text-align:center">4</td><td>Player exceeded maximum outer bet</td></tr>
	 * <tr><td style="text-align:center">5</td><td>Player bets exceed player balance</td></tr>
	 * </table>
	 * 
	 */
	public PlayerWagerError areWagersValid() {
		List<Wager> wagers = round.getWagers();
		if (wagers.size() <= 0) {
			return new PlayerWagerError(1, null);
		}
		
		for (Player player : players) {
			List<Wager> playerWagers = round.getWagers(player);
			int amount = 0;
			for (Wager wager : playerWagers) {
				amount += wager.getChipCount() * wager.getChipValue();
				if (wager.getWagerType().isInnerWager()) {
					int innerAmount = wager.getChipCount() * wager.getChipValue();
					if (innerAmount > maximumInnerBet) {
						return new PlayerWagerError(3, player);
					}
				} else {
					int outerAmount = wager.getChipCount() * wager.getChipValue();
					if (outerAmount > maximumOuterBet) {
						return new PlayerWagerError(4, player);
					}
				}
			}
			
			if (amount < minimumBet) {
				return new PlayerWagerError(2, player);
			}
			
			if (amount > player.getBalance()) {
				return new PlayerWagerError(5, player);
			}
			
		}
		
		return new PlayerWagerError(0, null);
	}
	
	public Player getSelectedPlayer() {
		return selectedPlayer;
	}

	public void setSelectedPlayer(Player selectedPlayer) {
		this.selectedPlayer = selectedPlayer;
	}

	public void addRouletteChip(RouletteChip rouletteChip) {
		rouletteChips.addRouletteChip(rouletteChip);
	}

	public void removeRouletteChip(RouletteChip rouletteChip) {
		rouletteChips.removeRouletteChip(rouletteChip);
	}
	
	public RouletteChips getRouletteChips() {
		return rouletteChips;
	}
	
	public List<Wager> getWagers() {
		return round.getWagers();
	}
	
	public List<Wager> getWinningWagers(String number) {
		return round.getWinningWagers(number);
	}

	public void addWager(Wager wager) {
		round.addWager(wager);
	}
	
	public void clearWagers() {
		round.clearWagers();
	}
	
	public int getMinimumBet() {
		return minimumBet;
	}

	public int getMaximumInnerBet() {
		return maximumInnerBet;
	}

	public int getMaximumOuterBet() {
		return maximumOuterBet;
	}

	public ChipValues getChipValues() {
		return chipValues;
	}

	public Color getRedColor() {
		return rouletteWheel.getRedColor();
	}
	
	public Color getGreenColor() {
		return rouletteWheel.getGreenColor();
	}
	
	public WheelSegment[] getWheelSegments() {
		return rouletteWheel.getWheelSegments();
	}
	
	public int getWheelSegmentLength() {
		return rouletteWheel.length();
	}
	
	public int getRandomIndex() {
		return random.nextInt(rouletteWheel.length());
	}
	
	public WheelSegment getWheelSegment(int index) {
		return rouletteWheel.getWheelSegment(index);
	}

}
