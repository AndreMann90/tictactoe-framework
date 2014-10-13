package players;

public class PlayerProperties {
	public final String name;
	public final char symbol;
	public final boolean isHuman;
	
	
	private PlayerProperties(String name, char symbol, boolean isHuman) {
		this.name = name;
		this.symbol = symbol;
		this.isHuman = isHuman;
	}
	
	
	public static class PlayerBuilder {
		
		private String name;
		private char symbol;
		private boolean isHuman = true;
		
		public PlayerBuilder(char symbol) {
			this.symbol = symbol;
			this.name = "player " + symbol;
		}
		
		public PlayerBuilder setName(String name) {
			this.name = name;
			return this;
		}
		
		public PlayerBuilder setHuman(boolean isHuman) {
			this.isHuman = isHuman;
			return this;
		}
		
		public PlayerProperties create() {
			return new PlayerProperties(name, symbol, isHuman);	
		}

	}

}
