package ca.screenshot.formatcha;

import java.util.Collection;

/**
 * Created by plaguemorin on 22/07/15.
 * <p/>
 * Square board game with a number of players
 */
public interface InARowGame {
	int minimumNumberOfPlayers();

	int maximumNumberOfPlayers();

	InARowGameInstance startGame(final Collection<Player> players) throws GameException;
}
