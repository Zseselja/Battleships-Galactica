package model;

import java.util.Arrays;
import java.util.ArrayList;
import java.awt.Point;

/*
 * The Board class, one instance for each player
 * 
 * Stores both the state of the player board with ships and the 
 * empty opponents board to be filled as the game progresses.
 * 
 * */
public class Board {
	
	// Board tile types
	public enum tile{
		HIT, MISS, UNKNOWN, SHIP, WATER
	}
	
	public ArrayList<Ship> ships;
	public tile opponentBoard[][];
	public tile playerBoard[][];
	public int size;
	public int player;
	
	// Constructor
	public Board(int size, int playerNum){
		player = playerNum;
		opponentBoard = new tile[size][size];
		
		// Sets the opponents board with unknowns
		for(int i = 0; i < size; i++){
			Arrays.fill(opponentBoard[i], tile.UNKNOWN);
		}
		
		// Fills the player board with water
		for(int i = 0; i < size; i++){
			Arrays.fill(playerBoard, tile.WATER);
		}
	}
	
	// Adds a ship the the list
	public void addShip(Ship x){
		ships.add(x);
	}
	
	public ArrayList<Ship> getShips(){
		return this.ships;
	}
	
	// Returns the state of the enemy board
	public tile[][] getPlayerBoard(){
		return this.opponentBoard;
	}
	
	// Returns the state of the enemy board
	public tile[][] getOpponentBoard(){
		return this.opponentBoard;
	}
	
	// Updates the opponents board with a hit
	public void hitOpponent(Point hit){
		opponentBoard[hit.x][hit.y] = tile.HIT;
	}
	
	// Updates the opponents board with a miss
	public void missOpponent(Point miss){
		opponentBoard[miss.x][miss.y] = tile.MISS;
	}
	
	// Updates the opponents board with a hit
	public void hitPlayer(Point hit){
		playerBoard[hit.x][hit.y] = tile.HIT;
	}
		
	// Updates the opponents board with a miss
	public void missPlayer(Point miss){
		playerBoard[miss.x][miss.y] = tile.MISS;
	}
}
