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
		makeMove(Positions.fromTypeToPoint(position), position);
	}
	
	public void makeMove(Point position) throws InvalidMoveException {
		makeMove(position, Positions.fromPointToType(position));
	}
	
	private void makeMove(Point ppos, FieldPosition fpos) throws InvalidMoveException {
		if(moveMade) {
			throw new InvalidMoveException();
		}
		field.makeMove(ppos, fpos, id);
		moveMade = true;
	}

}
