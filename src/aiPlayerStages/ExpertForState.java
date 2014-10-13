package aiPlayerStages;

public abstract class ExpertForState {
	
	public abstract boolean useKnowledge();
	
	public abstract ExpertForState getNextExpertForState();
}
