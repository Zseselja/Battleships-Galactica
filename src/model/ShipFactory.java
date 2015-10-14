package model;

import model.Ship;

import java.awt.Point;



public class ShipFactory{
	
	public Ship buildAircraftCarrier(Point head) {
		Ship x = null;
		try {
			x = new Ship(ShipType.AIRCRAFT_CARRIER, 6, head);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return x;		
	}
	
	public Ship buildAircraftCarrier(Point head, Point tail) {
		Ship x = null;
		try {
			x = new Ship(ShipType.AIRCRAFT_CARRIER, 6, head, tail);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return x;		
	}
	
	public Ship buildBattleship(Point head) {
		Ship x = null;
		try {
			x = new Ship(ShipType.BATTLESHIP, 5, head);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return x;
				
	}
	
	public Ship buildBattleship(Point head, Point tail) {
		Ship x = null;
		try {
			x = new Ship(ShipType.BATTLESHIP, 5, head, tail);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return x;
				
	}
	
	public Ship buildDestroyer(Point head) {
		Ship x = null;
		try {
			x = new Ship(ShipType.DESTROYER, 4, head);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return x;
				
	}
	
	public Ship buildDestroyer(Point head, Point tail) {
		Ship x = null;
		try {
			x = new Ship(ShipType.DESTROYER, 4, head, tail);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return x;
				
	}
	
	public Ship buildSub(Point head) {
		Ship x = null;
		try {
			x = new Ship(ShipType.SUB, 3, head);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return x;		
	}
	
	public Ship buildSub(Point head, Point tail) {
		Ship x = null;
		try {
			x = new Ship(ShipType.SUB, 3, head, tail);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return x;		
	}
	
	public Ship buildPatrol(Point head) {
		Ship x = null;
		try {
			x = new Ship(ShipType.PATROL, 2, head);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return x;
				
	}
	
	public Ship buildPatrol(Point head, Point tail) {
		Ship x = null;
		try {
			x = new Ship(ShipType.PATROL, 2, head, tail);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return x;
				
	}
}
