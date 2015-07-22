package ca.screenshot.formatcha.tictactoe;

import ca.screenshot.formatcha.GameException;
import ca.screenshot.formatcha.InARowGameInstance;
import ca.screenshot.formatcha.InARowBoard;
import ca.screenshot.formatcha.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Created by plaguemorin on 22/07/15.
 * Basic tic tac toe logic
 */
public class GameInstance implements InARowGameInstance {
	private static final Logger LOGGER = LoggerFactory.getLogger(GameInstance.class);

	private long gameId;

	private Map<Player, BitmapBoard> board;
	private List<Player> playerCache;
	private InARowBoard wholeBoard;
	private boolean gameIsDone;

	private short currentPlayer;

	GameInstance(final Collection<Player> players) {
		this.gameId = (long) (Math.random() * Long.MAX_VALUE);

		// Build the boards per player
		this.board = new HashMap<>(2);
		for (final Player player : players) {
			this.board.put(player, new BitmapBoard());
		}

		// Game isn't done, we just started !
		this.gameIsDone = false;

		// Player 1 always starts; because I said so
		currentPlayer = 0;
		playerCache = new ArrayList<>(players);

		// Make a simple board inner-class
		this.wholeBoard = new InARowBoard() {
			@Override
			public boolean isEmpty(final int x, final int y) {
				for (final Map.Entry<Player, BitmapBoard> playerBoard : GameInstance.this.board.entrySet()) {
					if (!playerBoard.getValue().isEmpty(x, y)) {
						return false;
					}
				}
				return true;
			}

			@Override
			public Player playerOn(final int x, final int y) {
				for (final Map.Entry<Player, BitmapBoard> playerBoard : GameInstance.this.board.entrySet()) {
					if (!playerBoard.getValue().isEmpty(x, y)) {
						return playerBoard.getKey();
					}
				}
				return null;
			}
		};

		LOGGER.info("Started a game with {} players, id = {}", players.size(), this.gameId);
	}

	@Override
	public boolean isRunning() {
		return !this.gameIsDone;
	}

	@Override
	public Collection<Player> getPlayers() {
		return Collections.unmodifiableCollection(this.board.keySet());
	}

	@Override
	public InARowBoard getBoard() {
		return wholeBoard;
	}

	@Override
	public Player currentTurn() {
		return playerCache.get(currentPlayer);
	}

	@Override
	public Player winner() {
		// Find the winner; it's the first player who has a line
		for (final Map.Entry<Player, BitmapBoard> playerBoard : board.entrySet()) {
			if (playerBoard.getValue().hasLine()) {
				return playerBoard.getKey();
			}
		}

		return null;
	}

	@Override
	public synchronized void play(final Player player, final int x, final int y) throws GameException {
		LOGGER.info("[GAME{}] Player {} wants to place it's symbol on {}, {}", gameId, player.getName(), x, y);
		// Make sure players can't play pass the end
		if (!isRunning()) {
			throw new GameException("Game has ended");
		}

		// Make sure it's the current player's turn
		if (playerCache.indexOf(player) != currentPlayer) {
			throw new GameException("This player cannot play currently");
		}

		// Make sure the spot is free
		for (final Map.Entry<Player, BitmapBoard> playerBoard : this.board.entrySet()) {
			if (!playerBoard.getValue().isEmpty(x, y)) {
				throw new GameException("Cannot replay a move");
			}
		}

		// Get the board for this player
		final BitmapBoard boardForPlayer = this.board.get(player);
		if (boardForPlayer == null) {
			throw new GameException("Invalid player!");
		}

		// Now that we know it's free, update the players view of the board
		boardForPlayer.putPlayerOn(x, y);

		// Did we win ? Update the global game life
		this.gameIsDone = boardForPlayer.hasLine() || !stillHasEmptyCells();
		if (this.gameIsDone) {
			LOGGER.info("Game is done");
		}

		// Move on to the next player
		this.currentPlayer = (short) ((this.currentPlayer + 1) % this.board.size());
	}

	private boolean stillHasEmptyCells() {
		// Here we cheat a bit, using the whole board
		final InARowBoard wholeBoard = this.getBoard();
		for (int bx = 0; bx < 3; bx++) {
			for (int by = 0; by < 3; by++) {
				if (wholeBoard.isEmpty(bx, by)) {
					return true;
				}
			}
		}

		return false;
	}

}
