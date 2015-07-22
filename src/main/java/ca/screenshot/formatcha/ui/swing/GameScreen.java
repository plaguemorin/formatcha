package ca.screenshot.formatcha.ui.swing;

import ca.screenshot.formatcha.GameException;
import ca.screenshot.formatcha.InARowGameInstance;

import javax.swing.*;
import java.awt.*;

/**
 * Created by plaguemorin on 22/07/15.
 */
public class GameScreen extends JPanel implements GameUIUpdate {
	private InARowGameInstance gameInstance;
	private JLabel currentPlayer;
	private TicTacToeBoard board;

	public GameScreen(final InARowGameInstance gameInstance) {
		this.gameInstance = gameInstance;
		board = new TicTacToeBoard(this.gameInstance.getBoard(), this);
		currentPlayer = new JLabel();

		setLayout(new BorderLayout());

		add(board, BorderLayout.CENTER);
		add(this.currentPlayer, BorderLayout.SOUTH);

		update();
	}

	@Override
	public void actionOn(final int x, final int y) {
		try {
			gameInstance.play(gameInstance.currentTurn(), x, y);
		} catch (GameException e) {
			e.printStackTrace();
		}

		update();
	}

	public void update() {
		if (gameInstance.isRunning()) {
			this.currentPlayer.setText("Current player: " + gameInstance.currentTurn().getName());
			board.update();
		} else {
			remove(board);
			if (gameInstance.winner() != null) {
				add(new JLabel("The winner is:\n" + gameInstance.winner().getName()));
			} else {
				add(new JLabel("There is no winner :("), BorderLayout.CENTER);
			}
			this.currentPlayer.setText("Game has ended !");
		}
	}
}
