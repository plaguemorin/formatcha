package ca.screenshot.formatcha.tictactoe;

import ca.screenshot.formatcha.GameException;
import ca.screenshot.formatcha.InARowGame;
import ca.screenshot.formatcha.InARowGameInstance;
import ca.screenshot.formatcha.Player;

import java.util.Collection;

/**
 * Created by plaguemorin on 22/07/15.
 */
public class Game implements InARowGame {
	@Override
	public int minimumNumberOfPlayers() {
		return 2;
	}

	@Override
	public int maximumNumberOfPlayers() {
		return 2;
	}

	@Override
	public InARowGameInstance startGame(final Collection<Player> players) throws GameException {
		if (players.size() >= minimumNumberOfPlayers() && players.size() <= maximumNumberOfPlayers()) {
			return new GameInstance(players);
		}

		throw new GameException("Invalid number of players, gave " + players.size() + " but expected 2");
	}
}
