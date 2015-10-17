package model;

public class FleetPositionModel {
	//shipToPlace is modified when the user clicks one of the ship buttons. 
	//If the ship (for the button) has already been placed on the board, then shipToPlace = null
	private ShipType shipToPlace;
	
	//Any clicks to the board always translates the currentShip and any clicks to the rotate button always rotate the currentShip
	private Ship currentShip;
	
	private ShipList placedShips;
	private ShipFactory shipFactory;
	private int test;
	
	public FleetPositionModel() {
		this.test = 0;
		this.placedShips = new ShipList();
		this.shipToPlace = null;
		this.currentShip = null;
		this.shipFactory = new ShipFactory();
	}

	public ShipType getShipToPlace() {
		return shipToPlace;
	}

	public void setShipToPlace(ShipType shipToPlace) {
		this.shipToPlace = shipToPlace;
	}

	public int getTest() {
		return test;
	}

	public void setTest(int test) {
		this.test = test;
	}

	public ShipList getPlacedShips() {
		return placedShips;
	}

	public void setPlacedShips(ShipList ships) {
		this.placedShips = ships;
	}

	public Ship getCurrentShip() {
		return currentShip;
	}

	public void setCurrentShip(Ship currentShip) {
		this.currentShip = currentShip;
	}

	public ShipFactory getShipFactory() {
		return shipFactory;
	}
	
	public boolean isAllShipsPlaced() {
		return this.placedShips.size() >= 5;
	}
	
}
