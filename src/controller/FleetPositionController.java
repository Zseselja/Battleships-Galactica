package controller;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.FleetPositionView;
import view.Views;
import view.Window;

public class FleetPositionController {
	private int model;
	private FleetPositionView view;
	
	/**
	 * Setup the view and controller for interaction
	 */
	public FleetPositionController(int m, FleetPositionView v) {
		this.model = m;
		this.view = v;
		
		setUpViewEvents();
		//renderView(); // Skipping the first render for now because our model is fake
	}
	
	private void renderView() {
		this.view.getTextField().setText(Integer.toString(this.model));
	}
	
    private void setUpViewEvents(){
		view.getManipulateModelButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Doing the thing!");
				doTheThing();
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
    	model++;
    	renderView();
    }
}
