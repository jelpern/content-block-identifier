package codingTest;

import java.util.HashMap;

public class Counter<String> {
	
	final HashMap<String, Integer> counts = new HashMap<>();

	public void add(String t) {
		counts.merge(t, 1, Integer::sum);
	}

	public int count(String t) {
		return counts.getOrDefault(t, 0);
	}
	
}
