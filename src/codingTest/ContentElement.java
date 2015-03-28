package codingTest;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ContentElement {
	
	public static boolean isContent(Element e){
		Elements links = e.select("a[href]");
		Elements images = e.select("img");
		boolean text = e.hasText();
		return (links.size()>0 && images.size()>0 && text);
	}
	


}
