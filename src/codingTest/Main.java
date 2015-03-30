package codingTest;

import java.io.IOException;
import java.util.HashMap;
import java.util.NavigableSet;
import java.util.TreeSet;

public class Main {

	private static boolean debug = false;
	
	public static void main(String[] args) {
		// take input from command line
		if (args.length < 1) {
			System.out.println("Please enter a url to read from.");
			return;
		}
		String url = args[0];
		if (args.length > 1) {
			if (args[1].equals("debug")) {
				debug = true;
			}
		}
		try {
			// count the number of each element and whether they contain content
			Counter myCounter = new Counter();
			HashMap<String, TripleLiftSelector> selectorCounter = myCounter.countContentElements(url);
			
			// now that we're done counting, sort
			NavigableSet<TripleLiftSelector> it = new TreeSet<>(selectorCounter.values()).descendingSet();
			
			// return the one that is repeated the most
			TripleLiftSelector mostRepeated = it.first();
					
			if (mostRepeated.getNumWithContent() > 1){
				System.out.println("The most repeated, broadest element with content on the page at " + url + " can be found with CSS selector " + mostRepeated.getSelector() + ".");
				System.out.println("It occurs " + mostRepeated.getCount() + 
						" times, of which " + mostRepeated.getNumWithContent() + 
						" have content and " + mostRepeated.getNumWithoutContent() + " do not.");
			}
			
			// if in debug mode, print all the data
			if (debug) {
				System.out.println("tag.className,number of occurences,number that are content,number that are not content");
				for (TripleLiftSelector tls: it){
					System.out.println(tls.getSelector() + "," + tls.getCount() + "," + tls.getNumWithContent() + "," + tls.getNumWithoutContent());
				}
			}
		}
		catch (IOException e) {
			System.out.println("Error while trying to open " + url);
			System.out.println("Please try again later.");
		}
	}

}
