package playingField;

import java.awt.Point;

import exceptions.InvalidMoveException;
import players.PlayerID;

public class PlayingField {
	
	public final static int LAST_ROUND = 9;
	
	private int field[][] = new int[3][3];
	private FieldPosition history[] = new FieldPosition[9];
	private PlayerID winner = PlayerID.NoPlayer;
	private int round = 1;

	
	public char[][] getField(char playerOne, char playerTwo, char noPlayer) {
		char[] symbols = {noPlayer, playerOne, playerTwo};
		
		char[][] resultingField = new char[3][3];
		for(int x = 0; x < 3; x++) {
			for(int y = 0; y < 3; y++) {
				resultingField[x][y] = symbols[this.field[x][y]];
			}
		}
		return resultingField;
	}

	public int getIdAt(Point position) {
		return field[position.x][position.y];
	}

	public int getIdAt(int x, int y) {
		return field[x][y];
	}

	public int getIdAt(FieldPosition position) {
		return getIdAt(Positions.fromTypeToPoint(position));
	}
	
	public boolean isPostionFree(int x, int y) {
		return field[x][y] == PlayerID.NoPlayer.getIntRep();
	}
	
	public boolean isPostionFree(FieldPosition pos) {
		return getIdAt(pos) == PlayerID.NoPlayer.getIntRep();
	}
	
	public boolean isPostionFree(Point pos) {
		return getIdAt(pos) == PlayerID.NoPlayer.getIntRep();
	}
	
	public boolean isMyPosition(int x, int y, PlayerID myID) {
		return getIdAt(x, y) == myID.getIntRep();
	}
	
	public boolean isMyPosition(FieldPosition pos, PlayerID myID) {
		return getIdAt(pos) == myID.getIntRep();
	}
	
	public boolean isMyPosition(Point pos, PlayerID myID) {
		return getIdAt(pos) == myID.getIntRep();
	}
	
	public FieldPosition getPosFromHistory(int round) {
		if(round > this.round || round < 1) {
			return FieldPosition.Empty;
		}
		return history[round-1];
	}
	
	public void makeMove(Point ppos, FieldPosition fpos, PlayerID id) throws InvalidMoveException {
		if(isValidMove(ppos) == false) {
			throw new InvalidMoveException();
		}
		
		final int IDIntRep = id.getIntRep();
		
		field[ppos.x][ppos.y] = IDIntRep;
		history[round-1] = fpos;
		round++;
		
		final boolean hasWon = 
				//vertical
				(field[0][ppos.y] == IDIntRep 
				&& field[1][ppos.y] == IDIntRep 
				&& field[2][ppos.y] == IDIntRep)
				||
				//horizontal
				(field[ppos.x][0] == IDIntRep 
				&& field[ppos.x][1] == IDIntRep 
				&& field[ppos.x][2] == IDIntRep)
				||
				//cross
				(field[1][1] == IDIntRep //center
				&&
					((field[0][0] == IDIntRep && field[2][2] == IDIntRep)
					||
					(field[2][0] == IDIntRep && field[0][2] == IDIntRep))
				);
				
		if(hasWon) {
			winner = id;
		}
	}
	
	private boolean isValidMove(Point position) {
		if(position == null) {
			return false;
		}
		return field[position.x][position.y] == PlayerID.NoPlayer.getIntRep();		
	}
	
	public void revertLastMove() {
		if(round > 1) {
			round--;
			Point revert = Positions.fromTypeToPoint(history[round]);
			field[revert.x][revert.y] = PlayerID.NoPlayer.getIntRep();
		}		
	}
	
	public void resetToRound(int round) {
		if(round < 1) {
			round = 1;
		}
		while(this.round > round) {
			this.revertLastMove();
		}
	}	
	
	public boolean hasWinner() {		
		return winner != PlayerID.NoPlayer;		
	}
	
	public PlayerID winner() {		
		return winner;
	}
	
	public int getRound() {
		return round;
	}
	
}
