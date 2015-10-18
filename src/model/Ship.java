package model;

import java.awt.Point;


public class Ship {
	private ShipType type;
	private int health;
	
	private Point head;
	private Point tail;
	
	/**
	 * @param size length of the ship
	 * @param head starting point of the ship
	 * @param tail end point of the ship (must come up and right after head)
	 * 
	 * @throws Exception 
	 */
	public Ship(ShipType type, int size , Point head, Point tail) throws Exception {
		this.type = type;
		this.health = size;
		
		// Validate that the ship dimensions are 1*x or x*1
		boolean validShip = false;
		if (head.x == tail.x) { //vertical ship
			if (Math.abs(head.y-tail.y) == size-1) {
				validShip = true;
			}
		} else if (head.y == tail.y) { //horizontal ship
			if (Math.abs(head.x-tail.x) == size-1) {
				validShip = true;
			}
		}
		
		if (validShip) {
			this.head = head;
			this.tail = tail;
		} else {
			throw new Exception("No Fat Ships!"); 
		}
 
	}
	
	/*
	 * Automatically sets the tail horizontally from the head.
	 */
	public Ship(ShipType type, int size, Point head) throws Exception {
		this.type = type;
		this.health = size;
		
		this.head = head;
		this.tail = new Point(head.x+size-1, head.y);
	}
	
	public void decreaseHealth() {
		this.health = this.health - 1;
	}
	
	public boolean isSunk() {
		return this.health == 0;
	}

	public Point getHead(){
		return this.head;
	}
	
	public Point getTail(){
		return this.tail;
	}
	
	public void setHead(Point head) {
		this.head = head;
	}

	public void setTail(Point tail) {
		this.tail = tail;
	}
	
	public boolean isVertical() {
		return head.x == tail.x;
	}
	
	public int size() {
		if (isVertical()) {
			return Math.abs(head.y-tail.y);
		} else {
			return Math.abs(head.x-tail.x);
		}
	}
       /*
	* This method determines if the shot from a player 
	* hits a ship on the board or not. 
	*/
	public boolean intersects(Point hit) {
		boolean intersection = false;
		if (this.isVertical()) {
			if (hit.x == this.head.x) {
				if (hit.y >= this.head.y && hit.y <= this.tail.y) {
					intersection = true;
				}
			}
		} else {
			if (hit.y == this.head.y) {
				if (hit.x >= this.head.x && hit.x <= this.tail.x) {
					intersection = true;
				}
			}
		}
		return intersection;
	}
	
	public void translate(int dx, int dy) {
		this.head.translate(dx, dy);
		this.tail.translate(dx, dy);
	}

	public ShipType getType() {
		return type;
	}
	
}
