package aiPlayerStages;

import java.awt.Point;
import java.util.NavigableSet;
import java.util.Optional;
import java.util.Random;
import java.util.TreeSet;

import playingField.FieldPosition;
import playingField.Positions;

public class Decisioner {
	
	private NavigableSet<PossibleDecision> possibilities;
	private int totalWeight;
	private Random r;
	
	public Decisioner() {
		possibilities = new TreeSet<PossibleDecision>((arg1, arg2) -> arg1.weight - arg2.weight);
		totalWeight = 0;
		r = new Random();
	}
	
	private class PossibleDecision {
		
		final Point pos;
		final int weight;
		
		PossibleDecision(Point pos, int weight) {
			this.pos = pos;
			this.weight = weight;
		}
	}
	
	
	public void putPossibility(Point pos, int weight) {
		if(weight > 0) {
			totalWeight = totalWeight + weight;
			possibilities.add(new PossibleDecision(pos, totalWeight));
		}
	}

	public void putPossibility(FieldPosition pos, int weight) {
		putPossibility(Positions.fromTypeToPoint(pos), weight);
	}
	
	/**
	 * Chose a random position from weighted possibilities
	 * @return random (weighted) decision
	 */
	public Optional<Point> decide() {
		if(totalWeight <= 0) {
			return Optional.empty();
		}		
		
		final int decide = r.nextInt(totalWeight);
		final PossibleDecision res = possibilities.higher(new PossibleDecision(null, decide));
		
		possibilities.clear();
		totalWeight = 0;
		
		return Optional.ofNullable(res.pos);
	}
	
	public int getTotalWeight() {
		return totalWeight;
	}
	
	public boolean areTherePossibilities() {
		return possibilities.isEmpty() == false;
	}
}
