package ca.screenshot.formatcha;

/**
 * Created by plaguemorin on 22/07/15.
 */
public class GameException extends Exception {
	public GameException() {
	}

	public GameException(final String message) {
		super(message);
	}

	public GameException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public GameException(final Throwable cause) {
		super(cause);
	}

	public GameException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
