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
	 * specified, expected position
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
			 */
			boolean fieldsMatch = fieldsEqual(specifiedField, actualField, matcher);
			
			int rotation = 0;
			boolean mirrored = false;
			
			if(fieldsMatch == false) {

				StringBuilder field1 = rotateFieldClockwise(specifiedField);
				StringBuilder field2 = rotateFieldClockwise(actualField);
				rotation++;
				
				fieldsMatch = fieldsEqual(field1, field2, matcher);
				
				while (fieldsMatch == false && rotation < 3) {
					field1 = rotateFieldClockwise(field1);
					field2 = rotateFieldClockwise(field2);
					rotation++;

					fieldsMatch = fieldsEqual(field1, field2, matcher);
				}
				
				if(fieldsMatch == false) {
					mirrored = true;
					//mirror! 
					//TODO implement rest
				}
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
	
	private static StringBuilder rotateFieldClockwise(String s) {
		/*
		 * from:
		 * 012
		 * 345
		 * 678
		 * 
		 * to:
		 * 630
		 * 7 1
		 * 852
		 * 
		 * node: the center is left out! (because it's invariant)
		 */
		return new StringBuilder(10).append(s.charAt(6))
				.append(s.charAt(3))
				.append(s, 0, 2)
				.append(s.charAt(5))
				.append(s.charAt(8))
				.append(s.charAt(7));		
	}
	
	private static StringBuilder rotateFieldClockwise(StringBuilder s) {
		/*
		 * from:
		 * 012
		 * 7 3
		 * 654
		 * 
		 * to:
		 * 670
		 * 5 1
		 * 432
		 * 
		 *  note: delete from 8 till 9 because the insertion before 
		 *  increased the length of s by 2 
		 */
		return s.insert(0, s, 6, 7).delete(8, 9); 		
	}
	
}
