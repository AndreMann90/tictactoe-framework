package tictactoeConsole;

import gui.Display;

import java.util.Scanner;

import players.PlayerProperties.PlayerBuilder;
import tictactoeGame.Game;

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		Display display = new DisplayConsole();
		Game game = new Game(display);

		System.out.println("Welcome to Tic Tac Toe!");
		
		System.out.println("How many human player? (0-2)");
		int numHumans = in.nextInt();

		PlayerBuilder playerOne = new PlayerBuilder('x').setHuman(false)
				.setName("Player 1");		
		PlayerBuilder playerTwo = new PlayerBuilder('o').setHuman(false)
			.setName("Player 2");
		
		if(numHumans > 0) {
			playerOne.setHuman(true);
		}
		if(numHumans > 1) {
			playerTwo.setHuman(true);
		}

		
		game.startNewGame(playerOne.create(), playerTwo.create());
		
		in.close();
	}

}
