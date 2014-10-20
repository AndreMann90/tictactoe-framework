package playingField;

import java.awt.Point;
import java.util.Random;

public class Positions {
	
	public static Point fromTypeToPoint (final FieldPosition from) {
		switch (from) {
		
		case TopLeft: return new Point(0,0);
		case Top: return new Point(0,1);
		case TopRight: return new Point(0,2);
		case Left: return new Point(1,0);
		case Center: return new Point(1,1);
		case Right: return new Point(1,2);
		case BottomLeft: return new Point(2,0);
		case Bottom: return new Point(2,1);
		case BottomRight: return new Point(2,2);
		
		default: return null;
		
		}
	}
	
	public static FieldPosition fromPointToType (final Point from) {
		if(from.x == 0) {
			if(from.y == 0) {
				return FieldPosition.TopLeft;
			} else if(from.y == 1) {
				return FieldPosition.Top;
			} else if(from.y == 2) {
				return FieldPosition.TopRight;
			}
		} else if(from.x == 1) {
			if(from.y == 0) {
				return FieldPosition.Left;
			} else if(from.y == 1) {
				return FieldPosition.Center;
			} else if(from.y == 2) {
				return FieldPosition.Right;
			}
		}else if(from.x == 2) {
			if(from.y == 0) {
				return FieldPosition.BottomLeft;
			} else if(from.y == 1) {
				return FieldPosition.Bottom;
			} else if(from.y == 2) {
				return FieldPosition.BottomRight;
			}
		}

		return FieldPosition.Empty;
	}
	
	public static boolean isCorner(final FieldPosition position) {
		final int pos = position.getPosition();
		return pos != 8 && pos % 2 == 0;
	}
	
	public static int countPositionsInCorner(final FieldPosition[] positions) {
		int count = 0;
		for(int i = 0; i < positions.length; i++) {
			if(isCorner(positions[i])) {
				count++;
			}
		}
		return count;
	}
	
	public static boolean isCenter(final FieldPosition position) {
		final int pos = position.getPosition();
		return pos == 8;
	}
	
	public static boolean isOneOfThemCenter(final FieldPosition[] positions) {
		for(int i = 0; i < positions.length; i++) {
			if(isCenter(positions[i])) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean isBetweenCorners(final FieldPosition position) {
		final int pos = position.getPosition();
		return pos % 2 == 1;
	}
	
	public static boolean areNeighbors(final FieldPosition pos1, final FieldPosition pos2) {
		final int diff = Math.abs(pos1.getPosition() - pos2.getPosition());
		return diff == 1 || isCenter(pos1) || isCenter(pos2);
	}
	
	public static FieldPosition rotate (final boolean clockwise, final FieldPosition from, final int by) {
		if(clockwise) {
			return rotateRight(from, by);
		} else {
			return rotateLeft(from, by);
		}
	}
	
	public static FieldPosition rotateRight (final FieldPosition from, final int by) {
		if(isCenter(from)) {
			return from;
		}
		final int newPosNumber = (from.getPosition() + by) % 8;
		return FieldPosition.fromInt(newPosNumber);
	}
	
	public static FieldPosition rotateLeft (final FieldPosition from, final int by) {
		return rotateRight(from, (-1 * by));
	}
	
	public static FieldPosition opposite (final FieldPosition from) {
		return rotateRight(from, 4);
	}
	
	public static FieldPosition mirror (final FieldPosition from) {
		if(from.equals(FieldPosition.Center)) {
			return FieldPosition.Center;
		} else {
			final int rep = from.getPosition();
			return FieldPosition.fromInt((8 - rep) % 8);
		}		
	}
	
	public static FieldPosition randomCorner(Random r) {
		if(r == null) {
			r = new Random();
		}
		final int corner = r.nextInt(4) * 2;
		return FieldPosition.fromInt(corner);		
	}
	
	public static FieldPosition randomCorner() {
		return randomCorner(new Random());	
	}
	
	public static FieldPosition randomPosBetweenCorners(Random r) {
		return rotateRight(randomCorner(r), 1);		
	}
	
	public static FieldPosition randomPosBetweenCorners() {
		return randomPosBetweenCorners(new Random());	
	}
}
