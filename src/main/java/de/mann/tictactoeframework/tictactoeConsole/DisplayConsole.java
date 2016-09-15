package de.mann.tictactoeframework.tictactoeConsole;

import de.mann.tictactoeframework.exceptions.InvalidMoveException;
import de.mann.tictactoeframework.gui.Display;
import de.mann.tictactoeframework.players.PlayerProperties;
import de.mann.tictactoeframework.playingField.FieldPosition;
import de.mann.tictactoeframework.tictactoeGame.Move;
import de.mann.tictactoeframework.tictactoeGame.Symbol;

import java.util.List;
import java.util.Scanner;

class DisplayConsole implements Display {

	private Scanner in;

	DisplayConsole() {
		in = new Scanner(System.in);
	}

	@Override
	public void displayField(Symbol[][] field) {
		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
				System.out.print(field[x][y]);
				if (y < 2) {
					System.out.print("|");
				}
			}
			System.out.println();
		}
	}

	@Override
	public void gameHasWinner(PlayerProperties winner, List<FieldPosition> winningPositions) {
		System.out.println(winner.name + " has won the game!");
	}


	@Override
	public void waitForHumanPlayersMove(Move move, PlayerProperties player) {
		System.out.println(player.name
				+ ", please make your Move (Snail shell-coordinates: 0-8)");

		boolean validMove = false;
		do {
			try {
				int chosen = ConsoleInputHelper.readIntegerInput(0, 8, in,
						"Invalid input. Please use Snail shell-coordinates: 0-8");
				FieldPosition chosenField = FieldPosition.fromInt(chosen);
				move.makeMove(chosenField);
				validMove = true;
			} catch (InvalidMoveException e) {
				System.out.println(player.name
						+ ", this was not a valid move. Try again.");
			}
		} while (!validMove);
	}

	@Override
	public void gameEndedInATie() {
		System.out.println("The game ended in a tie.");
	}

	@Override
	public void aiStartsMove(PlayerProperties player) {
		// print out nothing
	}

	@Override
	public void aiFinishedMove(PlayerProperties player, FieldPosition decision) {
		System.out.print(player.name + "'s turn");
		if (!decision.isEmpty()) {
			System.out.println(": (" + decision.getPoint().x + ","
					+ decision.getPoint().y + ")");
		} else {
			System.out.println(": InvalidMove");
		}
	}


	@Override
	public void nextRoundHasBegun(int currentRound) {
		System.out.println("Round No. " + currentRound);
	}

}
