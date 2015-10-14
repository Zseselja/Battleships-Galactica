package controller;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import org.omg.CosNaming.NamingContextExtPackage.AddressHelper;

import model.FleetPositionModel;
import model.Ship;
import model.ShipType;
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
		for (Ship s: this.model.getShips()) {
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
				
				int col = e.getX()/FleetPositionView.getBoardPanelCellSize();
				int row = e.getY()/FleetPositionView.getBoardPanelCellSize();
				
				if (model.getShipToPlace() != null) {
					/*
					 * The ship selected has not been placed on the board yet.
					 * So we create the ship and add it to the list of ships. 
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
					model.getShips().add(s);
					model.setShipToPlace(null);
				} else {
					/*
					 * The ship selected is already on the board. So we translate the Ship to where
					 * the user clicked. 
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
				setShipToModify(ShipType.AIRCRAFT_CARRIER);
			}
		});
		
		view.getBattleshipButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setShipToModify(ShipType.BATTLESHIP);
			}
		});
		
		view.getDestroyerButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setShipToModify(ShipType.DESTROYER);
			}
		});
		
		view.getSubButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setShipToModify(ShipType.SUB);
			}
		});
		
		view.getPatrolButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setShipToModify(ShipType.PATROL);
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
		
		view.getDoneButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				List<Point> points = new ArrayList<Point>();
				
				boolean shipIntersection = false;
				for (Ship s: model.getShips()) {
					if (s.isVertical()) {
						int col = s.getHead().x;
						int row = s.getHead().y;
						int endRow = s.getTail().y;
						
						while (row <= endRow) {
							Point curr = new Point(col, row);
							if (points.contains(curr)) {
								shipIntersection = true;
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
								shipIntersection = true;
							} else {
								points.add(new Point(col, row));
							}
							col++;
						}
					}
				}
				
				if (shipIntersection) {
					System.out.println("The board is invalid");
					return;
				} else {
					//Go to game play view.
				}
				
			}
		});
    }
    
    /*
     * Checks whether the specified ship is already on the board,
     * if it is then it sets the currentShip to that (so that any clicks on the board translate that ship, and any clicks
     * to the rotate button, rotate that ship)
     * if not, then it sets the shipToPlace as the specified ship
     */
    private void setShipToModify(ShipType type) {
    	boolean alreadyPlaced = false;
		for (Ship s: model.getShips()) {
			if (s.getType() == type) {
				model.setCurrentShip(s);
				alreadyPlaced = true;
			}
		}
		
		if (!alreadyPlaced) {
			model.setShipToPlace(type);
		}
    }
    
    private boolean isPointInBoard(Point p) {
    	if (p.x > FleetPositionView.getBoardPanelMaxCols()-1
    			|| p.y > FleetPositionView.getBoardPanelMaxRows()-1) {
    		return false;
    	}
    	return true;
    }
    
    //Done. create list of all points on map. if there is a duplicate..then the fleet position is invalid.

}
