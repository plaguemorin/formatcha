package ca.screenshot.formatcha.ui.swing;

import ca.screenshot.formatcha.GameException;
import ca.screenshot.formatcha.GameUI;
import ca.screenshot.formatcha.InARowGame;
import ca.screenshot.formatcha.Player;
import ca.screenshot.formatcha.players.Human;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Collection;

/**
 * Created by plaguemorin on 22/07/15.
 * Swing based UI
 * I'm so sorry
 */
public class MainWindow extends JFrame implements GameUI {
	private InARowGame rowGame;

	private BeforeGame setupScreen;
	private GameScreen gameScreen;

	public MainWindow(final InARowGame rowGame) throws HeadlessException {
		super("TicTacToe");
		this.rowGame = rowGame;

		setSize(320, 240);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		setupScreen = new BeforeGame() {
			@Override
			public void startGameWith(final String p1Name, final String p2Name) {
				MainWindow.this.setupGameScreenForNewGame(Arrays.<Player>asList(new Human(p1Name, "X"), new Human(p2Name, "O")));
			}
		};
		this.add(setupScreen, BorderLayout.CENTER);
	}

	private void setupGameScreenForNewGame(final Collection<Player> players) {
		remove(setupScreen);
		setupScreen = null;
		try {
			this.add(new GameScreen(this.rowGame.startGame(players)), BorderLayout.CENTER);
		} catch (GameException e) {
			e.printStackTrace();
		}
	}
}
