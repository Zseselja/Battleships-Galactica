package view;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FleetPositionView extends JPanel {
	private static final int MAIN_BUTTON_WIDTH = 200;
	private static final int MAIN_BUTTON_HEIGHT = 75;
	
	private JTextField textField;
	private JButton mainButton;
	private JButton manipulateModelButton;
	
	public FleetPositionView(int width, int height) {
		this.setLayout(null);
		this.textField = new JTextField("Choose the Position of your fleet");
		this.textField.setBounds(100, 100, 300, 100);
		this.add(this.textField);
		
		// Render the return to start button
		this.mainButton = new JButton("Back to Main Menu");
		
		int mainButtonX = (width)-(MAIN_BUTTON_WIDTH);
		int mainButtonY = (height)-(MAIN_BUTTON_HEIGHT);
		this.mainButton.setBounds(mainButtonX, mainButtonY, MAIN_BUTTON_WIDTH, MAIN_BUTTON_HEIGHT);
		this.add(this.mainButton);
		
		// Render the (demo) manipulateModel button
		this.manipulateModelButton = new JButton("Do the thing!");
		
		this.manipulateModelButton.setBounds(0, 0, MAIN_BUTTON_WIDTH, MAIN_BUTTON_HEIGHT);
		this.add(this.manipulateModelButton);
	}

	public JButton getMainButton() {
		return mainButton;
	}
	
	public JButton getManipulateModelButton() {
		return manipulateModelButton;
	}
	
	public JTextField getTextField() {
		return textField;
	}
}
