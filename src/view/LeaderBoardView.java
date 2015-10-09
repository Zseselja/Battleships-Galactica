package view;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class LeaderBoardView extends JPanel {
	private static final int MAIN_BUTTON_WIDTH = 200;
	private static final int MAIN_BUTTON_HEIGHT = 75;
	
	private JButton mainButton;
	
	public LeaderBoardView(int width, int height) {
		this.setLayout(null);
		JTextField textField = new JTextField("Leader Board");
		textField.setBounds(100, 100, 100, 100);
		this.add(textField);
		
		this.mainButton = new JButton("Back to Main Menu");
		
		int mainButtonX = (width)-(MAIN_BUTTON_WIDTH);
		int mainButtonY = (height)-(MAIN_BUTTON_HEIGHT);
		this.mainButton.setBounds(mainButtonX, mainButtonY, MAIN_BUTTON_WIDTH, MAIN_BUTTON_HEIGHT);
		this.add(this.mainButton);
	}
	
	public JButton getMainButton() {
		return mainButton;
	}
	
}