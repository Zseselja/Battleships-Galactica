package view;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.stats;

public class GameResultsView extends JPanel {
	private static final int MAIN_BUTTON_WIDTH = 200;
	private static final int MAIN_BUTTON_HEIGHT = 75;
	
	private JButton continueButton;
	
	public GameResultsView(int width, int height , stats gameStats) {
		this.setLayout(null);
		System.out.print("in game results view");
		JTextField textField = new JTextField("    GameResults View");
		JTextField shots = new JTextField("    Shots Taken: " + gameStats.getShot());
		//double acc  = gameStats.getHit() / gameStats.getShot();
		JTextField accuracy = new JTextField("    Accuracy: " + 0);
		textField.setBounds(100, 100, 200, 100);
		accuracy.setBounds(200, 300, 200, 100);
		shots.setBounds(400, 300, 200, 100);
		this.add(textField);
		this.add(shots);
		this.add(accuracy);
		
		
		
		this.continueButton = new JButton("Continue");
		this.continueButton.setFont(new Font("Impact", Font.PLAIN, 20));
		int continueButtonX = (width)-(MAIN_BUTTON_WIDTH)-25;
		int continueButtonY = (height)-(MAIN_BUTTON_HEIGHT)-25;
		this.continueButton.setBounds(continueButtonX, continueButtonY, MAIN_BUTTON_WIDTH, MAIN_BUTTON_HEIGHT);
		this.add(this.continueButton);
	}

	public JButton getContinueButton() {
		return continueButton;
	}
	
}
