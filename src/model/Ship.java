package model;

import java.awt.Point;


public class Ship {
	private boolean[] health;
	
	private Point head;
	private Point tail;
	
	/**
	 * @param size length of the ship
	 * @param head starting point of the ship
	 * @param tail end point of the ship (must come up and right after head)
	 * 
	 * @throws Exception 
	 */
	public Ship(int size , Point head, Point tail) throws Exception {
		health = new boolean[size];
		
		for (int i = 0 ; i < size ; i++) {
			health[i] = true;
		}
		
		// Validate that the ship dimensions are 1*x or x*1
		if ((head.x == tail.x) || (head.y == tail.y)) {
			this.head = head;
			this.tail = tail;
		} else {
			throw new Exception("No Fat Ships!"); 
		}	 
	}
			
	public boolean[] getHealth(){
		return this.health;
	}
	
	public Point getHead(){
		return this.head;
	}
	
	public Point getTail(){
		return this.tail;
	}
	
	public boolean intersects(Point hit) {
		if ((this.head.x >= hit.x) && (this.tail.x <= hit.x)) {
			if ((this.head.y >= hit.y) && (this.tail.y <= hit.y)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}
