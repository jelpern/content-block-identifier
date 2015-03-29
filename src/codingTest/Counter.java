package codingTest;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Counter {
	
	final HashMap<TripleLiftClassSelectorObject, Integer> counts = new HashMap<>();

	public TripleLiftClassSelectorObject add(TripleLiftClassSelectorObject t) {
		counts.merge(t, 1, Integer::sum);
		t.setCount(counts.get(t));
		if (true) {} // conditional breakpoint hack
		return t;
		// do we need to return t?
	}

	public int count(TripleLiftClassSelectorObject t) {
		return counts.getOrDefault(t, 0);
	}
	
	public Set<Map.Entry<TripleLiftClassSelectorObject, Integer>> items() {
		return counts.entrySet();
	}
	
}
