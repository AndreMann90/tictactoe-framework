package de.mann.tictactoeframework.aiPlayerStages;

import de.mann.tictactoeframework.playingField.FieldPosition;
import de.mann.tictactoeframework.playingField.PlayingField;
import de.mann.tictactoeframework.playingField.Point;

public class RandomPlayer {
	
	
	public static void determineRandomPosition(PlayingField field, Decisioner decisioner) {
		
		final int weight = 1;
		
		//determine the number of free positions
		final int numFreePosition = (PlayingField.LAST_ROUND + 1) - field.getRound();
		
		if(numFreePosition <= 0) {
			return;
		}
		
		//the number of the free position that will be chosen
		final int thisFreePosition = decisioner.getRandom().nextInt(numFreePosition) + 1;
		
		
		/*
		 * search for the free position that was encountered
		 * after thisFreePosition times free positions
		 */
		int x = -1;
		int y = 0;
		//counts the number of encountered free positions so far
		int count = 0;
		
		while(count < thisFreePosition) {
			x++;
			if(x == 3) {
				x = 0;
				y++;
			}
			
			if(field.isPositionFree(x, y)) {
				count++;
			}
		}

		decisioner.putPossibility(FieldPosition.fromPointToType(new Point(x,y)), weight);
		
	}
}
