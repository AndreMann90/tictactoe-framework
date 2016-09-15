package de.mann.tictactoeframework.aiPlayerStages;

import de.mann.tictactoeframework.playingField.FieldPosition;
import de.mann.tictactoeframework.playingField.PlayingField;


public abstract class ExpertForRoundSpecification implements ExpertPlayer.ExpertForRound {
    @Override
    public void useKnowledge(PlayingField field, Decisioner decisioner) {

        final String[] fieldSpecifications = getSpecifications(field);

        /*
		 * Try all the specifications
		 */
        FieldPosition makeHere = FieldPosition.Empty;

        final String actualField = field.toString();
        for (int i = 0; i < fieldSpecifications.length && makeHere == FieldPosition.Empty; i++) {
            makeHere = FieldMatcher.fieldsMatch(fieldSpecifications[i],
                    actualField, FieldMatcher.DEFAULT_MATCH, decisioner.getRandom());
        }

        if(makeHere != FieldPosition.Empty) {
            decisioner.putPossibility(makeHere, 1);
        }
    }

    protected abstract String[] getSpecifications(PlayingField field);
}
