package codingTest;

import java.io.IOException;

import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NavigableSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String url = "http://www.cutestpaw.com/";
		try {
			Document doc = Jsoup.connect(url).userAgent("Mozilla/5.0").get();
			Counter<String> classCounter = new Counter<String>();
			Elements allElements = doc.getAllElements(); 
			// count how many of each class
			for (Element e: allElements){
				Set<String> classes = e.classNames();
				for (String c: classes){
					if (c.contains("top")){
						System.out.println(e.tag());
						System.out.println("class=" + c + "\"");
					}
					classCounter.add(c);
				}
				String classString = e.className();
				if (classString.contains(" ")){
					classCounter.add(classString);
				}
			}
			Set<Map.Entry<String, Integer>> classes = classCounter.counts.entrySet(); // the set of entries in classCounter
			// create a data store that efficiently and automatically sorts
			TreeSet<KeyValuePair> sortedClasses = new TreeSet();
			// put everything in it
			for (Map.Entry<String, Integer> entry: classes){
				KeyValuePair kvp = new KeyValuePair<String, Integer>(entry.getKey(),entry.getValue());
				sortedClasses.add(kvp);
			}
			NavigableSet<KeyValuePair> it = sortedClasses.descendingSet();
			for (KeyValuePair kv: it){
				System.out.println(kv); 
			}
			// TODO pop items off the Iterator, check for link, image, text
			System.out.println(it);
			System.out.println();
			//System.out.println(doc);
		}
		catch (IOException e) {
			System.out.println("Error while trying to open " + url);
			System.out.println("Please try again later.");
		}
	}

}
