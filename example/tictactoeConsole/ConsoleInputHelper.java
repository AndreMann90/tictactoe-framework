package tictactoeConsole;

import java.util.Scanner;

public class ConsoleInputHelper {

	public static int readIntegerInput(int from, int till, Scanner in, String errorMessage) {
		int result = from - 1;
		boolean validInput = false;
		do {
			String input = in.nextLine();
			try {
				result = Integer.parseInt(input);
			} catch (NumberFormatException e) {				
			}
			
			validInput = (result >= from && result <= till);
			if(validInput == false) {
				System.out.println(errorMessage);
			}
		} while (validInput == false);
		
		return result;		
	}
}
