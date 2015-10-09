import java.awt.Point;
import java.util.List;



public class Ship {
	private boolean[] health = null;
//	public String name;
	
//	public int posX;
//	public int posY;
	
	private Point head;
	private Point tail;
	

	/**
	 * 
	 * @param size length of the ship
	 * @param head starting point of the ship
	 * @param tail end point of the ship (must come up and right after head)
	 * @throws Exception 
	 */
	public Ship(int size , Point head, Point tail )throws Exception {
		health = new boolean[size];
		
		for(int i = 0 ; i < size ; i++){
			health[i] = true;
		
		}
		
		
//		checking to make sure the ships are only 1 block wide
		if((head.x == tail.x) || (head.y == tail.y)){
			this.head = head;
			this.tail = tail;
		}else{
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
	public boolean doesIntersect(Point hit){
		if((this.head.x >= hit.x) && (this.tail.x <= hit.x)){
			if((this.head.y >= hit.y) && (this.tail.y <= hit.y)){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	 
		
	}	
		
	
	
}
