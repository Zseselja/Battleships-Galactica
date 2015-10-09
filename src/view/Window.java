package view;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Window extends JFrame {
	private static final int WINDOW_HEIGHT = 700;
	private static final int WINDOW_WIDTH = 1000;
	private static final String TITLE = "Battleship Galactica";

	/**
	 * Build the main window that will hold our running application
	 */
	public Window() {
		super(TITLE);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.getContentPane().setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
		this.pack();
		this.setLocationRelativeTo(null);
	}
}
