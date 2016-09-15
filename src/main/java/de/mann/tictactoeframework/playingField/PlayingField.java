package de.mann.tictactoeframework.playingField;

import de.mann.tictactoeframework.exceptions.InvalidMoveException;
import de.mann.tictactoeframework.players.PlayerID;
import de.mann.tictactoeframework.tictactoeGame.Symbol;

import java.util.LinkedList;
import java.util.List;

public class PlayingField {
	
	public final static int LAST_ROUND = 9;
	
	private int field[][] = new int[3][3];
	private FieldPosition history[] = new FieldPosition[LAST_ROUND];
	private PlayerID winner = PlayerID.NoPlayer;
	private List<FieldPosition> winningPosition = new LinkedList<>();
	private int round = 1;

    public void clearField() {
        round = 1;
        winner = PlayerID.NoPlayer;
		winningPosition.clear();
        for (int i = 0; i < LAST_ROUND; i++) {
            history[i] = FieldPosition.Empty;
        }
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                field[x][y] = PlayerID.NoPlayer.getIntRep();
            }
        }
    }
	
	public Symbol[][] getField(Symbol playerOne, Symbol playerTwo, Symbol noPlayer) {
		final Symbol[] symbols = {noPlayer, playerOne, playerTwo};

		Symbol[][] resultingField = new Symbol[3][3];
		for(int x = 0; x < 3; x++) {
			for(int y = 0; y < 3; y++) {
				resultingField[x][y] = symbols[this.field[x][y]];
			}
		}
		return resultingField;
	}
	
	@Override
	public String toString() {
		Symbol[][] field = getField(Symbol.X, Symbol.O, Symbol.Empty);
		StringBuilder fieldString = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                fieldString.append(field[j][i].getChar());
            }
        }
        return fieldString.toString();
	}

	public int getIdAt(Point position) {
		return getIdAt(position.x, position.y);
	}

	public int getIdAt(int x, int y) {
        if(x >= 0 && x <= 2 && y >= 0 && y <= 2) {
            return field[x][y];
        } else {
            return Integer.MIN_VALUE;
        }
	}

	public int getIdAt(FieldPosition position) {
		return getIdAt(position.getPoint());
	}
	
	public boolean isPositionFree(int x, int y) {
		return getIdAt(x,y) == PlayerID.NoPlayer.getIntRep();
	}
	
	public boolean isPositionFree(FieldPosition pos) {
		return getIdAt(pos) == PlayerID.NoPlayer.getIntRep();
	}
	
	public boolean isPositionFree(Point pos) {
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

	public void makeMove(Point ppos, PlayerID id) throws InvalidMoveException {
        makeMove(ppos, FieldPosition.fromPointToType(ppos), id);
    }

    public void makeMove(FieldPosition fpos, PlayerID id) throws InvalidMoveException {
        makeMove(fpos.getPoint(), fpos, id);
    }

	private void makeMove(Point ppos, FieldPosition fpos, PlayerID id) throws InvalidMoveException {
		if(!isValidMove(ppos)) {
			throw new InvalidMoveException();
		}
		
		final int IDIntRep = id.getIntRep();
		
		field[ppos.x][ppos.y] = IDIntRep;
		history[round-1] = fpos;
		round++;
		
		boolean hasWon = false;

		if(field[0][ppos.y] == IDIntRep
				&& field[1][ppos.y] == IDIntRep
				&& field[2][ppos.y] == IDIntRep) {
			hasWon = true;
			winningPosition.add(FieldPosition.fromPointToType(new Point(0, ppos.y)));
			winningPosition.add(FieldPosition.fromPointToType(new Point(1, ppos.y)));
			winningPosition.add(FieldPosition.fromPointToType(new Point(2, ppos.y)));
		} else if(field[ppos.x][0] == IDIntRep
				&& field[ppos.x][1] == IDIntRep
				&& field[ppos.x][2] == IDIntRep) {
			hasWon = true;
			winningPosition.add(FieldPosition.fromPointToType(new Point(ppos.x, 0)));
			winningPosition.add(FieldPosition.fromPointToType(new Point(ppos.x, 1)));
			winningPosition.add(FieldPosition.fromPointToType(new Point(ppos.x, 2)));
		} else if(field[1][1] == IDIntRep) {
			if(field[0][0] == IDIntRep && field[2][2] == IDIntRep) {
				hasWon = true;
				winningPosition.add(FieldPosition.fromPointToType(new Point(0, 0)));
				winningPosition.add(FieldPosition.fromPointToType(new Point(1, 1)));
				winningPosition.add(FieldPosition.fromPointToType(new Point(2, 2)));
			} else if(field[2][0] == IDIntRep && field[0][2] == IDIntRep) {
				hasWon = true;
				winningPosition.add(FieldPosition.fromPointToType(new Point(2, 0)));
				winningPosition.add(FieldPosition.fromPointToType(new Point(1, 1)));
				winningPosition.add(FieldPosition.fromPointToType(new Point(0, 2)));
			}
		}
				
		if(hasWon) {
			winner = id;
		}
	}
	
	public boolean isValidMove(Point position) {
		return position != null && position.x >= 0 && position.x <= 2
                && position.y >= 0 && position.y <= 2
                && field[position.x][position.y] == PlayerID.NoPlayer.getIntRep();
	}
	
	public void revertLastMove() {
		if(round > 1) {
			round--;
			final Point revert = history[round].getPoint();
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

	public List<FieldPosition> getWinningPositions() {
		return winningPosition;
	}
	
	public PlayerID winner() {		
		return winner;
	}
	
	public int getRound() {
		return round;
	}

	////////////////////////////////////////////////////////////////////////////////////////////////
	// save and load ///////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////

	public String encodeString() {
		StringBuilder field = new StringBuilder();
		field.append(winner.getIntRep());
		for(FieldPosition pos : winningPosition) {
			field.append(pos.getPosition());
		}
        for (int i = 0; i < round - 1; i++) {
            FieldPosition pos = history[i];
            Point p = pos.getPoint();
			field.append(pos.getPosition());
			field.append(this.field[p.x][p.y]);
        }
        return field.toString();
    }

	public void decodeString(String encodedField) {
        clearField();
        if(encodedField.length() > 0) {
            round = (encodedField.length() / 2) + 1;
            int w = encodedField.charAt(0) - 48;
            winner = PlayerID.fromIntRep(w);
			if(hasWinner()) {
				for (int i = 1; i < 4; i++) {
					w = encodedField.charAt(i) - 48;
					winningPosition.add(FieldPosition.fromInt(w));
				}
			}
			final int start = hasWinner() ? 4 : 1;
            for (int i = start; i < encodedField.length(); i=i+2) {
                int pos = encodedField.charAt(i) - 48;
                final FieldPosition fpos = FieldPosition.fromInt(pos);
                history[(i - start)/2] = fpos;
                if(!fpos.isEmpty()) {
                    final Point ppos = fpos.getPoint();
                    int player = encodedField.charAt(i+1) - 48;
                    field[ppos.x][ppos.y] = player;
                }
            }
        }
	}

}
