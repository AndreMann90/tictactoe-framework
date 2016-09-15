package de.mann.tictactoeframework.aiPlayerStages;

import de.mann.tictactoeframework.playingField.FieldPosition;
import de.mann.tictactoeframework.playingField.PlayingField;

class ExpertForRound6 extends ExpertForRoundSpecification {

    @Override
    protected String[] getSpecifications(PlayingField field) {

        String[] fieldSpecifications = {};

        FieldPosition[] opponentMoves = { field.getPosFromHistory(1),
                field.getPosFromHistory(3),
                field.getPosFromHistory(5) };

		/*
		 * Limit the number of specifications
		 */
        final int cornerPostionsOfOpponent =
                FieldPosition.countPositionsInCorner(opponentMoves);
        final boolean centerOwnedByOpponent = FieldPosition.isOneOfThemCenter(opponentMoves);

        if(cornerPostionsOfOpponent == 1 && !centerOwnedByOpponent) {
            fieldSpecifications = specificationTwoBetweenCornerOneCornerPostionsOfOpponent();
        } else if(cornerPostionsOfOpponent == 0 && centerOwnedByOpponent) {
            fieldSpecifications = specificationTwoBetweenCornerCenterPostionsOfOpponent();
        } else if(cornerPostionsOfOpponent == 3) {
            fieldSpecifications = specificationThreeCornerPostionsOfOpponent();
        } //no specifications to try

        return fieldSpecifications;
    }
	
	
	private String[] specificationTwoBetweenCornerOneCornerPostionsOfOpponent() {
        return new String[]{
                  "-?x"
                + "x??"
                + "ex-",

                  "??x"
                + "xe?"
                + "ox?"
        };
	}
	
	private String[] specificationTwoBetweenCornerCenterPostionsOfOpponent() {
        return new String[]{
                  "-oe"
                + "xxo"
                + "-x-"
        };
	}
	
	private String[] specificationThreeCornerPostionsOfOpponent() {
        return new String[]{
                  "xox"
                + "-eo"
                + "--x"
        };
	}
}
