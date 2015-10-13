package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class FleetPositionModel {
	private ShipType selectedShip;
	private List<Ship> ships;
	private int test;
	
	private Point selectedHead;
	private Point selectedTail;
	
	public FleetPositionModel() {
		this.test = 0;
		this.ships = new ArrayList<Ship>();
		this.selectedShip = null;
		this.selectedHead = null;
		this.selectedTail = null;
	}

	public ShipType getSelectedShip() {
		return selectedShip;
	}

	public void setSelectedShip(ShipType selectedShip) {
		this.selectedShip = selectedShip;
	}

	public int getTest() {
		return test;
	}

	public void setTest(int test) {
		this.test = test;
	}

	public List<Ship> getShips() {
		return ships;
	}

	public void setShips(List<Ship> ships) {
		this.ships = ships;
	}

	public Point getSelectedHead() {
		return selectedHead;
	}

	public void setSelectedHead(Point selectedHead) {
		this.selectedHead = selectedHead;
	}

	public Point getSelectedTail() {
		return selectedTail;
	}

	public void setSelectedTail(Point selectedTail) {
		this.selectedTail = selectedTail;
	}

	
	
}
