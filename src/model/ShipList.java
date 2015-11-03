package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class ShipList extends ArrayList<Ship> {
	
	public ShipList() {
		super();
	}
	
	public Ship getShip(ShipType type) {
		for (Ship s: this) {
			if (s.getType() == type) {
				return s;
			}
		}
		return null;
	}
	
	public boolean isAnyShipIntersecting() {
		List<Point> points = new ArrayList<Point>();
		
		for (Ship s: this) {
		    if (s.isVertical()) {
				int col = s.getHead().x;
				int row = s.getHead().y;
				int endRow = s.getTail().y;
				
				while (row <= endRow) {
					Point curr = new Point(col, row);
					if (points.contains(curr)) {
						return true;
					} else {
						points.add(new Point(col, row));
					}
					row++;
				}
			} else {
				int row = s.getHead().y;
				int col = s.getHead().x;
				int endCol = s.getTail().x;
				while (col <= endCol) {
					Point curr = new Point(col, row);
					if (points.contains(curr)) {
						return true;
					} else {
						points.add(new Point(col, row));
					}
					col++;
				}
			}
		}
		
		return false;
	}
	
	public boolean intersects(Point hit) {
		boolean intersection = false;
		for (Ship s: this) {
			if (s.isVertical()) {
				if (hit.x == s.getHead().x) {
					if (hit.y >= s.getHead().y && hit.y <= s.getTail().y) {
						intersection = true;
					}
				}
			} else {
				if (hit.y == s.getHead().y) {
					if (hit.x >= s.getHead().x && hit.x <= s.getTail().x) {
						intersection = true;
					}
				}
			}
		}
		return intersection;
	}
	
	public Ship getIntersectingShip(Point hit) {
		for (Ship s: this) {
			if (s.isVertical()) {
				if (hit.x == s.getHead().x) {
					if (hit.y >= s.getHead().y && hit.y <= s.getTail().y) {
						return s;
					}
				}
			} else {
				if (hit.y == s.getHead().y) {
					if (hit.x >= s.getHead().x && hit.x <= s.getTail().x) {
						return s;
					}
				}
			}
		}
		return null;
	}
	
	public boolean isAllShipsSunk() {
		for (Ship s: this) {
			if (!s.isSunk()) {
				return false;
			}
		}
		return true;
	}
}
