package view;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import model.stats;




public class GameResultsView extends JPanel {
	private static final int MAIN_BUTTON_WIDTH = 200;
	private static final int MAIN_BUTTON_HEIGHT = 75;
	
	public BufferedImage background;
	private JButton continueButton;
	
	public GameResultsView(int width, int height , stats gameStats) {
		
		this.setLayout(null);
		System.out.print("in game results view");
		JTextField textField = new JTextField("    GameResults View");
		
		JTextField shots = new JTextField("    Shots Taken: " + gameStats.getShot());
		double acc  = ((double)gameStats.getHit() / (double)gameStats.getShot())* 100;
		String accString = String.format( "%1$,.2f", acc );
		JTextField accuracy = new JTextField( "    Accuracy: " + accString + "%");

		textField.setBounds(100, 100, 200, 100);
		accuracy.setBounds(200, 300, 200, 100);
		shots.setBounds(400, 300, 200, 100);
		this.add(textField);
		this.add(shots);
		this.add(accuracy);
		try {
			if(gameStats.win == true){
				this.background = ImageIO.read(new File("images/win_endSC.png"));
			}else{
				this.background = ImageIO.read(new File("images/loose_endSC.png"));
			}
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
		
		
		
		
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
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background, 0, 0, null);
	}
}
