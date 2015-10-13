package model;

import model.Ship;

import java.awt.Point;



public class ShipFactory{
	
	public Ship buildBattleship(Point head , Point tail) throws Exception {
		Ship x = new Ship( 5 , head , tail);
		return x;
				
	}
	
	public Ship buildAircraftCarrier(Point head , Point tail) throws Exception {
		Ship x = new Ship( 6 , head , tail);
		return x;
				
	}
	public Ship buildDestroyer(Point head , Point tail) throws Exception {
		Ship x = new Ship( 4 , head , tail);
		return x;
				
	}
	public Ship buildSub(Point head , Point tail) throws Exception {
		Ship x = new Ship( 3 , head , tail);
		return x;
				
	}
	public Ship buildPatrol(Point head , Point tail) throws Exception {
		Ship x = new Ship( 2 , head , tail);
		return x;
				
	}
}
