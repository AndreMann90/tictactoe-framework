package tictactoeGame;

import java.awt.Point;

import exceptions.InvalidMoveException;
import players.Player;
import players.PlayerID;
import playingField.FieldPosition;
import playingField.PlayingField;
import playingField.Positions;

public final class Move {
	
	private PlayingField field;
	private PlayerID id;
	private boolean moveMade;
	
	public Move(PlayingField field, Player turn) {
		this.field = field;
		this.id = turn.getPlayerID();
		moveMade = false;
	}
	
	public void makeMove(int x, int y) throws InvalidMoveException {
		this.makeMove(new Point(x,y));
	}
	
	public void makeMove(FieldPosition position) throws InvalidMoveException {
		checkWetherMoveWasMade();
		field.makeMove(Positions.fromTypeToPoint(position), position, id);
	}
	
	public void makeMove(Point position) throws InvalidMoveException {
		checkWetherMoveWasMade();
		field.makeMove(position, Positions.fromPointToType(position), id);
	}
	
	private void checkWetherMoveWasMade() throws InvalidMoveException {
		if(moveMade == false) {
			moveMade = true;
		} else {
			throw new InvalidMoveException();
		}
	}

}
