package ca.screenshot.formatcha.players;

import ca.screenshot.formatcha.Player;

/**
 * Created by plaguemorin on 22/07/15.
 * Basic Player
 */
public class Human implements Player {
	private final String name;
	private final String sym;

	public Human(final String name, final String symbol) {
		this.name = name;
		this.sym = symbol;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String getSymbolName() {
		return this.sym;
	}

	@Override
	public boolean equals(final Player player) {
		return this == player
				|| player instanceof Human
				&& (this.name.equals(player.getName()) && this.sym.equals(player.getSymbolName()));

	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		final Human human = (Human) o;

		if (name != null ? !name.equals(human.name) : human.name != null) return false;
		return !(sym != null ? !sym.equals(human.sym) : human.sym != null);

	}

	@Override
	public int hashCode() {
		int result = name != null ? name.hashCode() : 0;
		result = 31 * result + (sym != null ? sym.hashCode() : 0);
		return result;
	}
}
