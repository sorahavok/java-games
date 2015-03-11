package life.ruleset;

import java.util.Map;

public class StandardRules implements RuleSet {

	private final Map<Integer, BlockState> ruleMap;
	
	public StandardRules(final Map<Integer, BlockState> rulesMap) {
		this.ruleMap = rulesMap;
	}
	
	@Override
	public BlockState getState(int[] neighborStates, int myState) {
		int sum = 0;
		for(int i : neighborStates) {
			sum += i;
		}
		// Look for a "live value" in the map if it is there return it otherwise 0
		return ruleMap.getOrDefault(sum, BlockState.DIE);
	}

}
