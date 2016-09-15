package de.mann.tictactoeframework.aiPlayerStages;

import de.mann.tictactoeframework.playingField.FieldPosition;
import de.mann.tictactoeframework.playingField.PlayingField;

class ExpertForRound7 extends ExpertForRoundSpecification {


	@Override
	protected String[] getSpecifications(PlayingField field) {
		String[] fieldSpecifications = {};

		FieldPosition[] myMoves = { field.getPosFromHistory(1),
				field.getPosFromHistory(3),
				field.getPosFromHistory(5) };

		/*
		 * Limit the number of specifications
		 */
		final int cornerPositions =
                FieldPosition.countPositionsInCorner(myMoves);
		final boolean centerOwned = FieldPosition.isOneOfThemCenter(myMoves);

		if(cornerPositions == 1 && !centerOwned) {
			fieldSpecifications = specificationTwoBetweenCornerOneCornerPostionsOfOpponent();
		} //no specifications to try

        return fieldSpecifications;
	}


	private String[] specificationTwoBetweenCornerOneCornerPostionsOfOpponent() {
        return new String[]{
                  "-ox"
                + "xoo"
                + "ex-"
        };
	}
}
