package model;

import java.awt.Point;

public class Shot extends Point {
	public boolean hit;
	
	public Shot(int x, int y) {
		super(x,y);
		this.hit = false;
	}

	public boolean isHit() {
		return hit;
	}

	public void setHit(boolean hit) {
		this.hit = hit;
	}

}
