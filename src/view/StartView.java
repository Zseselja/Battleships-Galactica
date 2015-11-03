package view;

import java.awt.Button;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;


public class StartView extends JPanel {
	private static final int START_BUTTON_WIDTH = 200;
	private static final int START_BUTTON_HEIGHT = 75;
	
	private static final int LEADER_BOARD_BUTTON_WIDTH = 200;
	private static final int LEADER_BOARD_BUTTON_HEIGHT = 75;
	
	private JButton startButton;
	private JButton leaderBoardButton;
	
	private BufferedImage background;
	
	public StartView(int width, int height) {
		this.setLayout(null);
		
		this.startButton = new JButton("Start");
		this.startButton.setFont(new Font("Impact", Font.PLAIN, 26));
		this.leaderBoardButton = new JButton("LeaderBoard");
		this.leaderBoardButton.setFont(new Font("Impact", Font.PLAIN, 26));
		
		int startButtonX = 50;
		int startButtonY = 400;
		this.startButton.setBounds(startButtonX, startButtonY, START_BUTTON_WIDTH, START_BUTTON_HEIGHT);
		this.add(this.startButton);
		
		int leaderBoardButtonX = 50;
		int leaderBoardButtonY = 500;
		this.leaderBoardButton.setBounds(leaderBoardButtonX, leaderBoardButtonY, LEADER_BOARD_BUTTON_WIDTH, LEADER_BOARD_BUTTON_HEIGHT);
		this.add(this.leaderBoardButton);
		
		try {
			this.background = ImageIO.read(new File("images/start_bg.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background, 0, 0, null);
	}

	public JButton getStartButton() {
		return startButton;
	}

	public JButton getLeaderBoardButton() {
		return leaderBoardButton;
	}
}
