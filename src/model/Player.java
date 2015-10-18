package model;

import java.util.ArrayList;
import java.util.List;

public class Player {
	private ShipList ships;
	private List<Shot> shots;
	
	public Player() {
		this.ships = new ShipList();
		this.shots = new ArrayList<Shot>();
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
}
