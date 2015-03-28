package codingTest;

import java.io.IOException;

import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NavigableSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class Main {

	public static void main(String[] args) {
		// TODO take input from command line
		String url = "http://www.cutestpaw.com/";
		try {
			Document doc = Jsoup.connect(url).userAgent("Mozilla/5.0").get();
			// Counter is a wrapper around a hash table to mirror the Python Counter class 
			Counter<String> classCounter = new Counter<String>();
			Elements allElements = doc.getAllElements(); 
			
			// count how many of each class
			for (Element e: allElements){
				Set<String> classes = e.classNames();
				// necessary because some elements have more than one class
				// note that classCounter is not currently implemented with a sorted class
				// this is because the order can change with every insertion
				// it would be easy to change this however in the implementation
				for (String c: classes){
					// items which don't have a class are represented by empty strings
					// we don't want to count them
					if (!c.isEmpty()) {
						classCounter.add(c);
					}
				}
				String classString = e.className();
				if (classString.contains(" ")){
					classCounter.add(classString);
				}
			}
			
			// take all the items out of the counter
			Set<Map.Entry<String, Integer>> classes = classCounter.items();
			// create a data store that efficiently and automatically sorts
			TreeSet<KeyValuePair> sortedClasses = new TreeSet();
			// transform each entry the counter into a key value pair that sorts on *value*,
			// and put everything in the sorted data store
			for (Map.Entry<String, Integer> entry: classes){
				KeyValuePair kvp = new KeyValuePair<String, Integer>(entry.getKey(),entry.getValue());
				sortedClasses.add(kvp);
			}
			NavigableSet<KeyValuePair> it = sortedClasses.descendingSet();
			for (KeyValuePair kv: it){

			}
			// TODO pop items off the Iterator, check for link, image, text
			System.out.println();
			//System.out.println(doc);
		}
		catch (IOException e) {
			System.out.println("Error while trying to open " + url);
			System.out.println("Please try again later.");
		}
	}

}
