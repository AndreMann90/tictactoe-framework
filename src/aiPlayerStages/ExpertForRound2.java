package aiPlayerStages;

import java.util.Random;

import playingField.FieldPosition;
import playingField.PlayingField;
import playingField.Positions;
import aiPlayerStages.ExpertPlayer.ExpertForRound;

public class ExpertForRound2 implements ExpertForRound {

	@Override
	public void useKnowledge(PlayingField field, Decisioner decisioner, int weight) {
		final FieldPosition firstMove = field.getPosFromHistory(1);
		final Random r = new Random();
		
		if(Positions.isCenter(firstMove)) {
			decisioner.putPossibility(Positions.randomCorner(r), weight);
		} else if (Positions.isCorner(firstMove))  {
			/*
			 * x--
			 * -e-
			 * ---
			 */
			decisioner.putPossibility(FieldPosition.Center, weight);
		} else {
			/*
			 * exe
			 * -e-
			 * ---
			 */
			if(r.nextBoolean()) {
				decisioner.putPossibility(FieldPosition.Center, weight);
			} else {
				final boolean clockwise = r.nextBoolean();
				decisioner.putPossibility(Positions
						.rotate(clockwise, firstMove, 1), weight);
			}
		}
	}

}
