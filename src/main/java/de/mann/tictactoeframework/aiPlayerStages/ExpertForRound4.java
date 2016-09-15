package de.mann.tictactoeframework.aiPlayerStages;

import de.mann.tictactoeframework.playingField.FieldPosition;
import de.mann.tictactoeframework.playingField.PlayingField;

class ExpertForRound4 extends ExpertForRoundSpecification {

    @Override
    protected String[] getSpecifications(PlayingField field) {

        String[] fieldSpecifications = {};

        FieldPosition[] opponentMoves = { field.getPosFromHistory(1),
                field.getPosFromHistory(3) };

		/*
		 * Limit the number of specifications
		 */
        final int cornerPositionsOfOpponent = FieldPosition.countPositionsInCorner(opponentMoves);

        if(cornerPositionsOfOpponent == 2) {
            fieldSpecifications = specificationTwoCornerPositionsOfOpponent();
        } else if(cornerPositionsOfOpponent == 1) {
            if(FieldPosition.isOneOfThemCenter(opponentMoves)) {
                fieldSpecifications = specificationOneCornerOneCenterPositionsOfOpponent();
            } else {
                fieldSpecifications = specificationOneBetweenCornerOneCornerPositionsOfOpponent();
            }
        } else { // no corner position of opponent
            if(!FieldPosition.isOneOfThemCenter(opponentMoves)) {
                fieldSpecifications = specificationTwoBetweenCornerPositionsOfOpponent();
            } //else needs not to be specified
        }

        return fieldSpecifications;
    }
	
	
	private String[] specificationTwoCornerPositionsOfOpponent() {
		return new String[]{
				  "xe-"
				+ "eoe"
				+ "-ex"
		};
	}
	
	private String[] specificationOneCornerOneCenterPositionsOfOpponent() {
		return new String[]{
				  "x-e"
				+ "-x-"
				+ "e-o"
		};
	}
	
	private String[] specificationOneBetweenCornerOneCornerPositionsOfOpponent() {
		return new String[]{
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
	}
	
	private String[] specificationTwoBetweenCornerPositionsOfOpponent() {
		return new String[]{
				  "exe"
				+ "??x"
				+ "??e",

				  "?x?"
				+ "-ex"
				+ "--?"
		};
	}
}
