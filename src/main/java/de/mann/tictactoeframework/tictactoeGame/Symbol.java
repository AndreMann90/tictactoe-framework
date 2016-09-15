package de.mann.tictactoeframework.tictactoeGame;

/**
 * Symbol of Tic Tac Toe, usually: X and O
 */
public enum Symbol {
    X(120), O(111), XO(10683), Empty(45);

    private int uniCode;

    Symbol(int c) {
        uniCode = c;
    }

    public int getUniCode() {
        return uniCode;
    }

    public char getChar() {
        return (char) uniCode;
    }

    public static Symbol otherSymbol(Symbol symbol) {
        return symbol == O ? X : O;
    }

    public static Symbol fromUniCode(int uniCode) {
        switch (uniCode) {
            case 120:
                return X;
            case 111:
                return O;
            case 10683:
                return XO;
            default:
                return Empty;
        }
    }

    public static String formattedString(Symbol symbol, String separator) {
        if(symbol == XO) {
            return Symbol.X.toString() + separator + Symbol.O.toString();
        } else {
            return symbol.toString();
        }
    }

    @Override
    public String toString() {
        return String.valueOf((char) getUniCode()).toUpperCase();
    }
}
