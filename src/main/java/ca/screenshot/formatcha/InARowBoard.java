package ca.screenshot.formatcha;

/**
 * Created by plaguemorin on 22/07/15.
 * The view of a board for a player
 */
public interface InARowBoard {
	boolean isEmpty(int x, int y);

	Player playerOn(int x, int y);
}
