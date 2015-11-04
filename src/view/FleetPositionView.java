package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.TextField;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class FleetPositionView extends JPanel {
	private static final int MAIN_BUTTON_WIDTH = 200;
	private static final int MAIN_BUTTON_HEIGHT = 75;
	
	private static final int BOARD_PANEL_Y_OFFSET = 75;
	private static final int BOARD_PANEL_X_OFFSET = 25;			
	private static final int BOARD_CELL_SIZE = 50;
	
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
	
	private JLabel instructions;
	private BufferedImage background;
	
	public FleetPositionView(int width, int height) {
		this.setLayout(null);
		
		this.header = new JLabel("Choose the Position of your Fleet");
		this.header.setFont(new Font("Impact", Font.PLAIN, 24));
		this.header.setBounds(25, 25, 500, 25);
		this.add(this.header);
		
		this.instructions = new JLabel("<html>Instructions:<br>"
				+ "1. Select a ship<br>"
				+ "2. Click anywhere on the board to place it<br>"
				+ "3. Click the rotate button to rotate the ship<br></html>");
		this.instructions.setFont(new Font("Impact", Font.PLAIN, 20));
		this.instructions.setBounds(550, 25, 500, 200);
		this.add(this.instructions);
		/*this.textField = new JTextField("Choose the Position of your fleet");
		this.textField.setBounds(200, 0, 300, 100);
		this.add(this.textField);*/
		
		// Render the return to start button
		this.mainButton = new JButton("Back to Main Menu");
		this.mainButton.setFont(new Font("Impact", Font.PLAIN, 20));
		int mainButtonX = 25;
		int mainButtonY = (height)-(MAIN_BUTTON_HEIGHT)-25;
		this.mainButton.setBounds(mainButtonX, mainButtonY, MAIN_BUTTON_WIDTH, MAIN_BUTTON_HEIGHT);
		this.add(this.mainButton);
		
		this.doneButton = new JButton("Done");
		this.doneButton.setFont(new Font("Impact", Font.PLAIN, 20));
		int doneButtonX = (width)-(MAIN_BUTTON_WIDTH)-25;
		int doneButtonY = (height)-(MAIN_BUTTON_HEIGHT)-25;
		this.doneButton.setBounds(doneButtonX, doneButtonY, MAIN_BUTTON_WIDTH, MAIN_BUTTON_HEIGHT);
		this.add(this.doneButton);
		
		this.rotateButton = new JButton();
		this.rotateButton.setBounds(350, 4, 64, 64);
		this.rotateButton.setIcon(new ImageIcon("images/rotate_btn.png"));
		this.rotateButton.setBorder(BorderFactory.createEmptyBorder());
		this.add(this.rotateButton);
		
		this.boardPanel = new BoardPanel();
		// Not sure why, but have to add +1 or else the right and bottom border don't show
		int boardWidth = (BoardConstants.MAX_COLS*BOARD_CELL_SIZE)+1;
		int boardHeight = (BoardConstants.MAX_ROWS*BOARD_CELL_SIZE)+1;
		this.boardPanel.setBounds(BOARD_PANEL_X_OFFSET, BOARD_PANEL_Y_OFFSET, boardWidth, boardHeight);
		this.add(this.boardPanel);
		
		this.aircraftCarrierButton = new JButton();
		this.aircraftCarrierButton.setBounds(550, 200, MAIN_BUTTON_WIDTH, MAIN_BUTTON_HEIGHT);
		this.aircraftCarrierButton.setIcon(new ImageIcon("images/ac_btn.png"));
		this.aircraftCarrierButton.setAlignmentX(SwingConstants.CENTER);
		this.add(this.aircraftCarrierButton);
		
		this.battleshipButton = new JButton();
		this.battleshipButton.setBounds(550, 275, MAIN_BUTTON_WIDTH, MAIN_BUTTON_HEIGHT);
		this.battleshipButton.setIcon(new ImageIcon("images/bs_btn.png"));
		this.battleshipButton.setAlignmentX(SwingConstants.CENTER);
		this.add(this.battleshipButton);
		
		this.destroyerButton = new JButton();
		this.destroyerButton.setBounds(550, 350, MAIN_BUTTON_WIDTH, MAIN_BUTTON_HEIGHT);
		this.destroyerButton.setIcon(new ImageIcon("images/ds_btn.png"));
		this.destroyerButton.setAlignmentX(SwingConstants.CENTER);
		this.add(this.destroyerButton);
		
		this.subButton = new JButton();
		this.subButton.setBounds(550, 425, MAIN_BUTTON_WIDTH, MAIN_BUTTON_HEIGHT);
		this.subButton.setIcon(new ImageIcon("images/sub_btn.png"));
		this.subButton.setAlignmentX(SwingConstants.CENTER);
		this.add(this.subButton);
		
		this.patrolButton = new JButton();
		this.patrolButton.setBounds(550, 500, MAIN_BUTTON_WIDTH, MAIN_BUTTON_HEIGHT);
		this.patrolButton.setIcon(new ImageIcon("images/pt_btn.png"));
		this.patrolButton.setAlignmentX(SwingConstants.CENTER);
		this.add(this.patrolButton);
		
		
		board = new BoardPiece[BoardConstants.MAX_COLS][BoardConstants.MAX_ROWS];
		for (int col = 0; col < BoardConstants.MAX_COLS; col++) {
            for (int row = 0; row < BoardConstants.MAX_ROWS; row++) {
                BoardPiece cell = new BoardPiece(col*BOARD_CELL_SIZE, row*BOARD_CELL_SIZE, 
                		BOARD_CELL_SIZE, BOARD_CELL_SIZE, Color.GRAY);
                board[col][row] = cell;
            }
        }
		
		try {
			this.background = ImageIO.read(new File("images/fleet_bg.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(background, 0, 0, null);
	}
	
	private class BoardPanel extends JPanel {
		
		public BoardPanel() {
			this.setLayout(null);
		}
		
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D) g.create();
			
			for (int col = 0; col < BoardConstants.MAX_COLS; col++) {
	            for (int row = 0; row < BoardConstants.MAX_ROWS; row++) {
	            	g2d.setColor(board[col][row].getColor());
	            	if (board[col][row].getColor() != Color.GRAY) {
	            		g2d.fill(board[col][row]);
	            	} else {
	            		g2d.draw(board[col][row]);
	            	}
	            }
	        }
			
			//erase Board data after each paint
			for (int col = 0; col < BoardConstants.MAX_COLS; col++) {
	            for (int row = 0; row < BoardConstants.MAX_ROWS; row++) {
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

	public static int getBoardCellSize() {
		return BOARD_CELL_SIZE;
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
