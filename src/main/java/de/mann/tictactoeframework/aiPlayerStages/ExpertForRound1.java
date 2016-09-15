package de.mann.tictactoeframework.aiPlayerStages;

import de.mann.tictactoeframework.aiPlayerStages.ExpertPlayer.ExpertForRound;
import de.mann.tictactoeframework.playingField.FieldPosition;
import de.mann.tictactoeframework.playingField.PlayingField;

class ExpertForRound1 implements ExpertForRound {

	@Override
	public void useKnowledge(PlayingField field, Decisioner decisioner) {
		//36 percent chance for center
		decisioner.putPossibility(FieldPosition.Center, 35);
		//40 percent chance for a corner
		decisioner.putPossibilityForEachCorner(10);
		//24 percent chance for a corner
		decisioner.putPossibilityForEachEdge(6);
	}

}
