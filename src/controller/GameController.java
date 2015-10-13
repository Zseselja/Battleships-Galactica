package controller;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.FleetPositionModel;
import view.FleetPositionView;
import view.LeaderBoardView;
import view.StartView;
import view.Views;
import view.Window;

public class GameController 
{
	private Window window;
	
	public GameController(Window window) {
		// Initialize the window
		this.window = window;
		this.window.setLayout(new CardLayout());
		
		// Launch into the start screen
		launchStart();
	}
	
	/**
	 * Instantiates the StartView, binds to its panel-changing actions, and applies it to the window
	 */
	public void launchStart() {
		// Create the start view
		// TODO: Retrieve/reset the state of an existing start view if it exists in the window
		StartView startView = new StartView(window.getContentPane().getWidth(), window.getContentPane().getHeight());
		
		// Bind to any buttons/actions that change the visible panel
		startView.getStartButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				launchFleetPosition();
			}
		});
		
		startView.getLeaderBoardButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				launchLeaderBoard();
			}
		});
		
		// Apply the start view to the main window
		this.window.add(startView, Views.START_VIEW);
		CardLayout layout = (CardLayout) window.getContentPane().getLayout();
		layout.show(window.getContentPane(), Views.START_VIEW);
		this.window.setVisible(true);
	}
	
	/**
	 * Instantiates the FleetPositionView, binds to its panel-changing actions, and applies it to the window
	 */
	public void launchFleetPosition() {
		System.out.println("Navigating to Fleet Position View");
		
		FleetPositionView fleetPositionView = new FleetPositionView(window.getContentPane().getWidth(), window.getContentPane().getHeight());
		FleetPositionModel fleetPositionModel = new FleetPositionModel();
		FleetPositionController fleetPositionController = new FleetPositionController(fleetPositionModel, fleetPositionView);
		
		fleetPositionView.getMainButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Navigating to Start View");
				
				launchStart();
			}
		});
		
		// Placed here to show how another button would be added
//		fleetPositionView.getFinsishedPlacingButton().addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				System.out.println("Navigating to beginning of game");
//				
//				launchGame();
//			}
//		});
		
		this.window.add(fleetPositionView, Views.FLEET_POSITION_VIEW);
		CardLayout layout = (CardLayout) window.getContentPane().getLayout();
		layout.show(window.getContentPane(), Views.FLEET_POSITION_VIEW);
	}
	
	/**
	 * Instantiates the LeaderBoardView, binds to its panel-changing actions, and applies it to the window
	 */
	public void launchLeaderBoard() {
		System.out.println("Navigating to Fleet Position View");
		
		LeaderBoardView leaderBoardView = new LeaderBoardView(window.getContentPane().getWidth(), window.getContentPane().getHeight());
		
		leaderBoardView.getMainButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Navigating to Start View");
				launchStart();
			}
		});
		
		this.window.add(leaderBoardView, Views.LEADER_BOARD_VIEW);
		CardLayout layout = (CardLayout) window.getContentPane().getLayout();
		layout.show(window.getContentPane(), Views.LEADER_BOARD_VIEW);
	}

}
