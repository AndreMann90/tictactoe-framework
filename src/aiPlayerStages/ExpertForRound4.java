package aiPlayerStages;

import playingField.FieldPosition;
import playingField.PlayingField;
import playingField.Positions;
import aiPlayerStages.ExpertPlayer.ExpertForRound;

public class ExpertForRound4 implements ExpertForRound {

	@Override
	public void useKnowledge(PlayingField field, Decisioner decisioner,
			int weight) {
		
		String[] fieldSpecifications = {};
		
		FieldPosition[] opponentMoves = { field.getPosFromHistory(1),
				field.getPosFromHistory(3) };
		
		/*
		 * Limit the number of specifications
		 */
		final int cornerPostionsOfOpponent = Positions.countPositionsInCorner(opponentMoves);
		
		if(cornerPostionsOfOpponent == 2) {
			fieldSpecifications = specificationTwoCornerPostionsOfOpponent();
		} else if(cornerPostionsOfOpponent == 1) {
			if(Positions.isOneOfThemCenter(opponentMoves)) {
				fieldSpecifications = specificationOneCornerOneCenterPostionsOfOpponent();
			} else {
				fieldSpecifications = specificationOneBetweenCornerOneCornerPostionsOfOpponent();				
			}
		} else { // no corner position of opponent
			if(Positions.isOneOfThemCenter(opponentMoves)== false) {
				fieldSpecifications = specificationTwoBetweenCornerPostionsOfOpponent();				
			} //else needs not to be specified
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
	
	
	
	private String[] specificationTwoCornerPostionsOfOpponent() {
		String[] fieldSpecifications = {
				  "xe-"
				+ "eoe"
				+ "-ex"
		};
		return fieldSpecifications;
	}
	
	private String[] specificationOneCornerOneCenterPostionsOfOpponent() {
		String[] fieldSpecifications = {
				  "x-e"
				+ "-x-"
				+ "e-o"
		};
		return fieldSpecifications;
	}
	
	private String[] specificationOneBetweenCornerOneCornerPostionsOfOpponent() {
		String[] fieldSpecifications = {
				  "xee"
				+ "-ox"
				+ "-ee",
				
				  "--x"
				+ "-e-"
				+ "?x?",
				
				  "xxo"
				+ "--e"
				+ "---"
		};
		return fieldSpecifications;
	}
	
	private String[] specificationTwoBetweenCornerPostionsOfOpponent() {
		String[] fieldSpecifications = {
				  "exe"
				+ "??x"
				+ "??e",
				
				  "?x?"
				+ "-ex"
				+ "--?"
		};
		return fieldSpecifications;
	}
}
