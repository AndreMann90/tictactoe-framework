package de.mann.tictactoeframework.tictactoeGame;

import de.mann.tictactoeframework.gui.Display;
import de.mann.tictactoeframework.players.*;
import de.mann.tictactoeframework.playingField.PlayingField;

import java.util.Random;

public class Game implements Move.MoveMade {
	
	private final Display display;
    private final PlayingField field;
    private Player playerOne;
    private Player playerTwo;
    private boolean playerTwoBegan;
	private final Random r;

    public Game (Display display) {
		this.display = display;
		r = new Random();
        field = new PlayingField();
        playerTwoBegan = true;
	}

    public int getRound() {
        return field.getRound();
    }

    public PlayerProperties playersTurn() {
        if(playerOne == null || playerTwo == null) {
            return null;
        } else {
            if (isPlayerOneTurn()) {
                return playerOne.getProperties();
            } else {
                return playerTwo.getProperties();
            }
        }
    }

    public PlayerProperties notPlayersTurn() {
        if(playerOne == null || playerTwo == null) {
            return null;
        } else {
            if (!isPlayerOneTurn()) {
                return playerOne.getProperties();
            } else {
                return playerTwo.getProperties();
            }
        }
    }

	public void startNewGame(PlayerProperties firstPlayer, PlayerProperties secondPlayer) {
		startNewGame(GameProperty.DEFAULT, firstPlayer, secondPlayer);
	}

	public void startNewGame(GameProperty gameproperty, PlayerProperties firstPlayer, final PlayerProperties secondPlayer) {

        /*
		 * swap players iff second player begins
		 * because in the Game loop playerOne always begins
		 */
        final boolean secondPlayerBegins;
        switch (gameproperty.order) {
            case WINNER:
                if(field.hasWinner()) {
                    secondPlayerBegins = (field.winner() == PlayerID.Player2);
                    break;
                }
            case LOSER:
                if(field.hasWinner()) {
                    secondPlayerBegins = (field.winner() != PlayerID.Player2);
                    break;
                }
            case ALTERNATING:
                secondPlayerBegins = !playerTwoBegan;
                break;
            case ALWAYS_FIRST_PLAYER:
                secondPlayerBegins = false;
                break;
            case ALWAYS_SECOND_PLAYER:
                secondPlayerBegins = true;
                break;
            default:
                secondPlayerBegins = r.nextBoolean();
                break;
        }

        setUp(secondPlayerBegins, firstPlayer, secondPlayer);

        display.displayField(field.getField(playerOne.getProperties().symbol,
                playerTwo.getProperties().symbol, Symbol.Empty));

        nextRound();
	}

    private void setUp(boolean playerTwoBegins, PlayerProperties firstPlayer,
                       PlayerProperties secondPlayer) {

        // Clear to have a fresh playing field
        field.clearField();
        playerTwoBegan = playerTwoBegins;

        //Create the players
        if(firstPlayer.isHuman) {
            playerOne = new HumanPlayer(PlayerID.Player1, firstPlayer, display);
        } else {
            playerOne = new AIplayer(PlayerID.Player1, firstPlayer, display, field, r);
        }

        if(secondPlayer.isHuman) {
            playerTwo = new HumanPlayer(PlayerID.Player2, secondPlayer, display);
        } else {
            playerTwo = new AIplayer(PlayerID.Player2, secondPlayer, display, field, r);
        }
    }

	private void nextRound() {
        display.nextRoundHasBegun(field.getRound());

        if(isPlayerOneTurn()) {
            playerOne.makeMove(new Move(field, playerOne, this));
        } else {
            playerTwo.makeMove(new Move(field, playerTwo, this));
        }
    }

    private boolean isPlayerOneTurn() {
        final int order = playerTwoBegan ? 0 : 1;
        return field.getRound() % 2 == order;
    }

	@Override
	public void moveMade() {
        display.displayField(field.getField(playerOne.getProperties().symbol,
                playerTwo.getProperties().symbol, Symbol.Empty));

        if(field.hasWinner()) {
            PlayerProperties winner;
            if(playerOne.getPlayerID() == field.winner()) {
                winner = playerOne.getProperties();
            } else if(playerTwo.getPlayerID() == field.winner()){
                winner = playerTwo.getProperties();
            } else {
                throw new AssertionError("field.hasWinner is wrong");
            }
            display.gameHasWinner(winner, field.getWinningPositions());
        } else if(field.getRound() > PlayingField.LAST_ROUND) {
            display.gameEndedInATie();
        } else {
            nextRound();
        }
	}

}
