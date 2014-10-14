package players;

import exceptions.InvalidMoveException;
import gui.Display;

import java.awt.Point;
import java.util.Optional;

import aiPlayerStages.Decisioner;
import aiPlayerStages.ExpertPlayer;
import aiPlayerStages.RandomPlayer;
import aiPlayerStages.ThreeInARowPlayer;
import playingField.PlayingField;
import tictactoeGame.Move;

public class AIplayer extends Player {

	private PlayerID myPlayerID;
	private PlayerProperties myProperties;
	private PlayingField field;
	private Display display;

	public AIplayer(PlayerID playerID, PlayerProperties properties,
			Display display, PlayingField field) {
		
		this.myPlayerID = playerID;
		this.myProperties = properties;
		this.field = field;
		this.display = display;
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
	public void makeMove(Move move) {
		display.aiStartsMove(myProperties);
		
		Decisioner decisioner = new Decisioner();
		
		ExpertPlayer.useKnowlegdeToFindPosition(field, decisioner);
		
		if(decisioner.areTherePossibilities() == false) {
			ThreeInARowPlayer.findFinishingPosition(field, myPlayerID, decisioner);
		}
		
		if(decisioner.areTherePossibilities() == false) {
			ThreeInARowPlayer.spikeFinishingPosition(field, myPlayerID, decisioner);
		}
		
		if(decisioner.areTherePossibilities() == false) {
			RandomPlayer.determineRandomPosition(field, decisioner);
		}

		Optional<Point> decision = decisioner.decide();				

		try {
			move.makeMove(decision.get());
		} catch (InvalidMoveException e) {
			// should not happen
			e.printStackTrace();
		}
		
		
		display.aiFinishedMove(myProperties, decision);
	}

}
