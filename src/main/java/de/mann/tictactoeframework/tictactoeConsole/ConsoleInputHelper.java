package de.mann.tictactoeframework.tictactoeConsole;

import java.util.Scanner;

class ConsoleInputHelper {

	static int readIntegerInput(int from, int till, Scanner in, String errorMessage) {
		int result = from - 1;
		boolean validInput;
		do {
			String input = in.nextLine();
			try {
				result = Integer.parseInt(input);
				validInput = (result >= from && result <= till);
			} catch (NumberFormatException e) {
				validInput = false;
			}

			if(!validInput) {
				System.out.println(errorMessage);
			}
		} while (!validInput);
		
		return result;		
	}
}
