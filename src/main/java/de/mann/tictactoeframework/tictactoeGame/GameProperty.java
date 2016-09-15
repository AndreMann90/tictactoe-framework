package de.mann.tictactoeframework.tictactoeGame;

public class GameProperty {

	public final FirstPlayerOrder order;

	public final static GameProperty DEFAULT = new GameProperty(FirstPlayerOrder.ALTERNATING);
	
	public GameProperty(FirstPlayerOrder order) {
		this.order = order;
	}
	
	/*
	public static class Builder {

		private PlayerID thisPlayerBegins;
		
		public Builder() {
			thisPlayerBegins = PlayerID.NoPlayer; 
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
			return new GameProperty(thisPlayerBegins);
		}

	}
	*/
}
