package de.mann.tictactoeframework.gui;

import de.mann.tictactoeframework.players.PlayerProperties;
import de.mann.tictactoeframework.playingField.FieldPosition;
import de.mann.tictactoeframework.tictactoeGame.Move;
import de.mann.tictactoeframework.tictactoeGame.Symbol;

import java.util.List;

public interface Display {
	void displayField(Symbol[][] field);

	/**
	 * Callback fired when next round has begun
	 * @param currentRound round that begins
	 */
	void nextRoundHasBegun(int currentRound);

	/**
	 * Callback fired when the game is over and a winner exists
	 * @param winner the winner of the game
	 * @param winningPositions the positions with which the winner won
	 */
	void gameHasWinner(PlayerProperties winner, List<FieldPosition> winningPositions);

	/**
	 * Callback fired when the game is over and there is no winner
	 */
	void gameEndedInATie();

	/**
	 * Wait for human players move. Call {@link Move#makeMove(FieldPosition)} for continuing the game
	 * with move chosen by the human player
	 * @param move Callback for framework. Call {@link Move#makeMove(FieldPosition)} after human player
	 *             has chosen a move
	 * @param player player that has to move next
	 */
	void waitForHumanPlayersMove(Move move, PlayerProperties player);
	
	void aiStartsMove(PlayerProperties player);
	void aiFinishedMove(PlayerProperties player, FieldPosition decision);
}
