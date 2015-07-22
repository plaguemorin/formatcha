package ca.screenshot.formatcha.ui.swing;

import ca.screenshot.formatcha.InARowBoard;
import ca.screenshot.formatcha.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by plaguemorin on 22/07/15.
 */
public class TicTacToeBoard extends JPanel {
	private InARowBoard board;
	private JButton[] positions;

	public TicTacToeBoard(final InARowBoard board, final GameUIUpdate updates) {
		this.board = board;
		this.positions = new JButton[9];

		setLayout(new GridLayout(3, 3));

		// Setup each button
		for (int i = 0; i < this.positions.length; i++) {
			this.positions[i] = new JButton();

			final int x = i % 3;
			final int y = i / 3;

			this.positions[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(final ActionEvent actionEvent) {
					updates.actionOn(x, y);
				}
			});

			this.add(this.positions[i]);
		}

	}

	public void update() {
		// Loop and update the labels
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				final Player player = board.playerOn(i, j);
				final AbstractButton label = positions[i + j * 3];

				if (player != null) {
					label.setText(player.getSymbolName());
					label.setEnabled(false);
				} else {
					label.setText("");
				}
			}
		}
	}


}
