package de.mann.tictactoeframework.players;

public enum PlayerID {
	NoPlayer(0), Player1(1), Player2(2);

	private int rep;
	
	PlayerID(int c) {
		rep = c;
	}
	 
	public int getIntRep() {
		return rep;
	}

	public static PlayerID fromIntRep(int rep) {
        switch (rep) {
            case 1: return Player1;
            case 2: return Player2;
            default: return NoPlayer;
        }
    }
	
	public static PlayerID getOpponent(PlayerID me) {
		if(me == Player1) {
			return Player2;
		} else if(me == Player2) {
			return Player1;
		} else {
			return NoPlayer;
		}
	}
	
}
