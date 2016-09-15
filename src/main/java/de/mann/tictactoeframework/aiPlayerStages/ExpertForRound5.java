package de.mann.tictactoeframework.aiPlayerStages;

import de.mann.tictactoeframework.playingField.FieldPosition;
import de.mann.tictactoeframework.playingField.PlayingField;

class ExpertForRound5 extends ExpertForRoundSpecification {

	@Override
	protected String[] getSpecifications(PlayingField field) {

		final String[] fieldSpecifications;

		FieldPosition[] myMoves = { field.getPosFromHistory(1),
				field.getPosFromHistory(3) };

		/*
		 * Limit the number of specifications
		 */
		final int cornerPositions = FieldPosition.countPositionsInCorner(myMoves);

		if(cornerPositions == 2) {
			fieldSpecifications = specificationTwoCornerPositions();
		} else if(cornerPositions == 1) {
			if(FieldPosition.isOneOfThemCenter(myMoves)) {
				fieldSpecifications = specificationOneCornerOneCenterPositions();
			} else {
				fieldSpecifications = specificationOneBetweenCornerOneCornerPositions();
			}
		} else { // no corner position
			if(!FieldPosition.isOneOfThemCenter(myMoves)) {
				fieldSpecifications = specificationTwoBetweenCornerPositions();
			} else {
				fieldSpecifications = specificationOneBetweenCornerOneCenterPositions();
			}
		}

		return fieldSpecifications;
	}

	private String[] specificationTwoCornerPositions() {
        return new String[]{
                  "xox"
                + "?e?"
                + "-?-",

                  "xox"
                + "--?"
                + "e-?"
        };
	}
	
	private String[] specificationOneCornerOneCenterPositions() {
        return new String[]{
                  "xo-"
                + "-x-"
                + "e-o"
        };
	}
	
	private String[] specificationOneBetweenCornerOneCornerPositions() {
        return new String[]{
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
	}
	
	private String[] specificationTwoBetweenCornerPositions() {
        return new String[]{
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
	}
	
	private String[] specificationOneBetweenCornerOneCenterPositions() {
        return new String[]{
                  "ex-"
                + "?x?"
                + "-o-",

                  "-xo"
                + "exe"
                + "eoe"
        };
	}

}
