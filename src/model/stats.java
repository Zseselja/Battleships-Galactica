package model;

public class stats {
	public int shots;
	public int hits;
	public boolean win;
	
	public void addShot(){
		this.shots++;
	}
	public void addHit(){
		this.hits++;
	}
	public int getShot(){
		return this.shots;
	}
	public int getHit(){
		return this.hits;
	}
	public void setWin(boolean A){
		this.win = A;
	}
}
