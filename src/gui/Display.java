package gui;

import java.awt.Point;
import java.util.Optional;

import players.PlayerProperties;
import tictactoeGame.Move;

public interface Display {
	void displayField(char[][] field);
	
	void nextRoundHasBegun(int currentRound);

	void gameHasWinner(PlayerProperties winner);
	void gameEndedInATie();
	
	void waitForHumanPlayersMove(Move move, PlayerProperties player);
	
	void aiStartsMove(PlayerProperties player);
	void aiFinishedMove(PlayerProperties player, Optional<Point> decision);
}
