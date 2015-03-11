package life.ruleset;

public interface RuleSet {
	BlockState getState(int[] neighborStates, int boardArray);
}
