package codingTest;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Counter {
	
	final HashMap<TripleLiftElement, Integer> counts = new HashMap<>();

	public TripleLiftElement add(TripleLiftElement t) {
		int c = counts.merge(t, 1, Integer::sum);
		t.setCount(counts.get(t));
		if (c != t.getCount()) { // conditional breakpoint hack 
			System.out.println("Element count went wrong on item: " + t.getSelector());
		} 
		return t;
		// do we need to return t?
	}

	public int count(TripleLiftElement t) {
		return counts.getOrDefault(t, 0);
	}
	
	public Set<Map.Entry<TripleLiftElement, Integer>> items() {
		return counts.entrySet();
	}
	
}
