package controller;

import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.FleetPositionModel;
import model.Ship;
import model.ShipFactory;
import model.ShipList;
import model.ShipType;
import view.BoardConstants;
import view.FleetPositionView;

public class FleetPositionController {
	private FleetPositionView view;
	private FleetPositionModel model;
	
	/**
	 * Setup the view and controller for interaction
	 */
	public FleetPositionController(FleetPositionModel m, FleetPositionView v) {
		this.model = m;
		this.view = v;
		
		setUpViewEvents();
		//renderView(); // Skipping the first render for now because our model is fake
	}
	
	private void renderView() {
		//this.view.getTextField().setText(Integer.toString(this.model.getTest()));
		for (Ship s: this.model.getPlacedShips()) {
			if (s.isVertical()) {
				int col = s.getHead().x;
				int row = s.getHead().y;
				int endRow = s.getTail().y;
				while (row <= endRow) {
					this.view.getBoard()[col][row++].setColor(Color.RED);
				}
			} else {
				int row = s.getHead().y;
				int col = s.getHead().x;
				int endCol = s.getTail().x;
				while (col <= endCol) {
					this.view.getBoard()[col++][row].setColor(Color.RED);
				}
			}
		}
		this.view.repaint();
	}
	
    private void setUpViewEvents() {
		view.getBoardPanel().addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				
				int col = e.getX()/FleetPositionView.getBoardCellSize();
				int row = e.getY()/FleetPositionView.getBoardCellSize();
				
				if (model.getShipToPlace() != null) {
					/*
					 * The ship selected has not been placed on the board yet.
					 * So we create the ship, add it to the list of ships, and set it as the currentShip.
					 * The head of the ship is where the user clicked.
					 */
					Point head = new Point(col, row);
					
					Ship s = null;
					if (model.getShipToPlace() == ShipType.AIRCRAFT_CARRIER) {
						s = model.getShipFactory().buildAircraftCarrier(head);
					} else if (model.getShipToPlace() == ShipType.BATTLESHIP) {
						s = model.getShipFactory().buildBattleship(head);
					} else if (model.getShipToPlace() == ShipType.DESTROYER) {
						s = model.getShipFactory().buildDestroyer(head);
					} else if (model.getShipToPlace() == ShipType.PATROL) {
						s = model.getShipFactory().buildPatrol(head);
					} else if (model.getShipToPlace() == ShipType.SUB) {
						s = model.getShipFactory().buildSub(head);
					}
					
					if (!isPointInBoard(s.getTail())) {
						System.out.println("The tail is not in the board");
						return;
					}
					
					model.setCurrentShip(s);
					model.getPlacedShips().add(s);
					model.setShipToPlace(null);
				} else {
					/*
					 * The ship selected is already on the board. If there is a current ship,
					 * we translate the Ship to where the user clicked. 
					 */
					if (model.getCurrentShip() != null) {
						int dx = col-model.getCurrentShip().getHead().x;
						int dy = row-model.getCurrentShip().getHead().y;
						
						Point tempTail = new Point(model.getCurrentShip().getTail());
						tempTail.translate(dx, dy);
						if (!isPointInBoard(tempTail)) {
							System.out.println("The new tail is not in the board");
							return;
						}
	
						model.getCurrentShip().translate(dx, dy);
					}
				}
				
				renderView();
			}
			
			@Override
			public void mousePressed(MouseEvent e) {}
			
			@Override
			public void mouseExited(MouseEvent e) {}
			
			@Override
			public void mouseEntered(MouseEvent e) {}
			
