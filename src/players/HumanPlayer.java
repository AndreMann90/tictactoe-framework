package players;

import gui.Display;
import tictactoeGame.Move;

public class HumanPlayer extends Player {
	
	private PlayerID myPlayerID;
	private Display display;
	private PlayerProperties myProperties;
	
	public HumanPlayer(PlayerID playerID, PlayerProperties properties, Display display) {
		this.myPlayerID = playerID;
		this.myProperties = properties;
		this.display = display;
	}

	@Override
	public PlayerID getPlayerID() {
		return myPlayerID;
	}

	@Override
	public void makeMove(Move move) {
		display.waitForHumanPlayersMove(move, myProperties);
	}

	@Override
	public PlayerProperties getProperties() {
		return myProperties;
	}

}
