package aiPlayerStages;

import playingField.FieldPosition;
import playingField.PlayingField;
import playingField.Positions;
import aiPlayerStages.ExpertPlayer.ExpertForRound;

public class ExpertForRound7 implements ExpertForRound {

	@Override
	public void useKnowledge(PlayingField field, Decisioner decisioner,
			int weight) {
		
		String[] fieldSpecifications = {};
		
		FieldPosition[] myMoves = { field.getPosFromHistory(1),
				field.getPosFromHistory(3),
				field.getPosFromHistory(5) };
		
		/*
		 * Limit the number of specifications
		 */
		final int cornerPositions = 
				Positions.countPositionsInCorner(myMoves);
		final boolean centerOwned = Positions.isOneOfThemCenter(myMoves);
		
		if(cornerPositions == 1 && centerOwned == false) {
			fieldSpecifications = specificationTwoBetweenCornerOneCornerPostionsOfOpponent();
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
				  "-ox"
				+ "xoo"
				+ "ex-"
		};
		return fieldSpecifications;
	}
}
