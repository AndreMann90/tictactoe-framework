package de.mann.tictactoeframework.players;

import de.mann.tictactoeframework.aiPlayerStages.*;
import de.mann.tictactoeframework.exceptions.InvalidMoveException;
import de.mann.tictactoeframework.gui.Display;
import de.mann.tictactoeframework.playingField.FieldPosition;
import de.mann.tictactoeframework.playingField.PlayingField;
import de.mann.tictactoeframework.tictactoeGame.Move;

import java.util.Random;

public class AIplayer extends Player {

	private final PlayerID myPlayerID;
	private final PlayerProperties myProperties;
	private final PlayingField field;
	private final Display display;
	private final Random random;

	public AIplayer(PlayerID playerID, PlayerProperties properties,
					Display display, PlayingField field, Random r) {
		
		this.myPlayerID = playerID;
		this.myProperties = properties;
		this.field = field;
		this.display = display;
		this.random = r;
	}

	@Override
	public PlayerID getPlayerID() {
		return myPlayerID;
	}

	@Override
	public PlayerProperties getProperties() {
		return myProperties;
	}

	@Override
	public void makeMove(final Move move) {
		display.aiStartsMove(myProperties);
		
		Decisioner decisioner = new Decisioner(random);
		
		if(fulfilled(myProperties.knowledge)) {
			ExpertPlayer.useKnowledgeToFindPosition(field, decisioner);
		}
		
		if(!decisioner.areTherePossibilities() && fulfilled(myProperties.finisher)) {
			ThreeInARowPlayer.findFinishingPosition(field, myPlayerID, decisioner);
		}
		
		if(!decisioner.areTherePossibilities() && fulfilled(myProperties.spiker)) {
			ThreeInARowPlayer.spikeFinishingPosition(field, myPlayerID, decisioner);
		}

        if(!decisioner.areTherePossibilities()) {
            OpportunityPlayer.findOpportunity(field, myPlayerID, decisioner);
        }
		
		if(!decisioner.areTherePossibilities()) {
			RandomPlayer.determineRandomPosition(field, decisioner);
		}

		final FieldPosition decision = decisioner.decide();

		display.aiFinishedMove(myProperties, decision);
		try {
			move.makeMove(decision);
		} catch (InvalidMoveException e) {
			// should not happen
			e.printStackTrace();
		}
	}

	private boolean fulfilled(final int aiStageChance) {
		return aiStageChance >= 100 || random.nextInt(100) < aiStageChance;
	}

}
