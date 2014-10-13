package players;

import tictactoeGame.Move;

public abstract class Player {
	public abstract PlayerID getPlayerID();
	public abstract PlayerProperties getProperties();
	
	public abstract void makeMove(final Move move);
}
