package de.mann.tictactoeframework.aiPlayerStages;

import de.mann.tictactoeframework.playingField.FieldPosition;
import de.mann.tictactoeframework.playingField.Point;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;


class FieldMatcher {

	interface PositionMatch {
		boolean positionsMatch(char specifiedPosition, char actualPosition);
	}
	
	/**
	 *  'x' for player one \n
	 *  'o' for player two \n
	 *  '-' for empty position \n
	 *  '?' for do not care \n
	 *  all other chars are considered to be a free position          
	 */
	final static PositionMatch DEFAULT_MATCH = (specifiedPosition, actualPosition) ->
			specifiedPosition == '?' || specifiedPosition == actualPosition
            || (actualPosition == '-' && specifiedPosition != 'x' && specifiedPosition != 'o');

	
	/**
	 * Checks whether the two fields matches. If so, returns the 
	 * specified, expected position. \n
	 * example:\n
	 * xo- \n
	 * -o- \n
	 * -x- \n
	 * matches: \n
	 * --x \n
	 * xoo \n
	 * --- \n
	 * as well as: \n
	 * x-- \n
	 * oox \n
	 * --- \n 
	 * 
	 * @param specifiedField
	 *            field in String representation \n
	 *            'e' for a free position that is to be 
	 *            returned in the case of a match 
	 * @param actualField
	 *            actual field to compare with
	 * @return the expected position ('e') if the fields match, Empty position 
	 *            otherwise
	 */
	public static FieldPosition fieldsMatch(String specifiedField,
			String actualField, PositionMatch matcher, Random r) {

		if (specifiedField.length() != 9 || actualField.length() != 9) {
			return FieldPosition.Empty;
		}
		
		/*
		 * first check if the center position matches
		 * if not, the fields matches neither
		 * (center is invariant)
		 */
		final boolean centersMatch = matcher.positionsMatch(specifiedField.charAt(4),
				actualField.charAt(4));

		
		//the result to return
		FieldPosition result = FieldPosition.Empty;
		if(centersMatch) {
			
			/*
			 * center matches, check the ring around
			 * 
			 * -definitions-
			 * 
			 * ring:
			 * 012
			 * 7 3
			 * 654
			 * 
			 * ring(1x rotated):
			 * 234
			 * 1 5
			 * 076
			 * 
			 * ring(mirrored):
			 * 076
			 * 1 5
			 * 234
			 * 
			 * 
			 * -idea-
			 * 0123456701234567 (actualDoubledRing)
			 * 01234567         (specifiedRing)
			 *   01234567       (specifiedRing, 1x rotated)
			 *     01234567     (specifiedRing, 2x rotated)
			 *       01234567   (specifiedRing, 3x rotated)
			 * 
			 * 0123456701234567 (actualDoubledRing)
			 * 07654321         (mirroredSpecifiedRing)
			 *   07654321       (mirroredSpecifiedRing, 1x rotated)
			 *     07654321     (mirroredSpecifiedRing, 2x rotated)
			 *       07654321   (mirroredSpecifiedRing, 3x rotated)
			 * 
			 */
			StringBuilder specifiedRing = ringFromField(specifiedField);
			StringBuilder actualDoubledRing = doubleRing(ringFromField(actualField));
			
			int rotated = 0;
			boolean mirrored = false;
			
			boolean matchFound = false;
			
			while(rotated < 4 && !matchFound) {
				
				final int start = (2*rotated);
				final int end = start + 8;
				matchFound = fieldsEqual(specifiedRing, 
						actualDoubledRing.subSequence(start, end),
						matcher);
				
				if(!matchFound) {
					rotated++; 
				}
				if(rotated == 4 && !mirrored) {
					specifiedRing = mirrorRing(specifiedRing);
					mirrored = true;
					rotated = 0;					
				}
			}
			
			if(matchFound) {
				List<Point> expected = new LinkedList<>();

				for (int i = 0; i < specifiedField.length(); i++) {
					if (specifiedField.charAt(i) == 'e') {
						Point curPos = new Point(i % 3, i / 3);
						expected.add(curPos);
					}
				}
				
				if(expected.size() > 0) {
					final int thisPoint = r.nextInt(expected.size());
					
					result = FieldPosition.fromPointToType(expected.get(thisPoint));
					
					if(mirrored) {
						result = result.mirror();
					}

					if(rotated > 0) {
						result = result.rotateRight(2 * rotated);
					}
				}
			}
			
		}		

		return result;
	}
	
	private static boolean fieldsEqual(CharSequence one, CharSequence two, 
			PositionMatch matcher) {
		
		if(one.length() != two.length()) {
			return false;
		}
		
		for(int i = 0; i < one.length(); i++) {
			if( !matcher.positionsMatch(one.charAt(i), two.charAt(i)) ) {
				//if one position does not match, the hole field does not match
				return false;
			}
		}
		
		return true;
	}
	
	private static StringBuilder ringFromField(String s) {
		/*
		 * from:
		 * 012
		 * 345
		 * 678
		 * 
		 * to (clockwise ring):
		 * 012
		 * 3 5
		 * 678
		 * 
		 * node: the center is left out, because it's invariant
		 */
		return new StringBuilder(8).append(s, 0, 3)
				.append(s.charAt(5))
				.append(s.charAt(8))
				.append(s.charAt(7))
				.append(s.charAt(6))
				.append(s.charAt(3));		
	}
	
	private static StringBuilder doubleRing(StringBuilder ring) {
		return new StringBuilder(16).append(ring).append(ring); 		
	}
	
	
	private static StringBuilder mirrorRing(StringBuilder ring) {
		final char zero = ring.charAt(0);
		ring.reverse();
		return new StringBuilder(8).append(zero).
				append(ring, 0, ring.length()-1); //07654321
	}
	
}
