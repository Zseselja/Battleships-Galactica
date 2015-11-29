package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Player implements Serializable {
	private ShipList ships;
	private List<Shot> shots;
	private boolean win;
	
	public Player() {
		this.ships = new ShipList();
		this.shots = new ArrayList<Shot>();
		this.win = false;
	}

	public ShipList getShips() {
		return ships;
	}

	public void setShips(ShipList ships) {
		this.ships = ships;
	}

	public List<Shot> getShots() {
		return shots;
	}
	
	

	public void setShots(List<Shot> shots) {
		this.shots = shots;
	}

	public boolean isWin() {
		return win;
	}

	public void setWin(boolean win) {
		this.win = win;
	}

}
