package playingField;

import java.util.HashMap;
import java.util.Map;

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
	
	private static final Map<Integer, FieldPosition> intToTypeMap = new HashMap<Integer, FieldPosition>();
	static {
	    for (FieldPosition type : FieldPosition.values()) {
	        intToTypeMap.put(type.pos, type);
	    }
	}

	public static FieldPosition fromInt(int i) {
		FieldPosition type = intToTypeMap.get(Integer.valueOf(i));
	    if (type == null) 
	        return FieldPosition.Empty;
	    return type;
	}
	
	
}
