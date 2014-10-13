package tictactoeGame;

import players.PlayerID;

public class GameProperty {
	
	public final char emptyPositionSymbol;
	public final PlayerID thisPlayerBegins; //NoPlayer means random

	public final static GameProperty DEFAULT = new GameProperty(PlayerID.NoPlayer, '-');
	
	private GameProperty(PlayerID thisPlayerBegins, char emptyPositionSymbol) {
		this.thisPlayerBegins = thisPlayerBegins;
		this.emptyPositionSymbol = emptyPositionSymbol;
	}
	
	
	public static class Builder {

		private char emptyPositionSymbol;
		private PlayerID thisPlayerBegins;
		
		public Builder() {
			emptyPositionSymbol = '-';
			thisPlayerBegins = PlayerID.NoPlayer; 
		}
		
		public Builder setEmptyPositionSymbol(char symbol) {
			this.emptyPositionSymbol = symbol;
			return this;
		}
		
		public Builder setFirstPlayerBegins() {
			this.thisPlayerBegins = PlayerID.Player1;
			return this;
		}
		
		public Builder setSecondPlayerBegins() {
			this.thisPlayerBegins = PlayerID.Player2;
			return this;
		}
		
		public Builder setRandomPlayerBegins() {
			this.thisPlayerBegins = PlayerID.NoPlayer;
			return this;
		}
		
		public GameProperty create() {
			return new GameProperty(thisPlayerBegins, emptyPositionSymbol);	
		}

	}
}
