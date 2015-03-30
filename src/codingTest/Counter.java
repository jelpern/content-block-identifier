package codingTest;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Counter {
	
	public Counter () {
		
	}
	
	public HashMap<String, TripleLiftSelector> countContentElements(String url) 
			throws IOException {
		Document doc = Jsoup.connect(url).userAgent("Mozilla/5.0").get();
		HashMap<String, TripleLiftSelector> selectorCounter = new HashMap<>();
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
		return selectorCounter;
	}

	
}
