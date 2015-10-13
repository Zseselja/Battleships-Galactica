package controller;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import model.FleetPositionModel;
import model.Ship;
import model.ShipFactory;
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
		this.view.getTextField().setText(Integer.toString(this.model.getTest()));
	}
	
    private void setUpViewEvents(){
		view.getManipulateModelButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Doing the thing!");
				doTheThing();
			}
		});
		
		view.getBoardPanel().addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				System.out.println("release");
				if (model.getSelectedHead() == null) return;
				
				int row = e.getY()/FleetPositionView.getBoardPanelCellSize();
				int col = e.getX()/FleetPositionView.getBoardPanelCellSize();
				model.setSelectedTail(new Point(col, row));
				/*
				if (model.getSelectedShip() != null) {
					ShipFactory factory = new ShipFactory();
					if (model.getSelectedShip() == ShipType.AIRCRAFT_CARRIER) {
						Ship ship = null;
						try {
							ship = factory.buildAircraftCarrier(model.getSelectedHead(), model.getSelectedTail());
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						model.getShips().add(ship);
					}
				}*/
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				System.out.println("press");
				int row = e.getY()/FleetPositionView.getBoardPanelCellSize();
				int col = e.getX()/FleetPositionView.getBoardPanelCellSize();
				model.setSelectedHead(new Point(col, row));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				System.out.println("exit");
				model.setSelectedHead(null);
				model.setSelectedTail(null);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {}
			
			@Override
			public void mouseClicked(MouseEvent e) {}
		});
		
		view.getAircraftCarrierButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model.setSelectedShip(ShipType.AIRCRAFT_CARRIER);
			}
		});
    }
    
    /**
     * A sample method that manipulates the model in some way, then calls a method that re-renders all the view
     * elements based on that model
     * 
     * Another example might be placeShip(Point pos)
     */
    private void doTheThing() {
    	model.setTest(model.getTest()+1);
    	renderView();
    }
}
