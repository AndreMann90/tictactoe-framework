package aiPlayerStages;

import playingField.FieldPosition;
import playingField.PlayingField;
import playingField.Positions;
import aiPlayerStages.ExpertPlayer.ExpertForRound;

public class ExpertForRound6 implements ExpertForRound {

	@Override
	public void useKnowledge(PlayingField field, Decisioner decisioner,
			int weight) {
		
		String[] fieldSpecifications = {};
		
		FieldPosition[] opponentMoves = { field.getPosFromHistory(1),
				field.getPosFromHistory(3),
				field.getPosFromHistory(5) };
		
		/*
		 * Limit the number of specifications
		 */
		final int cornerPostionsOfOpponent = 
				Positions.countPositionsInCorner(opponentMoves);
		final boolean centerOwnedByOpponent = Positions.isOneOfThemCenter(opponentMoves);
		
		if(cornerPostionsOfOpponent == 1 && centerOwnedByOpponent == false) {
			fieldSpecifications = specificationTwoBetweenCornerOneCornerPostionsOfOpponent();
		} else if(cornerPostionsOfOpponent == 0 && centerOwnedByOpponent == true) {
			fieldSpecifications = specificationTwoBetweenCornerCenterPostionsOfOpponent();
		} else if(cornerPostionsOfOpponent == 3) {
			fieldSpecifications = specificationThreeCornerPostionsOfOpponent();
		} else {
			//no specifications to try
			return;
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
	
	
	
	private String[] specificationTwoBetweenCornerOneCornerPostionsOfOpponent() {
		String[] fieldSpecifications = {
				  "-?x"
				+ "x??"
				+ "ex-",
				
				  "??x"
				+ "xe?"
				+ "ox?"
		};
		return fieldSpecifications;
	}
	
	private String[] specificationTwoBetweenCornerCenterPostionsOfOpponent() {
		String[] fieldSpecifications = {
				  "-oe"
				+ "xxo"
				+ "-x-"
		};
		return fieldSpecifications;
	}
	
	private String[] specificationThreeCornerPostionsOfOpponent() {
		String[] fieldSpecifications = {
				  "xox"
				+ "-eo"
				+ "--x"
		};
		return fieldSpecifications;
	}
}
