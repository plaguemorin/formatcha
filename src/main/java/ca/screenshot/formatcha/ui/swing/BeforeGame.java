package ca.screenshot.formatcha.ui.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by plaguemorin on 22/07/15.
 */
public abstract class BeforeGame extends JPanel {

	public BeforeGame() throws HeadlessException {
		final JLabel player1NameLbl = new JLabel("Player 1 name:");
		final JLabel player2NameLbl = new JLabel("Player 2 name:");
		final JTextField player2Name = new JTextField();
		final JTextField player1Name = new JTextField();
		final JButton startGame = new JButton("Start Game");

		setLayout(new GridLayout(0, 2));

		add(player1NameLbl);
		add(player1Name);
		add(player2NameLbl);
		add(player2Name);

		add(startGame);

		startGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent actionEvent) {
				if (player1Name.getText().isEmpty()) {
					// Error
					return;
				}
				if (player2Name.getText().isEmpty()) {
					// Error
					return;
				}

				startGameWith(player1Name.getText(), player2Name.getText());
			}
		});
	}

	public abstract void startGameWith(final String p1Name, final String p2Name);
}
