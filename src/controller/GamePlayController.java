package controller;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.Random;

import model.Computer;
import model.GamePlayModel;
import model.Player;
import model.Ship;
import model.ShipList;
import model.Shot;
import view.BoardConstants;
import view.GamePlayView;

public class GamePlayController {
	private GamePlayModel model;
	private GamePlayView view;

	public GamePlayController(GamePlayModel m, GamePlayView v) {
		this.model = m;
		this.view = v;
		
		setUpViewEvents();
		renderView();
	}
	
	private void renderView() {
		renderPlayerBoard();
		renderComputerBoard();
		this.view.repaint();
	}
	
	private void renderComputerBoard() {
		for (Shot s: this.model.getPlayer().getShots()) {
			if (s.isHit()) {
				this.view.getComputerBoard()[s.x][s.y].setColor(Color.BLACK);
			} else {
				this.view.getComputerBoard()[s.x][s.y].setColor(Color.BLUE);
			}
		}
	}
	
	private void renderPlayerBoard() {
		for (Ship s: model.getPlayer().getShips()) {
			if (s.isVertical()) {
				int col = s.getHead().x;
				int row = s.getHead().y;
				int endRow = s.getTail().y;
				while (row <= endRow) {
					this.view.getPlayerBoard()[col][row++].setColor(Color.RED);
				}
			} else {
				int row = s.getHead().y;
				int col = s.getHead().x;
				int endCol = s.getTail().x;
				while (col <= endCol) {
					this.view.getPlayerBoard()[col++][row].setColor(Color.RED);
				}
			}
		}
		
		for (Shot s: this.model.getComputer().getShots()) {
			if (s.isHit()) {
				this.view.getPlayerBoard()[s.x][s.y].setColor(Color.BLACK);
			} else {
				this.view.getPlayerBoard()[s.x][s.y].setColor(Color.BLUE);
			}
		}
	}
	
	private void setUpViewEvents() {
		view.getComputerBoardPanel().addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {}

			@Override
			public void mousePressed(MouseEvent e) {}

			@Override
			public void mouseReleased(MouseEvent e) {
				Computer computer = model.getComputer();
				Player player = model.getPlayer();
				
				ShipList computerShips = computer.getShips();
				ShipList playerShips = player.getShips();
				
				List<Shot> computerShots = computer.getShots();
				List<Shot> playerShots = player.getShots();
				
				int col = e.getX()/GamePlayView.getBoardCellSize();
				int row = e.getY()/GamePlayView.getBoardCellSize();
				Shot shot = new Shot(col, row);
				
				if (playerShots.contains(shot)) {
					System.out.println("Already shot there");
					return;
				} else {
					playerShots.add(shot);
				}

				Ship ship = computerShips.getIntersectingShip(shot);
				if (ship == null) {
					System.out.println("You Missed");
					
					boolean fireAShot = true;
					while (fireAShot) {
						do {
							shot = generateRandomShot();
						} while (computerShots.contains(shot));
						computerShots.add(shot);
						
						ship = playerShips.getIntersectingShip(shot);
						if (ship == null) {
							System.out.println("Computer Missed");
							fireAShot = false;
						} else {
							System.out.println("Computer Hit");
							ship.decreaseHealth();
							shot.setHit(true);
							if (playerShips.isAllShipsSunk()) {
								//Win sequence
								System.out.println("Computer wins");
							}
						}
					}
				} else {
					System.out.println("You hit a ship");
					ship.decreaseHealth();
					shot.setHit(true);
					if (computerShips.isAllShipsSunk()) {
						//Win sequence
						System.out.println("Player wins");
					}
				}
				renderView();
			}

			@Override
			public void mouseEntered(MouseEvent e) {}

			@Override
			public void mouseExited(MouseEvent e) {}
			
		});
		
	}
	
	private Shot generateRandomShot() {
		Random r = new Random();
		
		int x = r.nextInt(BoardConstants.MAX_COLS);
		int y = r.nextInt(BoardConstants.MAX_ROWS);
		
		return new Shot(x, y);
	}
}
