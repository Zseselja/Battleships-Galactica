package model;

import model.Ship;

import java.awt.Point;
import java.util.Random;



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
	
	public Ship buildRandomShip(Point head, ShipType type) {
		Random r = new Random();
		int randOrientation = r.nextInt(2); // 0 or 1
		Point tail = null;

		if (type == ShipType.AIRCRAFT_CARRIER) {
			if (randOrientation == 1) {
				tail = new Point(head.x+5, head.y);
	    	} else {
	    		tail = new Point(head.x, head.y+5);
	    	}
			return buildAircraftCarrier(head, tail);
		} else if (type == ShipType.BATTLESHIP) {
			if (randOrientation == 1) {
				tail = new Point(head.x+4, head.y);
	    	} else {
	    		tail = new Point(head.x, head.y+4);
	    	}
			return buildBattleship(head, tail);
		} else if (type == ShipType.DESTROYER) {
			if (randOrientation == 1) {
				tail = new Point(head.x+3, head.y);
	    	} else {
	    		tail = new Point(head.x, head.y+3);
	    	}
			return buildDestroyer(head, tail);
		} else if (type == ShipType.SUB) {
			if (randOrientation == 1) {
				tail = new Point(head.x+2, head.y);
	    	} else {
	    		tail = new Point(head.x, head.y+2);
	    	}
			return buildSub(head, tail);
		} else {
			if (randOrientation == 1) {
				tail = new Point(head.x+1, head.y);
	    	} else {
	    		tail = new Point(head.x, head.y+1);
	    	}
			return buildPatrol(head, tail);
		}
	}
}
