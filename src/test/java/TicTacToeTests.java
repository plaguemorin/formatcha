import ca.screenshot.formatcha.GameException;
import ca.screenshot.formatcha.InARowBoard;
import ca.screenshot.formatcha.InARowGameInstance;
import ca.screenshot.formatcha.Player;
import ca.screenshot.formatcha.players.Human;
import ca.screenshot.formatcha.tictactoe.Game;
import org.junit.Before;
import org.junit.Test;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;

/**
 * Created by plaguemorin on 22/07/15.
 */
public class TicTacToeTests {
	private InARowGameInstance gameInstance;
	private Player alice;
	private Player bob;

	private static void dumpBoard(final InARowBoard board) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				final Player player = board.playerOn(i, j);
				if (player != null) {
					System.out.print(" " + player.getSymbolName() + " ");
				} else {
					System.out.print(" . ");
				}
			}

			System.out.println("");
		}
	}

	@Before
	public void setup() throws GameException {
		bob = new Human("Bob", "X");
		alice = new Human("Alice", "O");
		gameInstance = new Game().startGame(asList(bob, alice));
	}

	@Test(expected = GameException.class)
	public void replayMove() throws GameException {
		assertTrue(gameInstance.isRunning());

		// Player 1 should be starting
		assertEquals(gameInstance.currentTurn(), bob);

		// Make a winning move
		gameInstance.play(bob, 0, 0);
		gameInstance.play(alice, 0, 0);
	}

	@Test
	public void basicGame() throws GameException {
		assertTrue(gameInstance.isRunning());

		// Player 1 should be starting
		assertEquals(gameInstance.currentTurn(), bob);

		// Make a winning move
		gameInstance.play(bob, 0, 0);
		gameInstance.play(alice, 1, 0);
		gameInstance.play(bob, 0, 1);
		gameInstance.play(alice, 1, 1);
		gameInstance.play(bob, 0, 2); // WIN!

		// Dump, just for fun
		dumpBoard(gameInstance.getBoard());

		// Make sure the game is done
		assertFalse(gameInstance.isRunning());

		// Make sure bob is listed as won
		assertEquals(gameInstance.winner(), bob);
	}
}
