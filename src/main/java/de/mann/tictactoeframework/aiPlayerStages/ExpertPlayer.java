package de.mann.tictactoeframework.aiPlayerStages;

import de.mann.tictactoeframework.playingField.PlayingField;

public class ExpertPlayer {

	private final static ExpertForRound[] experts =
		{
		new ExpertForRound1(),
		new ExpertForRound2(),
		new ExpertForRound3(),
		new ExpertForRound4(),
		new ExpertForRound5(),
		new ExpertForRound6(),
		new ExpertForRound7()
		};

	public static void useKnowledgeToFindPosition(PlayingField field,
												  Decisioner decisioner) {
		
		if(field.getRound() <= experts.length) {
			experts[field.getRound()-1].useKnowledge(field, decisioner);
		}		
	}

	public interface ExpertForRound {
		void useKnowledge(PlayingField field, Decisioner decisioner);
	}
}
