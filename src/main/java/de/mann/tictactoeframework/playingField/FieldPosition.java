package de.mann.tictactoeframework.playingField;

import java.util.Random;

public enum FieldPosition {
	Empty(-1), TopLeft(0), Top(1), TopRight(2), Right(3), 
	BottomRight(4), Bottom(5), BottomLeft(6), Left(7), Center(8);

	private int pos;
	
	FieldPosition(int c) {
		pos = c;
	}
	 
	public int getPosition() {
		return pos;
	}

	public boolean isEmpty() {
		return this == Empty;
	}

	////////////////////////////////////////////////////////////////////////////////////////////////
	// Conversions /////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////

    private static final Point[] typeToPointMap = {null, new Point(0,0), new Point(1,0), new Point(2,0), new Point(2,1),
            new Point(2,2), new Point(1,2), new Point(0,2), new Point(0,1), new Point(1,1)};

    public Point getPoint () {
        return typeToPointMap[this.getPosition()+1];
    }

	private static final FieldPosition[][] pointToTypeMap = {
			{TopLeft, Left, BottomLeft},
			{Top, Center, Bottom},
			{TopRight, Right, BottomRight}
	};

	public static FieldPosition fromPointToType (final Point from) {
		if(from.x >= 0 && from.x <= 2 && from.y >= 0 && from.y <= 2) {
			return pointToTypeMap[from.x][from.y];
		} else {
			return FieldPosition.Empty;
		}
	}
	
	private static final FieldPosition[] intToTypeMap = {TopLeft, Top, TopRight, Right,
			BottomRight, Bottom, BottomLeft, Left, Center};

	public static FieldPosition fromInt(int i) {
	    if (i < 0 || i > 8) {
            return FieldPosition.Empty;
		} else {
            return intToTypeMap[i];
        }
	}

	////////////////////////////////////////////////////////////////////////////////////////////////
	// Operations //////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////

	public boolean isCorner() {
		final int pos = this.getPosition();
		return pos != 8 && pos % 2 == 0;
	}

	public static int countPositionsInCorner(final FieldPosition[] positions) {
		int count = 0;
		for (FieldPosition position : positions) {
			if (position.isCorner()) {
				count++;
			}
		}
		return count;
	}

	public boolean isCenter() {
		return this == Center;
	}

	public static boolean isOneOfThemCenter(final FieldPosition[] positions) {
		for (FieldPosition position : positions) {
			if (position.isCenter()) {
				return true;
			}
		}
		return false;
	}

	public boolean isBetweenCorners() {
		final int pos = this.getPosition();
		return pos % 2 == 1;
	}

	public static boolean areNeighbors(final FieldPosition pos1, final FieldPosition pos2) {
		final int diff = Math.abs(pos1.getPosition() - pos2.getPosition());
		return diff == 1 || pos1.isCenter() || pos2.isCenter() || diff == 7;
		/*                          o--      x--
		 * diff == 7 for this case: x-- and: o--
		 *                          ---      ---
		 */
	}

	public FieldPosition rotate (final boolean clockwise, final int by) {
		if(clockwise) {
			return rotateRight(by);
		} else {
			return rotateLeft(by);
		}
	}

	public FieldPosition rotateRight (final int by) {
		if(this.isCenter()) {
			return Center;
		}
		final int newPosNumber = (this.getPosition() + by) % 8;
		return FieldPosition.fromInt(newPosNumber);
	}

	public FieldPosition rotateLeft (final int by) {
		return rotateRight(8 - by);
	}

	public FieldPosition opposite () {
		return rotateRight(4);
	}

	public FieldPosition mirror () {
		if(isCenter()) {
			return FieldPosition.Center;
		} else {
			final int rep = this.getPosition();
			return FieldPosition.fromInt((8 - rep) % 8);
		}
	}

	@Deprecated
	public static FieldPosition randomCorner(Random r) {
		if(r == null) {
			r = new Random();
		}
		final int corner = r.nextInt(4) * 2;
		return FieldPosition.fromInt(corner);
	}

	@Deprecated
	public static FieldPosition randomPosBetweenCorners(Random r) {
		return randomCorner(r).rotateRight(1);
	}
	
	
}
