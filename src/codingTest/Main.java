package codingTest;

import java.io.IOException;
import java.util.HashMap;
import java.util.NavigableSet;
import java.util.TreeSet;

public class Main {

	public static void main(String[] args) {
		// take input from command line
		if (args.length < 1) {
			System.out.println("Please enter a url to read from.");
		}
		String url = args[0];
		try {
			// count the number of each element and whether they contain content
			Counter myCounter = new Counter();
			HashMap<String, TripleLiftSelector> selectorCounter = myCounter.countContentElements(url);
			
			// now that we're done counting, sort
			NavigableSet<TripleLiftSelector> it = new TreeSet<>(selectorCounter.values()).descendingSet();
			
			// 
			System.out.println("tag.className,number of occurences,number that are content,number that are not content");
			for (TripleLiftSelector tls: it){
				System.out.println(tls.getSelector() + "," + tls.getCount() + "," + tls.getNumWithContent() + "," + tls.getNumWithoutContent());
			}
			System.out.println();
		}
		catch (IOException e) {
			System.out.println("Error while trying to open " + url);
			System.out.println("Please try again later.");
		}
	}

}
