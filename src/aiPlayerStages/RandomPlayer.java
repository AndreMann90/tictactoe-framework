package aiPlayerStages;

import java.awt.Point;
import java.util.Random;

import playingField.PlayingField;

public class RandomPlayer {
	
	
	public static void determineRandomPosition(PlayingField field, Decisioner decisioner) {
		
		final int weight = 1;
		
		Random r = new Random();
		
		//determine the number of free positions
		final int numFreePosition = (PlayingField.LAST_ROUND + 1) - field.getRound();
		
		if(numFreePosition <= 0) {
			return;
		}
		
		//the number of the free position that will be chosen
		final int thisFreePosition = r.nextInt(numFreePosition) + 1;
		
		
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
			
			if(field.isPostionFree(x, y)) {
				count++;
			}
		}

		decisioner.putPossibility(new Point(x, y), weight);
		
	}
}
