package aiPlayerStages;

public class EmptyExpert extends ExpertForState {

	@Override
	public boolean useKnowledge() {
		return false;
	}

	@Override
	public ExpertForState getNextExpertForState() {
		return this;
	}

}
