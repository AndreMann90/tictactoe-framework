package de.mann.tictactoeframework.tictactoeGame;

/**
 * Preference which player begins
 */
public enum FirstPlayerOrder {
    ALTERNATING(0), WINNER(1), LOSER(2), RANDOM(3), ALWAYS_FIRST_PLAYER(4), ALWAYS_SECOND_PLAYER(5);

    private int id;

    FirstPlayerOrder(int id) {
        this.id = id;
    }

    public static FirstPlayerOrder fromInt(int i) {
        switch (i) {
            case 0:
                return ALTERNATING;
            case 1:
                return WINNER;
            case 2:
                return LOSER;
            case 4:
                return ALWAYS_FIRST_PLAYER;
            case 5:
                return ALWAYS_SECOND_PLAYER;
            default:
                return RANDOM;
        }
    }
}
