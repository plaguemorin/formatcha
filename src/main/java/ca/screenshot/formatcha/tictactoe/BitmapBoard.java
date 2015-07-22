package ca.screenshot.formatcha.tictactoe;

/**
 * Created by plaguemorin on 22/07/15.
 * <p/>
 * Tic Tac Toe implemented in a simple number:
 * <p/>
 * x = col
 * y = row
 * <p/>
 * 0   1   2
 * +---+---+---+
 * 0 | 0 | 1 | 2 |
 * +---+---+---+
 * 1 | 3 | 4 | 5 |
 * +---+---+---+
 * 2 | 6 | 7 | 8 |
 * +---+---+---+
 */
public class BitmapBoard {
	// The winning possibilities don't change per player, let's just make it static
	private static boolean[] winningPossibilities = new boolean[1 << 9];

	static {
		// Horizontal Lines
		winningPossibilities[numberForPosition(0, 0) | numberForPosition(0, 1) | numberForPosition(0, 2)] = true;
		winningPossibilities[numberForPosition(1, 0) | numberForPosition(1, 1) | numberForPosition(1, 2)] = true;
		winningPossibilities[numberForPosition(2, 0) | numberForPosition(2, 1) | numberForPosition(2, 2)] = true;

		// Vertical Lines
		winningPossibilities[numberForPosition(0, 0) | numberForPosition(1, 0) | numberForPosition(2, 0)] = true;
		winningPossibilities[numberForPosition(0, 1) | numberForPosition(1, 1) | numberForPosition(2, 1)] = true;
		winningPossibilities[numberForPosition(0, 2) | numberForPosition(1, 2) | numberForPosition(2, 2)] = true;

		// Diagonals
		winningPossibilities[numberForPosition(0, 0) | numberForPosition(1, 1) | numberForPosition(2, 2)] = true;
		winningPossibilities[numberForPosition(2, 0) | numberForPosition(1, 1) | numberForPosition(0, 2)] = true;
	}

	// We store a 3 x 3 array in here; each position can be: Used or Empty
	private int position;

	public BitmapBoard() {
		position = 0;
	}

	private static int numberForPosition(int x, int y) {
		int pos = x + y * 3;
		if (pos < 0 || pos > 8) {
			throw new RuntimeException("Invalid position");
		}

		return 1 << pos;
	}

	public boolean isEmpty(int x, int y) {
		return (position & numberForPosition(x, y)) == 0;
	}

	public void putPlayerOn(int x, int y) {
		position |= numberForPosition(x, y);
	}

	public boolean hasLine() {
		return winningPossibilities[position];
	}
}
