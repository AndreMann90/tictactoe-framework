package de.mann.tictactoeframework.tictactoeConsole;

import de.mann.tictactoeframework.gui.Display;
import de.mann.tictactoeframework.players.PlayerProperties;
import de.mann.tictactoeframework.tictactoeGame.Game;
import de.mann.tictactoeframework.tictactoeGame.Symbol;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		Display display = new DisplayConsole();
		Game game = new Game(display);

		System.out.println("Welcome to Tic Tac Toe!");

		
		System.out.println("How many human player? (0-2)");
		int numHumans = ConsoleInputHelper.readIntegerInput(0, 2, in,
				"Invalid input. How many human player? (0-2)");

		PlayerProperties.PlayerBuilder playerOne = new PlayerProperties.PlayerBuilder(Symbol.X).setHuman(true)
				.setName("Player \"X\"");
		PlayerProperties.PlayerBuilder playerTwo = new PlayerProperties.PlayerBuilder(Symbol.O).setHuman(true)
				.setName("Player \"O\"");
	
		if (numHumans == 0) {
			System.out.println("Choose AI strength for Player \"X\"");
			setAiStrength(playerOne, in);
		}
		
		if (numHumans < 2) {
			System.out.println("Choose AI strength for Player \"O\"");
			setAiStrength(playerTwo, in);			
		}

		
		do {
			// Starting the game:
			game.startNewGame(playerOne.create(), playerTwo.create());

			System.out.println("Enter to start new Game. (Type in \"e\" for exit)");
		} while (!in.nextLine().equals("e"));

		in.close();
	}
	
	private static void setAiStrength (PlayerProperties.PlayerBuilder ai, Scanner in) {
		System.out.println("-Invincible AI (1)");
		System.out.println("-Almost flawless AI (2)");
        System.out.println("-Competitive AI (3)");
		System.out.println("-Attentive AI (4)");
		System.out.println("-Novice AI (5)");
        System.out.println("-Stupid AI (6)");

		int aiStrength = ConsoleInputHelper.readIntegerInput(1, 6, in,
				"Invalid input. Choose between 1 and 6");

		switch (aiStrength) {
		
		case 1: ai.setInvincibleAI(); break;
		case 2: ai.setAlmostFlawlessAI(); break;
		case 3: ai.setCompetitiveAI(); break;
		case 4: ai.setAttentiveAI(); break;
		case 5: ai.setNoviceAI(); break;
        case 6: ai.setStupidAI(); break;
		
		}
	}

}
