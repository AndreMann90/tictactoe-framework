package aiPlayerStages;

import playingField.FieldPosition;

public class FieldMatcher {
	
	@FunctionalInterface
	public interface PositionMatch {
		boolean positionsMatch(char specifiedPostion, char actualPosition);
	}
	
	/**
	 *  'x' for player one \n
	 *  'o' for player two \n
	 *  '-' for empty position \n
	 *  '?' for do not care \n
	 *  all other chars are considered to be a free position          
	 */
	public final static PositionMatch DEFAULT_MATCH = (one, two) -> one == '?' 
			|| one == two || (two == '-' && one != 'x' && one != 'o');

	
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
	static FieldPosition fieldsMatch(String specifiedField, String actualField, PositionMatch matcher) {

		if (specifiedField.length() != 9) {
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
			 * 670
			 * 5 1
			 * 432
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
			
			while(rotated < 4 && matchFound == false) {
				
				final int start = (2*rotated);
				final int end = start + 8;
				matchFound = fieldsEqual(specifiedRing, 
						actualDoubledRing.subSequence(start, end),
						matcher);
				
				if(matchFound == false) {
					rotated++; 
				}
				if(rotated == 4 && mirrored == false) {
					mirrorRing(specifiedRing);
					mirrored = true;
					rotated = 0;					
				}
			}
			
			if(matchFound) {
				//TODO find expected field
			}
			
		}		

		return result;
	}
	
	private static boolean fieldsEqual(CharSequence one, CharSequence two, PositionMatch matcher) {
		if(one.length() != two.length()) {
			return false;
		}
		
		for(int i = 0; i < one.length(); i++) {
			if( matcher.positionsMatch(one.charAt(i), two.charAt(i)) == false ) {
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
		return new StringBuilder(8).append(s, 0, 2)
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
		final String reversedPart = ring.reverse().toString(); //76543210
		return ring.replace(1, ring.length(), reversedPart); //07654321
	}
	
}
