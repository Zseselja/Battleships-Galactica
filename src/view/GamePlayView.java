package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GamePlayView extends JPanel {
	private static final int BOARD_CELL_SIZE = 35;
	private static final int PLAYER_BOARD_PANEL_Y_OFFSET = 100;
	private static final int PLAYER_BOARD_PANEL_X_OFFSET = 100;
	private static final int COMPUTER_BOARD_PANEL_Y_OFFSET = 100;
	private static final int COMPUTER_BOARD_PANEL_X_OFFSET = 500;
	
	private JPanel playerBoardPanel;
	private BoardPiece[][] playerBoard;
	
	private JPanel computerBoardPanel;
	private BoardPiece[][] computerBoard;
	
	public GamePlayView(int width, int height) {
		this.setLayout(null);
		
		this.playerBoardPanel = new PlayerBoardPanel();
		int boardWidth = (BoardConstants.MAX_COLS*BOARD_CELL_SIZE)+1;
		int boardHeight = (BoardConstants.MAX_ROWS*BOARD_CELL_SIZE)+1;
		this.playerBoardPanel.setBounds(PLAYER_BOARD_PANEL_X_OFFSET, PLAYER_BOARD_PANEL_Y_OFFSET, boardWidth, boardHeight);
		this.add(this.playerBoardPanel);
		
		this.playerBoard = new BoardPiece[BoardConstants.MAX_COLS][BoardConstants.MAX_ROWS];
		for (int col = 0; col < BoardConstants.MAX_COLS; col++) {
            for (int row = 0; row < BoardConstants.MAX_ROWS; row++) {
                BoardPiece cell = new BoardPiece(col*BOARD_CELL_SIZE, row*BOARD_CELL_SIZE, 
                		BOARD_CELL_SIZE, BOARD_CELL_SIZE, Color.GRAY);
                this.playerBoard[col][row] = cell;
            }
        }
		
		this.computerBoardPanel = new ComputerBoardPanel();
		this.computerBoardPanel.setBounds(COMPUTER_BOARD_PANEL_X_OFFSET, COMPUTER_BOARD_PANEL_Y_OFFSET, boardWidth, boardHeight);
		this.add(this.computerBoardPanel);
		
		this.computerBoard = new BoardPiece[BoardConstants.MAX_COLS][BoardConstants.MAX_ROWS];
		for (int col = 0; col < BoardConstants.MAX_COLS; col++) {
            for (int row = 0; row < BoardConstants.MAX_ROWS; row++) {
                BoardPiece cell = new BoardPiece(col*BOARD_CELL_SIZE, row*BOARD_CELL_SIZE, 
                		BOARD_CELL_SIZE, BOARD_CELL_SIZE, Color.GRAY);
                this.computerBoard[col][row] = cell;
            }
        }
	}
	
	private class PlayerBoardPanel extends JPanel {
		
		public PlayerBoardPanel() {
			this.setLayout(null);
		}
		
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D) g.create();
			
			for (int col = 0; col < BoardConstants.MAX_COLS; col++) {
	            for (int row = 0; row < BoardConstants.MAX_ROWS; row++) {
	            	g2d.setColor(playerBoard[col][row].getColor());
	            	if (playerBoard[col][row].getColor() != Color.GRAY) {
	            		g2d.fill(playerBoard[col][row]);
	            	} else {
	            		g2d.draw(playerBoard[col][row]);
	            	}
	            }
	        }
			
			//erase Board data after each paint
			for (int col = 0; col < BoardConstants.MAX_COLS; col++) {
	            for (int row = 0; row < BoardConstants.MAX_ROWS; row++) {
	            	playerBoard[col][row].setColor(Color.GRAY);
	            }
	        }
		}
	}
	
	private class ComputerBoardPanel extends JPanel {
		
		public ComputerBoardPanel() {
			this.setLayout(null);
		}
		
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D) g.create();
			
			for (int col = 0; col < BoardConstants.MAX_COLS; col++) {
	            for (int row = 0; row < BoardConstants.MAX_ROWS; row++) {
	            	g2d.setColor(computerBoard[col][row].getColor());
	            	if (computerBoard[col][row].getColor() != Color.GRAY) {
	            		g2d.fill(computerBoard[col][row]);
	            	} else {
	            		g2d.draw(computerBoard[col][row]);
	            	}
	            }
	        }
			
			//erase Board data after each paint
			for (int col = 0; col < BoardConstants.MAX_COLS; col++) {
	            for (int row = 0; row < BoardConstants.MAX_ROWS; row++) {
	            	computerBoard[col][row].setColor(Color.GRAY);
	            }
	        }
		}
	}

	public JPanel getPlayerBoardPanel() {
		return playerBoardPanel;
	}

	public BoardPiece[][] getPlayerBoard() {
		return playerBoard;
	}

	public JPanel getComputerBoardPanel() {
		return computerBoardPanel;
	}

	public BoardPiece[][] getComputerBoard() {
		return computerBoard;
	}

	public static int getBoardCellSize() {
		return BOARD_CELL_SIZE;
	}

}
