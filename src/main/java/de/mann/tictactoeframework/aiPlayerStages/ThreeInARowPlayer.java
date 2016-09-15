package de.mann.tictactoeframework.aiPlayerStages;

import de.mann.tictactoeframework.players.PlayerID;
import de.mann.tictactoeframework.playingField.FieldPosition;
import de.mann.tictactoeframework.playingField.PlayingField;

public class ThreeInARowPlayer {
	
	public static void findFinishingPosition(PlayingField field,
			PlayerID myID, Decisioner decisioner) {
			
		determineFinishingPosition(field, myID, decisioner);
		
	}
	
	public static void spikeFinishingPosition(PlayingField field,
			PlayerID myID, Decisioner decisioner) {
			
		determineFinishingPosition(field, PlayerID.getOpponent(myID), decisioner);
		
	}

	private static void determineFinishingPosition(PlayingField field,
			PlayerID id, Decisioner decisioner) {

		final int weight = 1;
		
		/*
		 * Since there are not that many possibilities, this is 
		 * implemented with only if-statements with clever processing
		 * instead of for-statements with less clever processing
		 */
		
		if (field.isMyPosition(FieldPosition.Center, id)) { // do I own the center

			// cross like: \
			if (field.isMyPosition(FieldPosition.TopLeft, id)
					&& field.isPositionFree(FieldPosition.BottomRight)) {
				decisioner.putPossibility(FieldPosition.BottomRight, weight);
			} else if (field.isMyPosition(FieldPosition.BottomRight, id)
					&& field.isPositionFree(FieldPosition.TopLeft)) {
				decisioner.putPossibility(FieldPosition.TopLeft, weight);
			}
			
			// cross: /
			if (field.isMyPosition(FieldPosition.TopRight, id)
					&& field.isPositionFree(FieldPosition.BottomLeft)) {
				decisioner.putPossibility(FieldPosition.BottomLeft, weight);
			} else if (field.isMyPosition(FieldPosition.BottomLeft, id)
					&& field.isPositionFree(FieldPosition.TopRight)) {
				decisioner.putPossibility(FieldPosition.TopRight, weight);
			}

			// vertical: |
			if (field.isMyPosition(FieldPosition.Top, id)
					&& field.isPositionFree(FieldPosition.Bottom)) {
				decisioner.putPossibility(FieldPosition.Bottom, weight);
			} else if (field.isMyPosition(FieldPosition.Bottom, id)
					&& field.isPositionFree(FieldPosition.Top)) {
				decisioner.putPossibility(FieldPosition.Top, weight);
			}
			
			// horizontal: -
			if (field.isMyPosition(FieldPosition.Right, id)
					&& field.isPositionFree(FieldPosition.Left)) {
				decisioner.putPossibility(FieldPosition.Left, weight);
			} else if (field.isMyPosition(FieldPosition.Left, id)
					&& field.isPositionFree(FieldPosition.Right)) {
				decisioner.putPossibility(FieldPosition.Right, weight);
			}

		} else if (field.isPositionFree(FieldPosition.Center)) { // is the center free
			
			final boolean makeCenter = 
					(field.isMyPosition(FieldPosition.TopLeft, id) && field.isMyPosition(FieldPosition.BottomRight, id))
					|| (field.isMyPosition(FieldPosition.TopRight, id) && field.isMyPosition(FieldPosition.BottomLeft, id))
					|| (field.isMyPosition(FieldPosition.Top, id) && field.isMyPosition(FieldPosition.Bottom, id))
					|| (field.isMyPosition(FieldPosition.Left, id) && field.isMyPosition(FieldPosition.Right, id));
			
			if(makeCenter) {
				decisioner.putPossibility(FieldPosition.Center, weight);
			}
			
		} 

		if(field.isMyPosition(FieldPosition.TopLeft, id)) {
			
			//horizontal, top
			if (field.isMyPosition(FieldPosition.Top, id)
					&& field.isPositionFree(FieldPosition.TopRight)) {
				decisioner.putPossibility(FieldPosition.TopRight, weight);
			} else if (field.isMyPosition(FieldPosition.TopRight, id)
					&& field.isPositionFree(FieldPosition.Top)) {
				decisioner.putPossibility(FieldPosition.Top, weight);
			}
			
			//vertical, left
			if (field.isMyPosition(FieldPosition.Left, id)
					&& field.isPositionFree(FieldPosition.BottomLeft)) {
				decisioner.putPossibility(FieldPosition.BottomLeft, weight);
			} else if (field.isMyPosition(FieldPosition.BottomLeft, id)
					&& field.isPositionFree(FieldPosition.Left)) {
				decisioner.putPossibility(FieldPosition.Left, weight);
			}
			
		} else if(field.isPositionFree(FieldPosition.TopLeft)) {
			
			final boolean makeTopLeft = 
					(field.isMyPosition(FieldPosition.Top, id) 
					&& field.isMyPosition(FieldPosition.TopRight, id))
					||
					(field.isMyPosition(FieldPosition.Left, id) 
					&& field.isMyPosition(FieldPosition.BottomLeft, id));
			
			if(makeTopLeft) {
				decisioner.putPossibility(FieldPosition.TopLeft, weight);
			}
			
		}

		if(field.isMyPosition(FieldPosition.BottomRight, id)) {
			
			//horizontal, bottom
			if (field.isMyPosition(FieldPosition.Bottom, id)
					&& field.isPositionFree(FieldPosition.BottomLeft)) {
				decisioner.putPossibility(FieldPosition.BottomLeft, weight);
			} else if (field.isMyPosition(FieldPosition.BottomLeft, id)
					&& field.isPositionFree(FieldPosition.Bottom)) {
				decisioner.putPossibility(FieldPosition.Bottom, weight);
			}
			
			//vertical, right
			if (field.isMyPosition(FieldPosition.Right, id)
					&& field.isPositionFree(FieldPosition.TopRight)) {
				decisioner.putPossibility(FieldPosition.TopRight, weight);
			} else if (field.isMyPosition(FieldPosition.TopRight, id)
					&& field.isPositionFree(FieldPosition.Right)) {
				decisioner.putPossibility(FieldPosition.Right, weight);
			}
			
		} else if(field.isPositionFree(FieldPosition.BottomRight)) {
			
			final boolean makeBottomRight = 
					(field.isMyPosition(FieldPosition.Right, id) 
					&& field.isMyPosition(FieldPosition.TopRight, id))
					|| 
					(field.isMyPosition(FieldPosition.Bottom, id) 
					&& field.isMyPosition(FieldPosition.BottomLeft, id));
			
			if(makeBottomRight) {
				decisioner.putPossibility(FieldPosition.BottomRight, weight);
			}
			
		}

	}
}
