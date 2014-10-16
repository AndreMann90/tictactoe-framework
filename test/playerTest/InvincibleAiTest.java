package playerTest;

import static org.junit.Assert.assertFalse;
import gui.Display;

import java.awt.Point;
import java.util.Optional;

import org.junit.Test;

import players.PlayerProperties;
import players.PlayerProperties.PlayerBuilder;
import tictactoeGame.Game;
import tictactoeGame.Move;

public class InvincibleAiTest {

	@Test
	public void invincibleTest() {
		PlayerProperties playerOne = new PlayerBuilder('x').setHuman(false)
				.create();
		PlayerProperties playerTwo = new PlayerBuilder('o').setHuman(false)
				.create();

		Game game = new Game(display);

		final int max = 362880; // = 9!
		/*
		 * This test finds out with a very good probability whether the AI is
		 * invincible or not. Since there are 9! combinations of game sequences
		 * (there are less because not all sequences are valid - whatever), this
		 * test finds very likely one sequence that ended not in a tie iff the
		 * AI is not invincible
		 */
		for (int i = 0; i < max; i++) {
			game.startNewGame(playerOne, playerTwo);
			assertFalse(hasAnyoneEverWonTheGame);
		}

	}

	private boolean hasAnyoneEverWonTheGame = false;
	private Display display = new Display() {

		@Override
		public void displayField(char[][] field) {
		}

		@Override
		public void nextRoundHasBegun(int currentRound) {
		}

		@Override
		public void gameHasWinner(PlayerProperties winner) {
			InvincibleAiTest.this.hasAnyoneEverWonTheGame = true;
		}

		@Override
		public void gameEndedInATie() {
		}

		@Override
		public void waitForHumanPlayersMove(Move move, PlayerProperties player) {
		}

		@Override
		public void aiStartsMove(PlayerProperties player) {
		}

		@Override
		public void aiFinishedMove(PlayerProperties player,
				Optional<Point> decision) {
		}
	};

}
