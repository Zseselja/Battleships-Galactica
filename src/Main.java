import javax.swing.SwingUtilities;

import controller.GameController;
import view.StartView;
import view.Window;


public class Main 
{
    public static void main(String[] args) throws InterruptedException 
    {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				//GameModel model = new GameModel();
				
				Window gameWindow = new Window();
				
				GameController controller = new GameController(gameWindow);
			}
		});
    }
}
