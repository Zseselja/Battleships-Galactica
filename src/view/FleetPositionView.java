package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FleetPositionView extends JPanel {
	private static final int MAIN_BUTTON_WIDTH = 200;
	private static final int MAIN_BUTTON_HEIGHT = 75;
	
	private static final int BOARD_PANEL_Y_OFFSET = 100;
	private static final int BOARD_PANEL_X_OFFSET = 100;			
	private static final int BOARD_PANEL_MAX_ROWS = 10;
	private static final int BOARD_PANEL_MAX_COLS = 10;
	private static final int BOARD_PANEL_CELL_SIZE = 50;
	
	private JTextField textField;
	private JLabel header;
	private JButton mainButton;
	private JButton doneButton;
	private JButton rotateButton;
	private JPanel boardPanel;
	private BoardPiece[][] board;
	
	private JButton aircraftCarrierButton;
	private JButton battleshipButton;
	private JButton destroyerButton;
	private JButton patrolButton;
	private JButton subButton;
	
	public FleetPositionView(int width, int height) {
		this.setLayout(null);
		
		this.header = new JLabel("Choose the Position of your Fleet");
		this.header.setFont(new Font("Arial Black", Font.PLAIN, 24));
		this.header.setBounds(100, 0, 500, 100);
		this.add(this.header);
		
		/*this.textField = new JTextField("Choose the Position of your fleet");
		this.textField.setBounds(200, 0, 300, 100);
		this.add(this.textField);*/
		
		// Render the return to start button
		this.mainButton = new JButton("Back to Main Menu");
		int mainButtonX = (width)-(MAIN_BUTTON_WIDTH);
		int mainButtonY = (height)-(MAIN_BUTTON_HEIGHT);
		this.mainButton.setBounds(mainButtonX, mainButtonY, MAIN_BUTTON_WIDTH, MAIN_BUTTON_HEIGHT);
		this.add(this.mainButton);
		
		this.doneButton = new JButton("Done");
		int doneButtonX = (width)-(2*MAIN_BUTTON_WIDTH);
		int doneButtonY = (height)-(MAIN_BUTTON_HEIGHT);
		this.doneButton.setBounds(doneButtonX, doneButtonY, MAIN_BUTTON_WIDTH, MAIN_BUTTON_HEIGHT);
		this.add(this.doneButton);
		
		this.rotateButton = new JButton("Rotate");
		this.rotateButton.setBounds(600, 0, MAIN_BUTTON_WIDTH, MAIN_BUTTON_HEIGHT);
		this.add(this.rotateButton);
		
		this.boardPanel = new BoardPanel();
		// Not sure why, but have to add +1 or else the right and bottom border don't show
		int boardWidth = (BOARD_PANEL_MAX_ROWS*BOARD_PANEL_CELL_SIZE)+1;
		int boardHeight = (BOARD_PANEL_MAX_COLS*BOARD_PANEL_CELL_SIZE)+1;
		this.boardPanel.setBounds(BOARD_PANEL_X_OFFSET, BOARD_PANEL_Y_OFFSET, boardWidth, boardHeight);
		this.add(this.boardPanel);
		
		this.aircraftCarrierButton = new JButton("Aircraft Carrier");
		this.aircraftCarrierButton.setBounds(800, 0, MAIN_BUTTON_WIDTH, MAIN_BUTTON_HEIGHT);
		this.add(this.aircraftCarrierButton);
		
		this.battleshipButton = new JButton("Battleship");
		this.battleshipButton.setBounds(800, 100, MAIN_BUTTON_WIDTH, MAIN_BUTTON_HEIGHT);
		this.add(this.battleshipButton);
		
		this.destroyerButton = new JButton("Destroyer");
		this.destroyerButton.setBounds(800, 200, MAIN_BUTTON_WIDTH, MAIN_BUTTON_HEIGHT);
		this.add(this.destroyerButton);
		
		this.subButton = new JButton("Sub");
		this.subButton.setBounds(800, 300, MAIN_BUTTON_WIDTH, MAIN_BUTTON_HEIGHT);
		this.add(this.subButton);
		
		this.patrolButton = new JButton("Patrol");
		this.patrolButton.setBounds(800, 400, MAIN_BUTTON_WIDTH, MAIN_BUTTON_HEIGHT);
		this.add(this.patrolButton);
		
		
		board = new BoardPiece[BOARD_PANEL_MAX_COLS][BOARD_PANEL_MAX_ROWS];
		for (int col = 0; col < BOARD_PANEL_MAX_COLS; col++) {
            for (int row = 0; row < BOARD_PANEL_MAX_ROWS; row++) {
                BoardPiece cell = new BoardPiece(col*BOARD_PANEL_CELL_SIZE, row*BOARD_PANEL_CELL_SIZE, 
                		BOARD_PANEL_CELL_SIZE, BOARD_PANEL_CELL_SIZE, Color.GRAY);
                board[col][row] = cell;
            }
        }
		
	}
	
	private class BoardPanel extends JPanel {
		
		public BoardPanel() {
			this.setLayout(null);
		}
		
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D) g.create();
			
			for (int col = 0; col < BOARD_PANEL_MAX_COLS; col++) {
	            for (int row = 0; row < BOARD_PANEL_MAX_ROWS; row++) {
	            	g2d.setColor(board[col][row].getColor());
	            	if (board[col][row].getColor() != Color.GRAY) {
	            		g2d.fill(board[col][row]);
	            	} else {
	            		g2d.draw(board[col][row]);
	            	}
	            }
	        }
			
			//erase Board data after each paint
			for (int col = 0; col < BOARD_PANEL_MAX_COLS; col++) {
	            for (int row = 0; row < BOARD_PANEL_MAX_ROWS; row++) {
	            	board[col][row].setColor(Color.GRAY);
	            }
	        }
		}
		
	}
	
	public BoardPiece[][] getBoard() {
		return board;
	}
	
	public JButton getRotateButton() {
		return rotateButton;
	}

	public JButton getMainButton() {
		return mainButton;
	}
	
	public JButton getDoneButton() {
		return doneButton;
	}

	public JTextField getTextField() {
		return textField;
	}

	public JPanel getBoardPanel() {
		return boardPanel;
	}

	public static int getMainButtonWidth() {
		return MAIN_BUTTON_WIDTH;
	}

	public static int getMainButtonHeight() {
		return MAIN_BUTTON_HEIGHT;
	}

	public static int getBoardPanelYOffset() {
		return BOARD_PANEL_Y_OFFSET;
	}

	public static int getBoardPanelXOffset() {
		return BOARD_PANEL_X_OFFSET;
	}

	public static int getBoardPanelMaxRows() {
		return BOARD_PANEL_MAX_ROWS;
	}

	public static int getBoardPanelMaxCols() {
		return BOARD_PANEL_MAX_COLS;
	}

	public static int getBoardPanelCellSize() {
		return BOARD_PANEL_CELL_SIZE;
	}

	public JButton getAircraftCarrierButton() {
		return aircraftCarrierButton;
	}

	public JButton getBattleshipButton() {
		return battleshipButton;
	}

	public JButton getDestroyerButton() {
		return destroyerButton;
	}

	public JButton getPatrolButton() {
		return patrolButton;
	}

	public JButton getSubButton() {
		return subButton;
	}

}
