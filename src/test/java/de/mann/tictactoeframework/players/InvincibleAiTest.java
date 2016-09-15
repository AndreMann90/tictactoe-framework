package de.mann.tictactoeframework.players;

import de.mann.tictactoeframework.gui.Display;
import de.mann.tictactoeframework.playingField.FieldPosition;
import de.mann.tictactoeframework.tictactoeGame.*;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertFalse;

public class InvincibleAiTest {

	private boolean hasPlayerOneEverLost = false;
	private int playerXwins = 0;
	private int playerOwins = 0;
	private int gameEndedInTie = 0;
	

	@Test
	public void invincibleTest() {
		PlayerProperties playerOne = new PlayerProperties.PlayerBuilder(Symbol.X).setInvincibleAI()
				.create();
		PlayerProperties playerTwo = new PlayerProperties.PlayerBuilder(Symbol.O).setAttentiveAI()
				.create();

		GameProperty gameProperty = new GameProperty(FirstPlayerOrder.ALWAYS_FIRST_PLAYER);

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
			game.startNewGame(gameProperty, playerOne, playerTwo);
			assertFalse(hasPlayerOneEverLost);
		}
		
		showStats();
		
		for (int i = 0; i < max; i++) {
			game.startNewGame(gameProperty, playerTwo, playerOne);
			assertFalse(hasPlayerOneEverLost);
		}

		showStats();
	}
	
	private void showStats() {
		final float totalGames = (playerXwins + playerOwins + gameEndedInTie) / 100;
		System.out.println("PlayerX wins: " + playerXwins/totalGames + "%");
		System.out.println("PlayerO wins: " + playerOwins/totalGames + "%");
		System.out.println("Game ended in a tie: " + gameEndedInTie/totalGames + "%");
		System.out.println("--------------------------");
	}

	private final Display display = new Display() {

		@Override
		public void displayField(Symbol[][] field) {

		}

		@Override
		public void nextRoundHasBegun(int currentRound) {

		}

		@Override
		public void gameHasWinner(PlayerProperties winner, List<FieldPosition> winningPositions) {
			switch (winner.symbol) {
				case O:
					playerOwins++;
					InvincibleAiTest.this.hasPlayerOneEverLost = true;
				case X:
					playerXwins++;
			}
		}

		@Override
		public void gameEndedInATie() {
			gameEndedInTie++;
		}

		@Override
		public void waitForHumanPlayersMove(Move move, PlayerProperties player) {

		}

		@Override
		public void aiStartsMove(PlayerProperties player) {

		}

		@Override
		public void aiFinishedMove(PlayerProperties player, FieldPosition decision) {

		}
	};

}
