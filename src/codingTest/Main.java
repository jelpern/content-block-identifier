package codingTest;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Main {

	public static void main(String[] args) {
		// take input from command line
		if (args.length < 1) {
			System.out.println("Please enter a url to read from.");
		}
		String url = args[0];
		try {
			Document doc = Jsoup.connect(url).userAgent("Mozilla/5.0").get();
			// Counter is a wrapper around a hash table to mirror the Python Counter class
			// TODO replace Counter with a simple hashmap where the key is the selector string and the value is the tls
			Counter selectorCounter = new Counter();
			Elements allElements = doc.getAllElements(); 
			
			// count how many of each tag.class selector
			for (Element e: allElements) {
				Set<String> classes = e.classNames();
				// for loop necessary because some elements have more than one class
				boolean elementIsContent = TripleLiftSelector.isContent(e);
				for (String c: classes) {
					// conditional breakpoint hack - does nothing
					String selector = e.tagName();
					// check necessary because some elements have no class attribute
					if (!c.isEmpty()) {
						selector += "." + c;
					}
					if (selectorCounter.containsKey(selector)) {
						selectorCounter.get(selector).incrementCount();
					} else {	
						TripleLiftSelector tls = new TripleLiftSelector(selector, 1);
						selectorCounter.put(selector, tls);
					}
					TripleLiftSelector tls = selectorCounter.get(selector);
					if (elementIsContent) tls.incrementNumWithContent();
					else tls.incrementNumWithoutContent();
				}
				// some elements have multiple classes
				// count how many elements have the same combination of classes
				if (classes.size() > 1) {
// 					String selector = e.tagName() + "." + String.join(".",e.className().split("\\s+"));
					// Above is elegant, but not sure if it handles all types of whitespace in the className string. 
					// Better safe than sorry
					String selector = e.tagName();
					String classSeparator = ".";
					for (String c: classes){
						selector += classSeparator + c;
					}
					if (selectorCounter.containsKey(selector)) {
						selectorCounter.get(selector).incrementCount();
					} else {	
						TripleLiftSelector tls = new TripleLiftSelector(selector, 1);
						selectorCounter.put(selector, tls);
					}
					TripleLiftSelector tls = selectorCounter.get(selector);
					if (elementIsContent) tls.incrementNumWithContent();
					else tls.incrementNumWithoutContent();

				}
			} // done counting tag.class selectors
			
			// now that we're done counting, sort
			TreeSet<TripleLiftSelector> sortedClasses = new TreeSet<>(selectorCounter.values());
			NavigableSet<TripleLiftSelector> it = sortedClasses.descendingSet();
			System.out.println("tag.className,number of occurences,number that are content,number that are not content");
			for (TripleLiftSelector tls: it){
				System.out.println(tls.getSelector() + "," + tls.getCount() + "," + tls.getNumWithContent() + "," + tls.getNumWithoutContent());
			}
			System.out.println();
			//System.out.println(doc);
		}
		catch (IOException e) {
			System.out.println("Error while trying to open " + url);
			System.out.println("Please try again later.");
		}
	}

}
