package de.mann.tictactoeframework.aiPlayerStages;

import de.mann.tictactoeframework.playingField.FieldPosition;

import java.util.NavigableSet;
import java.util.Random;
import java.util.TreeSet;

public class Decisioner {
	
	private final NavigableSet<PossibleDecision> possibilities;
	private int totalWeight;
	private final Random r;
	
	public Decisioner(Random r) {
		possibilities = new TreeSet<>((lhs, rhs) -> lhs.weight - rhs.weight);
		totalWeight = 0;
		this.r = r;
	}
	
	private class PossibleDecision {
		
		final FieldPosition pos;
		final int weight;
		
		PossibleDecision(FieldPosition pos, int weight) {
			this.pos = pos;
			this.weight = weight;
		}
	}

	public Random getRandom() {
        return r;
    }
	
	void putPossibility(FieldPosition pos, int weight) {
		if(weight > 0) {
			totalWeight = totalWeight + weight;
			possibilities.add(new PossibleDecision(pos, totalWeight));
		}
	}

	void putPossibilityForEachCorner(int weight) {
		this.putPossibility(FieldPosition.TopLeft, weight);
		this.putPossibility(FieldPosition.TopRight, weight);
		this.putPossibility(FieldPosition.BottomLeft, weight);
		this.putPossibility(FieldPosition.BottomRight, weight);
	}

	void putPossibilityForEachEdge(int weight) {
		this.putPossibility(FieldPosition.Left, weight);
		this.putPossibility(FieldPosition.Top, weight);
		this.putPossibility(FieldPosition.Bottom, weight);
		this.putPossibility(FieldPosition.Right, weight);
	}
	
	/**
	 * Chose a random position from weighted possibilities
	 * @return random (weighted) decision
	 */
	public FieldPosition decide() {
		if(totalWeight <= 0) {
			return FieldPosition.Empty;
		}

		if(possibilities.size() == 1) {
			return possibilities.first().pos;
		}
		
		final int decide = r.nextInt(totalWeight);
		final PossibleDecision res = possibilities.higher(new PossibleDecision(null, decide));
		
		possibilities.clear();
		totalWeight = 0;
		
		return res.pos;
	}
	
	public int getTotalWeight() {
		return totalWeight;
	}
	
	public boolean areTherePossibilities() {
		return !possibilities.isEmpty();
	}
}
