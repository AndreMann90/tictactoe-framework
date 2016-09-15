package de.mann.tictactoeframework.tictactoeGame;

import de.mann.tictactoeframework.exceptions.InvalidMoveException;
import de.mann.tictactoeframework.players.Player;
import de.mann.tictactoeframework.players.PlayerID;
import de.mann.tictactoeframework.playingField.FieldPosition;
import de.mann.tictactoeframework.playingField.PlayingField;

public final class Move {

	interface MoveMade {
        void moveMade();
    }
	
	private final PlayingField field;
	private final PlayerID id;
	private boolean moveMade;
    private final MoveMade callback;
	
	public Move(PlayingField field, Player turn, MoveMade callback) {
		this.field = field;
        this.callback = callback;
        this.id = turn.getPlayerID();
		moveMade = false;
	}
	
	public void makeMove(FieldPosition position) throws InvalidMoveException {
		if(moveMade) {
			throw new InvalidMoveException();
		}
		field.makeMove(position, id);
        moveMade();
	}
	
	public boolean isMoveValid(FieldPosition position) {
		return !moveMade && field.isValidMove(position.getPoint());
	}

    private void moveMade() {
        moveMade = true;
        callback.moveMade();
    }
}
