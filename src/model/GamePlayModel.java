package model;

public class GamePlayModel {
	private Player player;
	private Player computer;

	public GamePlayModel(ShipList playerShips, ShipList computerShips) {
		this.player = new Player();
		this.player.setShips(playerShips);
		
		this.computer = new Player();
		this.computer.setShips(computerShips);
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Player getComputer() {
		return computer;
	}

	public void setComputer(Player computer) {
		this.computer = computer;
	}
	
}
