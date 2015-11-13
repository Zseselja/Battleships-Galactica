package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.Timer;

import model.Computer;
import model.GamePlayModel;
import model.Player;
import model.Ship;
import model.ShipList;
import model.Shot;
import view.BoardConstants;
import view.GamePlayView;

public class GamePlayController {
	private static final int COMPUTER_TURN_DELAY = 250;
	
	private GamePlayModel model;
	private GamePlayView view;
	private Timer computerTurn;

	public GamePlayController(GamePlayModel m, GamePlayView v) {
		this.model = m;
		this.view = v;
		
		setUpComputer();
		setUpViewEvents();
		renderView();
	}
	
	private void setUpComputer() {
		this.computerTurn = new Timer(COMPUTER_TURN_DELAY, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Computer computer = model.getComputer();
				Player player = model.getPlayer();
				ShipList playerShips = player.getShips();
				
				List<Shot> computerShots = computer.getShots();
				List<Shot> priorityShots = computer.getPriorityShots(); 
				boolean fireAShot = true;
				Shot shot;
				Ship ship;
				while (fireAShot) {
					if (priorityShots.isEmpty()) {
						//Generate a random shot
						do {
							shot = generateRandomShot();
						} while (computerShots.contains(shot));
						computerShots.add(shot);
						
					} else {
						//Fire priority shot
						shot = computer.getPriorityShot();
						if (shot != null) {
							computer.getPriorityShots().remove(0);
							computerShots.add(shot);
						}
					}
					
					ship = playerShips.getIntersectingShip(shot);
					if (ship == null) {
						System.out.println("Computer Missed");
						fireAShot = false;
					} else {
						System.out.println("Computer Hit");
						ship.decreaseHealth();
						shot.setHit(true);
						
						List<Shot> tempPriorityShots = generatePriorityShots(ship, shot);
						for (Shot p: tempPriorityShots) {
							if (!computerShots.contains(p)) {
								priorityShots.add(p);
							}
						}
						
						if (playerShips.isAllShipsSunk()) {
							//Win sequence
							System.out.println("Computer wins");
							computer.setWin(true);
						}
						
						
					}
				}

				renderView();
				computerTurn.stop();
			}
		});
	}
	
	private void renderView() {
		renderPlayerBoard();
		renderComputerBoard();
		
		if (model.getPlayer().isWin()) {
			view.getWinnerLabel().setText("Player Wins!!!");
			view.getContinueButton().setVisible(true);
		} else if (model.getComputer().isWin()) {
			view.getWinnerLabel().setText("Computer Wins!!!");
			view.getContinueButton().setVisible(true);
		}
		
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
				if (computerTurn.isRunning()) return;
				Computer computer = model.getComputer();
				Player player = model.getPlayer();
				
				if (player.isWin() || computer.isWin()) return;
				
				ShipList computerShips = computer.getShips();
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
					computerTurn.start();
				} else {
					System.out.println("You hit a ship");
					ship.decreaseHealth();
					shot.setHit(true);
					if (computerShips.isAllShipsSunk()) {
						//Win sequence
						System.out.println("Player wins");
						player.setWin(true);
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
	
	private List<Shot> generatePriorityShots(Ship ship, Shot shot) {
		List<Shot> priorityShots = new ArrayList<Shot>();
		if (ship.getNumHits() > 1) {
			if (ship.isVertical()) {
				if (shot.y > 0) {
					Shot pShot = new Shot(shot.x, shot.y-1);
					priorityShots.add(pShot);
				}
				if (shot.y < BoardConstants.MAX_ROWS-1) {
					Shot pShot = new Shot(shot.x, shot.y+1);
					priorityShots.add(pShot);
				}
			} else {
				if (shot.x > 0) {
					Shot pShot = new Shot(shot.x-1, shot.y);
					priorityShots.add(pShot);
				}
				if (shot.x < BoardConstants.MAX_COLS-1) {
					Shot pShot = new Shot(shot.x+1, shot.y);
					priorityShots.add(pShot);
				}
			}
		} else {
			if (shot.y > 0) {
				Shot pShot = new Shot(shot.x, shot.y-1);
				priorityShots.add(pShot);
			}
			if (shot.y < BoardConstants.MAX_ROWS-1) {
				Shot pShot = new Shot(shot.x, shot.y+1);
				priorityShots.add(pShot);
			}
			if (shot.x > 0) {
				Shot pShot = new Shot(shot.x-1, shot.y);
				priorityShots.add(pShot);
			}
			if (shot.x < BoardConstants.MAX_COLS-1) {
				Shot pShot = new Shot(shot.x+1, shot.y);
				priorityShots.add(pShot);
			}
		}
		
		return priorityShots;
	}
}
