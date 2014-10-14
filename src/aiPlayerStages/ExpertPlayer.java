package aiPlayerStages;

import playingField.PlayingField;

public class ExpertPlayer {

	private static ExpertForRound[] experts = 
		{
		new ExpertForRound1(),
		new ExpertForRound2(),
		new ExpertForRound3()
		};

	public static void useKnowlegdeToFindPosition(PlayingField field,
			Decisioner decisioner) {
		
		if(field.getRound() <= experts.length) {
			experts[field.getRound()-1].useKnowledge(field, decisioner, 1);
		}		
	}

	@FunctionalInterface
	public interface ExpertForRound {
		void useKnowledge(PlayingField field, Decisioner decisioner, int weight);
	}
}
