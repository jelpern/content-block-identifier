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
		// TODO take input from command line
		String url = "http://www.rantchic.com/";
		try {
			Document doc = Jsoup.connect(url).userAgent("Mozilla/5.0").get();
			// Counter is a wrapper around a hash table to mirror the Python Counter class 
			Counter selectorCounter = new Counter();
			Elements allElements = doc.getAllElements(); 
			
			// count how many of each tag.class selector
			for (Element e: allElements) {
				Set<String> classes = e.classNames();
				// for loop necessary because some elements have more than one class
				for (String c: classes) {
					// conditional breakpoint hack - does nothing
					String selector = e.tagName();
					// check necessary because some elements have no class attribute
					if (!c.isEmpty()) {
						selector += "." + c;
					}
					TripleLiftClassSelectorObject tlcso = new TripleLiftClassSelectorObject(selector); 
					selectorCounter.add(tlcso);
				}
				// some elements have multiple classes
				// count how many elements have the same combination of classes
				if (classes.size() > 1) {
// 					String selector = e.tagName() + "." + String.join(".",e.className().split("\\s+"));
					// above is elegant, but not sure if it handles all types of whitespace in the className string, 
					// so better safe than sorry
					String selector = e.tagName();
					String classSeparator = ".";
					for (String c: classes){
						selector += classSeparator + c;
					}
					TripleLiftClassSelectorObject newTlcso = new TripleLiftClassSelectorObject(selector);  
					TripleLiftClassSelectorObject returnedTlcso = selectorCounter.add(newTlcso);
					boolean match = returnedTlcso.equals(returnedTlcso);
					if (!match){
						System.out.println (returnedTlcso + " does not match " + newTlcso);
					}
				}
			} // done counting tag.class selectors
			
			// take all the items out of the counter
			Set<Map.Entry<TripleLiftClassSelectorObject, Integer>> classes = selectorCounter.items();
			// create a data store that efficiently and automatically sorts
			TreeSet<TripleLiftClassSelectorObject> sortedClasses = new TreeSet<TripleLiftClassSelectorObject>();
			// transform each entry in the counter into a TripleLiftClassSelectorObject that sorts on *value*,
			// and put everything in the sorted data store
			for (Entry<TripleLiftClassSelectorObject, Integer> entry: classes){
				entry.getKey().setCount(entry.getValue());
//				TripleLiftClassSelectorObject tlcso = new TripleLiftClassSelectorObject(entry.getKey().getSelector(),entry.getValue());
				sortedClasses.add(entry.getKey());
			}
			NavigableSet<TripleLiftClassSelectorObject> it = sortedClasses.descendingSet();
			System.out.println("tag.className,number of occurences,number that are content");
			for (TripleLiftClassSelectorObject tlcso: it){
				String selector = tlcso.getSelector();
				if (selector.isEmpty() || selector == null) { // conditional break hack
					Integer i = 4;
					i++;
				} 
				Elements htmlClass = doc.select(selector);
				int countContent = 0;
				// TODO check if all elements of the same class are also of the same tag
				for (Element e: htmlClass){
					countContent = ContentElement.isContent(e) ? countContent + 1 : countContent;
				}
				System.out.println(selector + "," + tlcso.getCount() + "," + countContent);
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
