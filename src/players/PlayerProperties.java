package players;

public class PlayerProperties {
	public final String name;
	public final char symbol;
	public final boolean isHuman;
	public final int knowledge;
	public final int finisher;
	public final int spiker;

	private PlayerProperties(String name, char symbol, boolean isHuman,
			int knowledge, int finisher, int spiker) {
		this.name = name;
		this.symbol = symbol;
		this.isHuman = isHuman;
		this.knowledge = knowledge;
		this.finisher = finisher;
		this.spiker = spiker;
	}

	public static class PlayerBuilder {

		private String name;
		private char symbol;
		private boolean isHuman = true;
		private int knowledge = 80;
		private int finisher = 99;
		private int spiker = 90;

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

		public PlayerBuilder setInvincibleAI() {
			this.isHuman = false;
			knowledge = 100;
			finisher = 100;
			spiker = 100;
			return this;
		}

		public PlayerBuilder setStupidAI() {
			this.isHuman = false;
			knowledge = 0;
			finisher = 0;
			spiker = 0;
			return this;
		}

		public PlayerBuilder setAttentiveAI() {
			this.isHuman = false;
			knowledge = 0;
			finisher = 100;
			spiker = 100;
			return this;
		}

		public PlayerBuilder setNoviceAI() {
			this.isHuman = false;
			knowledge = 20;
			finisher = 75;
			spiker = 65;
			return this;
		}

		public PlayerBuilder setCompetitiveAI() {
			this.isHuman = false;
			knowledge = 85;
			finisher = 98;
			spiker = 95;
			return this;
		}

		public PlayerBuilder setAlmostFlawlessAI() {
			this.isHuman = false;
			knowledge = 90;
			finisher = 100;
			spiker = 99;
			return this;
		}

		/**
		 * Set up a custom AI. \n Range of all parameters: [0, 100]
		 * 
		 * @param knowledge
		 *            AI makes the best move by knowing
		 * @param finisher
		 *            AI finds a three-in-a-row position to win the game
		 * @param spiker
		 *            AI spikes a three-in-a-row position of the opponent
		 * @return returns a PlayerBuilder
		 */
		public PlayerBuilder setCustomAI(final int knowledge,
				final int finisher, final int spiker) {

			this.isHuman = false;

			if (knowledge > 100) {
				this.knowledge = 100;
			} else if (knowledge < 0) {
				this.knowledge = knowledge;
			}

			if (knowledge > 100) {
				this.knowledge = 100;
			} else if (knowledge < 0) {
				this.knowledge = 0;
			} else {
				this.knowledge = knowledge;
			}

			if (finisher > 100) {
				this.finisher = 100;
			} else if (finisher < 0) {
				this.finisher = 0;
			} else {
				this.finisher = finisher;
			}

			if (spiker > 100) {
				this.spiker = 100;
			} else if (spiker < 0) {
				this.spiker = 0;
			} else {
				this.spiker = spiker;
			}

			return this;
		}

		public PlayerProperties create() {
			return new PlayerProperties(name, symbol, isHuman, knowledge,
					finisher, spiker);
		}

	}

}
