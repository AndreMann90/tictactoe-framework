package aiPlayerStages;

import playingField.FieldPosition;
import playingField.PlayingField;
import playingField.Positions;
import aiPlayerStages.ExpertPlayer.ExpertForRound;

public class ExpertForRound5 implements ExpertForRound {

	@Override
	public void useKnowledge(PlayingField field, Decisioner decisioner,
			int weight) {
		
		String[] fieldSpecifications = {};
		
		FieldPosition[] myMoves = { field.getPosFromHistory(1),
				field.getPosFromHistory(3) };
		
		/*
		 * Limit the number of specifications
		 */
		final int cornerPostions = Positions.countPositionsInCorner(myMoves);
		
		if(cornerPostions == 2) {
			fieldSpecifications = specificationTwoCornerPostions();
		} else if(cornerPostions == 1) {
			if(Positions.isOneOfThemCenter(myMoves)) {
				fieldSpecifications = specificationOneCornerOneCenterPostions();
			} else {
				fieldSpecifications = specificationOneBetweenCornerOneCornerPostions();				
			}
		} else { // no corner position
			if(Positions.isOneOfThemCenter(myMoves)== false) {
				fieldSpecifications = specificationTwoBetweenCornerPostions();				
			} else {
				fieldSpecifications = specificationOneBetweenCornerOneCenterPostions();
			}
		}
		
		
		/*
		 * Try all the specifications
		 */
		FieldPosition makeHere = FieldPosition.Empty;

		final String actualField = field.toString();
		for (int i = 0; i < fieldSpecifications.length && makeHere == FieldPosition.Empty; i++) {
			makeHere = FieldMatcher.fieldsMatch(fieldSpecifications[i],
					actualField, FieldMatcher.DEFAULT_MATCH);
		}
		
		if(makeHere != FieldPosition.Empty) {
			decisioner.putPossibility(makeHere, weight);
		}
	}
	
	
	
	private String[] specificationTwoCornerPostions() {
		String[] fieldSpecifications = {
				  "xox"
				+ "?e?"
				+ "-?-",
				
				  "xox"
				+ "--?"
				+ "e-?"
		};
		return fieldSpecifications;
	}
	
	private String[] specificationOneCornerOneCenterPostions() {
		String[] fieldSpecifications = {
				  "xo-"
				+ "-x-"
				+ "e-o"
		};
		return fieldSpecifications;
	}
	
	private String[] specificationOneBetweenCornerOneCornerPostions() {
		String[] fieldSpecifications = {		
				  "xee"
				+ "-ox"
				+ "eeo",
				
				  "xxo"
				+ "oe-"
				+ "---",
				
				  "x-e"
				+ "?ox"
				+ "?--",
				
				  "x??"
				+ "e-x"
				+ "--?",
				
				  "x?o"
				+ "-ex"
				+ "??-"					
		};
		return fieldSpecifications;
	}
	
	private String[] specificationTwoBetweenCornerPostions() {
		String[] fieldSpecifications = {				
				  "?x?"
				+ "xe-"
				+ "?--",
				
				  "ex-"
				+ "x??"
				+ "-?-",
				
				  "ox-"
				+ "xe?"
				+ "-?-",
				
				  "-xo"
				+ "xee"
				+ "eo-"
		};
		return fieldSpecifications;
	}
	
	private String[] specificationOneBetweenCornerOneCenterPostions() {
		String[] fieldSpecifications = {				
				  "ex-"
				+ "?x?"
				+ "-o-",
				
				  "-xo"
				+ "exe"
				+ "eoe"
		};
		return fieldSpecifications;
	}

}
