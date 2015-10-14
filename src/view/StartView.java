package view;

import javax.swing.JButton;
import javax.swing.JPanel;


public class StartView extends JPanel {
	private static final int START_BUTTON_WIDTH = 200;
	private static final int START_BUTTON_HEIGHT = 75;
	
	private static final int LEADER_BOARD_BUTTON_WIDTH = 200;
	private static final int LEADER_BOARD_BUTTON_HEIGHT = 75;
	
	private JButton startButton;
	private JButton leaderBoardButton;
	
	public StartView(int width, int height) {
		this.setLayout(null);
		this.startButton = new JButton("Start");
		this.leaderBoardButton = new JButton("LeaderBoard");
		
		int startButtonX = (width/2)-(START_BUTTON_WIDTH/2);
		int startButtonY = (height/2)-(START_BUTTON_HEIGHT/2);
		this.startButton.setBounds(startButtonX, startButtonY, START_BUTTON_WIDTH, START_BUTTON_HEIGHT);
		this.add(this.startButton);
		
		int leaderBoardButtonX = (width/2)-(LEADER_BOARD_BUTTON_WIDTH/2);
		int leaderBoardButtonY = startButtonY + this.startButton.getHeight();
		this.leaderBoardButton.setBounds(leaderBoardButtonX, leaderBoardButtonY, LEADER_BOARD_BUTTON_WIDTH, LEADER_BOARD_BUTTON_HEIGHT);
		this.add(this.leaderBoardButton);
	}

	public JButton getStartButton() {
		return startButton;
	}

	public JButton getLeaderBoardButton() {
		return leaderBoardButton;
	}
}
