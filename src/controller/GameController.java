package controller;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.FleetPositionModel;
import model.GamePlayModel;
import model.ShipList;
import model.Shot;
import model.Player;
import model.stats;
import view.FleetPositionView;
import view.GamePlayView;
import view.GameResultsView;
import view.LeaderBoardView;
import view.StartView;
import view.Views;
import view.Window;

public class GameController 
{
	private Window window;
	private FleetPositionController fleetPositionController;
	private GamePlayController gamePlayController;
	
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
		this.fleetPositionController = new FleetPositionController(fleetPositionModel, fleetPositionView);
		
		fleetPositionView.getMainButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Navigating to Start View");
				
				launchStart();
			}
		});
		
		fleetPositionView.getDoneButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				ShipList playerShips = fleetPositionController.getCorrectlyPlacedShips();
				if (playerShips != null) {
					ShipList computerShips = fleetPositionController.generateComputerShips();
					launchGamePlay(playerShips, computerShips);
				}/*
				ShipList playerShips = fleetPositionController.generateComputerShips();
				ShipList computerShips = fleetPositionController.generateComputerShips();
				launchGamePlay(playerShips, computerShips);*/
			}
		});
		
		
		this.window.add(fleetPositionView, Views.FLEET_POSITION_VIEW);
		CardLayout layout = (CardLayout) window.getContentPane().getLayout();
		layout.show(window.getContentPane(), Views.FLEET_POSITION_VIEW);
	}
	
	/**
	 * Instantiates the LeaderBoardView, binds to its panel-changing actions, and applies it to the window
	 */
	public void launchLeaderBoard() {
		System.out.println("Navigating to Leader Board View");
		
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
	
	/**
	 * Instantiates the GamePlayView, binds to its panel-changing actions, and applies it to the window
	 */
	public void launchGamePlay(ShipList playerShips, ShipList computerShips) {
		System.out.println("Navigating to Game Play View");
		

		GamePlayView gamePlayView = new GamePlayView(window.getContentPane().getWidth(), window.getContentPane().getHeight());
		GamePlayModel gamePlayModel = new GamePlayModel(playerShips, computerShips );
		this.gamePlayController = new GamePlayController(gamePlayModel, gamePlayView);
		
		gamePlayView.getContinueButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				launchGameResults();
			}
		});
		
		this.window.add(gamePlayView, Views.GAME_PLAY_VIEW);
		CardLayout layout = (CardLayout) window.getContentPane().getLayout();
		layout.show(window.getContentPane(), Views.GAME_PLAY_VIEW);
	}
	
	/**
	 * Instantiates the GameResultsView, binds to its panel-changing actions, and applies it to the window
	 */
	public void launchGameResults() {
		System.out.println("Navigating to Game Results View");
		stats gameStats = new stats();
		
//		**problem with static types
		
//		gameStats.hits = GamePlayController.getTotalHits();
//		gameStats.shots = GamePlayController.getTotalShots();
		
		GameResultsView gameResultsView = new GameResultsView(window.getContentPane().getWidth(), window.getContentPane().getHeight() , gameStats);

		gameResultsView.getContinueButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				launchStart();
			}
		});
		
		this.window.add(gameResultsView, Views.GAME_RESULTS_VIEW);
		CardLayout layout = (CardLayout) window.getContentPane().getLayout();
		layout.show(window.getContentPane(), Views.GAME_RESULTS_VIEW);
	}

}
