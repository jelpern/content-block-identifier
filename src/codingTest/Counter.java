package codingTest;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Counter<String> {
	
	final HashMap<String, Integer> counts = new HashMap<>();

	public void add(String t) {
		counts.merge(t, 1, Integer::sum);
	}

	public int count(String t) {
		return counts.getOrDefault(t, 0);
	}
	
	public Set<Map.Entry<String, Integer>> items() {
		return counts.entrySet();
	}
	
}
