package de.mann.tictactoeframework.aiPlayerStages;

import de.mann.tictactoeframework.players.PlayerID;
import de.mann.tictactoeframework.playingField.FieldPosition;
import de.mann.tictactoeframework.playingField.PlayingField;
import de.mann.tictactoeframework.playingField.Point;

public class OpportunityPlayer {

	public static void findOpportunity(PlayingField field,
                                       PlayerID myId, Decisioner decisioner) {

		final int weight = 1;
        final int fieldCount = 3;

        // Cross /
        int myPosCR = 0;
        Point freePosCR1 = null;
        Point freePosCR2 = null;
        // Cross \
        int myPosCL = 0;
        Point freePosCL1 = null;
        Point freePosCL2 = null;

        for (int i = 0; i < fieldCount; i++) {

            // Cross \
            if(field.isMyPosition(i, i, myId)) {
                myPosCL++;
            } else if(field.isPositionFree(i, i)) {
                if(freePosCL1 == null) {
                    freePosCL1 = new Point(i, i);
                } else if(freePosCL2 == null) {
                    freePosCL2 = new Point(i, i);
                } else {
                    myPosCL = fieldCount;
                }
            }
            // Cross /
            final int inv = (fieldCount - 1) - i; // inverted
            if(field.isMyPosition(i, inv, myId)) {
                myPosCR++;
            } else if(field.isPositionFree(i, inv)) {
                if(freePosCR1 == null) {
                    freePosCR1 = new Point(i, inv);
                } else if(freePosCR2 == null) {
                    freePosCR2 = new Point(i, inv);
                } else {
                    myPosCR = fieldCount;
                }
            }

            // vertical
            int myPosX = 0;
            Point freePosX1 = null;
            Point freePosX2 = null;
            // horizontal
            int myPosY = 0;
            Point freePosY1 = null;
            Point freePosY2 = null;
            for (int j = 0; j < fieldCount; j++) {
                // vertical
                if(field.isMyPosition(i, j, myId)) {
                    myPosX++;
                } else if(field.isPositionFree(i, j)) {
                    if(freePosX1 == null) {
                        freePosX1 = new Point(i, j);
                    } else if(freePosX2 == null) {
                        freePosX2 = new Point(i, j);
                    } else {
                        myPosX = fieldCount;
                    }
                }
                // horizontal
                if(field.isMyPosition(j, i, myId)) {
                    myPosY++;
                } else if(field.isPositionFree(j, i)) {
                    if(freePosY1 == null) {
                        freePosY1 = new Point(j, i);
                    } else if(freePosY2 == null) {
                        freePosY2 = new Point(j, i);
                    } else {
                        myPosY = fieldCount;
                    }
                }
            }
            // vertical
            if(myPosX == 1 && freePosX1 != null && freePosX2 != null) {
                decisioner.putPossibility(FieldPosition.fromPointToType(freePosX1), weight);
                decisioner.putPossibility(FieldPosition.fromPointToType(freePosX2), weight);
            }
            // horizontal
            if(myPosY == 1 && freePosY1 != null && freePosY2 != null) {
                decisioner.putPossibility(FieldPosition.fromPointToType(freePosY1), weight);
                decisioner.putPossibility(FieldPosition.fromPointToType(freePosY2), weight);
            }
        }

        // cross /
        if(myPosCR == 1 && freePosCR1 != null && freePosCR2 != null) {
            decisioner.putPossibility(FieldPosition.fromPointToType(freePosCR1), weight);
            decisioner.putPossibility(FieldPosition.fromPointToType(freePosCR2), weight);
        }
        // cross \
        if(myPosCL == 1 && freePosCL1 != null && freePosCL2 != null) {
            decisioner.putPossibility(FieldPosition.fromPointToType(freePosCL1), weight);
            decisioner.putPossibility(FieldPosition.fromPointToType(freePosCL2), weight);
        }
    }
}
