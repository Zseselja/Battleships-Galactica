package view;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GameResultsView extends JPanel {
	private static final int MAIN_BUTTON_WIDTH = 200;
	private static final int MAIN_BUTTON_HEIGHT = 75;
	
	private JButton continueButton;
	
	public GameResultsView(int width, int height) {
		this.setLayout(null);
		JTextField textField = new JTextField("GameResults View");
		textField.setBounds(100, 100, 200, 100);
		this.add(textField);
		
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