			@Override
			public void mouseClicked(MouseEvent e) {}
		});
		
		view.getAircraftCarrierButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ShipType type = ShipType.AIRCRAFT_CARRIER;
				Ship ship = model.getPlacedShips().getShip(type);
				if (ship == null) {
					model.setShipToPlace(type);
				} else {
					model.setCurrentShip(ship);
				}
			}
		});
		
		view.getBattleshipButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ShipType type = ShipType.BATTLESHIP;
				Ship ship = model.getPlacedShips().getShip(type);
				if (ship == null) {
					model.setShipToPlace(type);
				} else {
					model.setCurrentShip(ship);
				}
			}
		});
		
		view.getDestroyerButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ShipType type = ShipType.DESTROYER;
				Ship ship = model.getPlacedShips().getShip(type);
				if (ship == null) {
					model.setShipToPlace(type);
				} else {
					model.setCurrentShip(ship);
				}
			}
		});
		
		view.getSubButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ShipType type = ShipType.SUB;
				Ship ship = model.getPlacedShips().getShip(type);
				if (ship == null) {
					model.setShipToPlace(type);
				} else {
					model.setCurrentShip(ship);
				}
			}
		});
		
		view.getPatrolButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ShipType type = ShipType.PATROL;
				Ship ship = model.getPlacedShips().getShip(type);
				if (ship == null) {
					model.setShipToPlace(type);
				} else {
					model.setCurrentShip(ship);
				}
			}
		});
		
		view.getRotateButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (model.getCurrentShip() == null) return;
				System.out.println("Rotating ship");
				
				int size = model.getCurrentShip().size();
				
				Point newTail = null;
				if (model.getCurrentShip().isVertical()) {
					newTail = new Point(model.getCurrentShip().getHead().x+size, model.getCurrentShip().getHead().y);
					
				} else {
					newTail = new Point(model.getCurrentShip().getHead().x, model.getCurrentShip().getTail().y+size);
				}
				
				if (!isPointInBoard(newTail)) {
					System.out.println("The new tail is not in the board");
					return;
				}
				
				model.getCurrentShip().setTail(newTail);
				renderView();
			}
		});
    }
    
    private boolean isPointInBoard(Point p) {
    	if (p.x > BoardConstants.MAX_COLS-1
    			|| p.y > BoardConstants.MAX_ROWS-1) {
    		return false;
    	}
    	return true;
    }
    
    public ShipList getCorrectlyPlacedShips() {

    	if (model.isAllShipsPlaced()) {
    		if (!model.getPlacedShips().isAnyShipIntersecting()) {
    			return model.getPlacedShips();
    		} else {
    			System.out.println("Ships are intersecting");
    			//Update view message to say that ships are intersecting
        		//renderView();
    			return null;
    		}
    	} else {
    		System.out.println("Not all ships placed");
    		//Update view message to say that not all ships placed
    		//renderView();
    		return null;
    	}
    }
    
    public ShipList generateComputerShips() {
    	ShipList placedShips = new ShipList();
    	List<ShipType> shipsToBuild = new ArrayList<ShipType>();
    	shipsToBuild.add(ShipType.AIRCRAFT_CARRIER);
    	shipsToBuild.add(ShipType.BATTLESHIP);
    	shipsToBuild.add(ShipType.DESTROYER);
    	shipsToBuild.add(ShipType.SUB);
    	shipsToBuild.add(ShipType.PATROL);
    	
    	ShipFactory factory = model.getShipFactory();
    	
    	while (!shipsToBuild.isEmpty()) {
    		ShipType type = shipsToBuild.remove(0);
    		boolean intersection;
    		boolean outOfBoard;
    		do {
    			intersection = false;
    			outOfBoard = false;
	    		Random r = new Random();
	        	int randX = r.nextInt(BoardConstants.MAX_COLS);
	        	int randY = r.nextInt(BoardConstants.MAX_ROWS);
	        	
	        	Point head = new Point(randX, randY);
	    		Ship ship = factory.buildRandomShipOfType(type, head);
	    		if (ship.getTail().x >= BoardConstants.MAX_COLS || ship.getTail().y >= BoardConstants.MAX_ROWS) {
	    			outOfBoard = true;
	    		}
	    		
	    		if (!outOfBoard) {
		    		placedShips.add(ship);
		    		if (placedShips.isAnyShipIntersecting()) {
		    			intersection = true;
		    			placedShips.remove(ship);
		    		}
	    		}
    		} while (intersection || outOfBoard);
    	}

    	return placedShips;
    }
    /*
    private boolean isTouching(ShipList ships) {
    	List<Point> points = new ArrayList<Point>();
    	for (Ship s: ships) {
    		if (s.isVertical()) {
    			
    		} else {
    			if (s.getHead().y >= 1) {
					for (int i = s.getHead().x-1; i <= s.getTail().x+1; i++) {
						points.add(new Point(i, s.getHead().y-1));
					}
    			}
    			
    			if (s.getHead().y <= BoardConstants.MAX_ROWS-2) {
					for (int i = s.getHead().x-1; i <= s.getTail().x+1; i++) {
						points.add(new Point(i, s.getHead().y+1));
					}
    			}
    			
    			if (s.getHead().x >= 1) {
    				points.add(new Point(s.getHead().x-1, s.getHead().y));
    			}
    			
    			if (s.getHead().x <= BoardConstants.MAX_COLS-2) {
    				points.add(new Point(s.getHead().x+1, s.getHead().y));
    			}
    		}
    	}
    	
    	for (Point p: points) {
    		if (ships.intersects(p)) {
    			return true;
    		}
    	}
    	return false;
    }*/

}
