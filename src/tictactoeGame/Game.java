package tictactoeGame;

import gui.Display;

import java.util.Random;

import players.AIplayer;
import players.HumanPlayer;
import players.Player;
import players.PlayerID;
import players.PlayerProperties;
import playingField.PlayingField;

public class Game {
	
	private Display display;
	private PlayingField field;
	
	public Game (Display display) {
		this.display = display;
	}
	
	public void startNewGame(PlayerProperties firstPlayer, 
			PlayerProperties secondPlayer) {
		
		startNewGame(GameProperty.DEFAULT, firstPlayer, secondPlayer);
	}

	public void startNewGame(GameProperty gameproperty, PlayerProperties firstPlayer, 
			PlayerProperties secondPlayer) {
	
		//Create a fresh playing field
		field = new PlayingField();
		display.displayField(field.getField(firstPlayer.symbol, secondPlayer.symbol, 
				gameproperty.emptyPositionSymbol));
		
		//Create the players
		Player playerOne;
		Player playerTwo;
		
		if(firstPlayer.isHuman) {
			playerOne = new HumanPlayer(PlayerID.Player1, firstPlayer, display);
		} else {
			playerOne = new AIplayer(PlayerID.Player1, firstPlayer, display, field);
		}
		
		if(secondPlayer.isHuman) {
			playerTwo = new HumanPlayer(PlayerID.Player2, secondPlayer, display);
		} else {
			playerTwo = new AIplayer(PlayerID.Player2, secondPlayer, display, field);
		}
		
		/*
		 * swap players iff second player begins 
		 * because in the Game loop playerOne always begins
		 */
		final boolean secondPlayerBegins;
		if(gameproperty.thisPlayerBegins.equals(PlayerID.NoPlayer)) {
			Random r = new Random();
			secondPlayerBegins = r.nextBoolean();
		} else if(gameproperty.thisPlayerBegins.equals(PlayerID.Player2)) {
			secondPlayerBegins = true;
		} else {
			secondPlayerBegins = false;
		}
		
		if(secondPlayerBegins) { //swap
			Player temp = playerOne;
			playerOne = playerTwo;
			playerTwo = temp;
		}
		
		
		
		//Game loop
		boolean hasWinner = false;
		while(hasWinner == false && field.getRound() <= PlayingField.LAST_ROUND) {
			
			display.nextRoundHasBegun(field.getRound());
			
			if(field.getRound() % 2 == 1) {
				playerOne.makeMove(new Move(field, playerOne));
			} else {
				playerTwo.makeMove(new Move(field, playerTwo));
			}

			display.displayField(field.getField(firstPlayer.symbol, secondPlayer.symbol,
					gameproperty.emptyPositionSymbol));
			
			if(field.hasWinner()) {
				hasWinner = true;
				
				PlayerProperties winner = null;
				if(playerOne.getPlayerID() == field.winner()) {
					winner = playerOne.getProperties();
				} else if(playerTwo.getPlayerID() == field.winner()){
					winner = playerTwo.getProperties();
				}
				display.gameHasWinner(winner);
			}
		}
		
		if(field.hasWinner() == false) {
			display.gameEndedInATie();
		}
		
	}
}
