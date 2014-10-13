package aiPlayerStages;


import players.PlayerID;
import playingField.FieldPosition;
import playingField.PlayingField;

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
					&& field.isPostionFree(FieldPosition.BottomRight)) {
				decisioner.putPossibility(FieldPosition.BottomRight, weight);
			} else if (field.isMyPosition(FieldPosition.BottomRight, id)
					&& field.isPostionFree(FieldPosition.TopLeft)) {
				decisioner.putPossibility(FieldPosition.TopLeft, weight);
			}
			
			// cross: /
			if (field.isMyPosition(FieldPosition.TopRight, id)
					&& field.isPostionFree(FieldPosition.BottomLeft)) {
				decisioner.putPossibility(FieldPosition.BottomLeft, weight);
			} else if (field.isMyPosition(FieldPosition.BottomLeft, id)
					&& field.isPostionFree(FieldPosition.TopRight)) {
				decisioner.putPossibility(FieldPosition.TopRight, weight);
			}

			// vertical: |
			if (field.isMyPosition(FieldPosition.Top, id)
					&& field.isPostionFree(FieldPosition.Bottom)) {
				decisioner.putPossibility(FieldPosition.Bottom, weight);
			} else if (field.isMyPosition(FieldPosition.Bottom, id)
					&& field.isPostionFree(FieldPosition.Top)) {
				decisioner.putPossibility(FieldPosition.Top, weight);
			}
			
			// horizontal: -
			if (field.isMyPosition(FieldPosition.Right, id)
					&& field.isPostionFree(FieldPosition.Left)) {
				decisioner.putPossibility(FieldPosition.Left, weight);
			} else if (field.isMyPosition(FieldPosition.Left, id)
					&& field.isPostionFree(FieldPosition.Right)) {
				decisioner.putPossibility(FieldPosition.Right, weight);
			}

		} else if (field.isPostionFree(FieldPosition.Center)) { // is the center free
			
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
					&& field.isPostionFree(FieldPosition.TopRight)) {
				decisioner.putPossibility(FieldPosition.TopRight, weight);
			} else if (field.isMyPosition(FieldPosition.TopRight, id)
					&& field.isPostionFree(FieldPosition.Top)) {
				decisioner.putPossibility(FieldPosition.Top, weight);
			}
			
			//vertical, left
			if (field.isMyPosition(FieldPosition.Left, id)
					&& field.isPostionFree(FieldPosition.BottomLeft)) {
				decisioner.putPossibility(FieldPosition.BottomLeft, weight);
			} else if (field.isMyPosition(FieldPosition.BottomLeft, id)
					&& field.isPostionFree(FieldPosition.Left)) {
				decisioner.putPossibility(FieldPosition.Left, weight);
			}
			
		} else if(field.isPostionFree(FieldPosition.TopLeft)) {
			
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
					&& field.isPostionFree(FieldPosition.BottomLeft)) {
				decisioner.putPossibility(FieldPosition.BottomLeft, weight);
			} else if (field.isMyPosition(FieldPosition.BottomLeft, id)
					&& field.isPostionFree(FieldPosition.Bottom)) {
				decisioner.putPossibility(FieldPosition.Bottom, weight);
			}
			
			//vertical, right
			if (field.isMyPosition(FieldPosition.Right, id)
					&& field.isPostionFree(FieldPosition.TopRight)) {
				decisioner.putPossibility(FieldPosition.TopRight, weight);
			} else if (field.isMyPosition(FieldPosition.TopRight, id)
					&& field.isPostionFree(FieldPosition.Right)) {
				decisioner.putPossibility(FieldPosition.Right, weight);
			}
			
		} else if(field.isPostionFree(FieldPosition.BottomRight)) {
			
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
