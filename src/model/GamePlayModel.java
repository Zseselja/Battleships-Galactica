package model;

public class GamePlayModel {
	private Player player;
	private Computer computer;

	public GamePlayModel(ShipList playerShips, ShipList computerShips) {
		this.player = new Player();
		this.player.setShips(playerShips);
		
		this.computer = new Computer();
		this.computer.setShips(computerShips);
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Computer getComputer() {
		return computer;
	}

	public void setComputer(Computer computer) {
		this.computer = computer;
	}
	
}
