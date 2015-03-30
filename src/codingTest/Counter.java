package codingTest;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Counter extends HashMap<String, TripleLiftSelector> {
	
	final HashMap<TripleLiftSelector, Integer> counts = new HashMap<>();

	public TripleLiftSelector add(TripleLiftSelector t) {
		int c = counts.merge(t, 1, Integer::sum);
		t.setCount(counts.get(t));
		if (c != t.getCount()) { // conditional breakpoint hack 
			System.out.println("Element count went wrong on item: " + t.getSelector());
		} 
		return t;
		// do we need to return t?
	}

	public int count(TripleLiftSelector t) {
		return counts.getOrDefault(t, 0);
	}
	
	public Set<Map.Entry<TripleLiftSelector, Integer>> items() {
		return counts.entrySet();
	}
	
}
