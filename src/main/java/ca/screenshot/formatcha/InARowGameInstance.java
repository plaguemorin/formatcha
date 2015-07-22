package ca.screenshot.formatcha;

import java.util.Collection;

/**
 * Created by plaguemorin on 22/07/15.
 * <p/>
 * A started game
 */
public interface InARowGameInstance {
	/**
	 * Returns whether or not the game is still active
	 *
	 * @return true if the game is still active, false other wise
	 */
	boolean isRunning();

	/**
	 * Returns the players in this game
	 *
	 * @return the players in this game
	 */
	Collection<Player> getPlayers();

	/**
	 * Returns the board
	 *
	 * @return the board
	 */
	InARowBoard getBoard();

	/**
	 * Return the current player that can play
	 *
	 * @return the player who's turn it is
	 */
	Player currentTurn();

	/**
	 * If the game has ended, this returns the current winner
	 *
	 * @return the winner, null if the game didn't end yet
	 */
	Player winner();

	/**
	 * Processes a move by a player
	 *
	 * @param player the player that made the move
	 * @param x      the column the player made the move in
	 * @param y      the row the player made the move in
	 * @throws GameException if the move is invalid
	 */
	void play(final Player player, final int x, final int y) throws GameException;
}
