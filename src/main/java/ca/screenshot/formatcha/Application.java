package ca.screenshot.formatcha;

import ca.screenshot.formatcha.tictactoe.Game;
import ca.screenshot.formatcha.ui.swing.MainWindow;

/**
 * Created by plaguemorin on 22/07/15.
 */
public class Application {

	public static void main(String args[]) {
		new MainWindow(new Game()).setVisible(true);
	}
}
