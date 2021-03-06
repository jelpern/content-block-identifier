package codingTest;

import java.util.Collection;

import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.jsoup.select.Elements;

/**
 * 
 * @author jelpern
 *
 * @param selector - The count of the selector attribute. Usually a single class, but sometimes multiple classes 
 * @param count - How many times this selector appears in the DOM.
 * 
 * A convenience class for this assignment. 
 * 
 * Compares by occurrences first since this is the main characteristic of the object: 
 * how many times a given class or class string appears in the DOM.
 * 
 * OPEN: Should it extend org.jsoup.nodes.Element? This would require giving it a single tag
 * 
 */
public class TripleLiftSelector implements Comparable<TripleLiftSelector>{

	private String classString; 
	private String tag;
	private String selector;
	private Integer count = 0; // number of occurrences
	private Integer numWithContent = 0; // number of occurrences that contain content
	private Integer numWithoutContent = 0; // number of occurrences that don't contain content
	
	public static boolean isContent(Element e){
		boolean hasLinks = TripleLiftSelector.hasLinks(e);
		boolean hasImages = hasImages(e);
		boolean text = e.hasText();
		return (hasLinks && hasImages && (text));
	}

	public static boolean hasLinks (Element e) {
		Elements links = e.select("a[href]");
		return (links.size() > 0);
	}

	
	/**
	 * @param e
	 * @return
	 */
	public static boolean hasImages(Element e) {
		Elements images = e.select("img");
		Elements imagesDataBackgroundJPG = e.select("[data-background$=.jpg]");
		Elements imagesDataBackgroundGIF = e.select("[data-background$=.gif]");
		Elements imagesDataBackgroundPNG = e.select("[data-background$=.png]");
		Elements imagesBackgroundURL = e.select("[style^=background:url]");
		images.addAll(imagesDataBackgroundJPG);
		images.addAll(imagesDataBackgroundGIF);
		images.addAll(imagesDataBackgroundPNG);
		images.addAll(imagesBackgroundURL);
		if (images.size() > 0) {
			return true;
		} 
		// TODO find regex for all images
		return false;
	}
	
	public TripleLiftSelector(String selector) {
		this.selector = selector;
	}

	public TripleLiftSelector(String selector, Integer count) {
		this.selector = selector;
		this.count = count;
	}

	/**
	 * @return the className
	 * 
	 * will return to uncommented once I've change references to it in other classes 
	 * 
	 */
//	public String getClassString() {
//		return classString;
//	}
//
//	/**
//	 * @param className the className to set
//	 */
//	public void setClassString(String classString) {
//		this.classString = classString;
//	}

	/**
	 * @return the count
	 */
	public Integer getCount() {
		return count;
	}

	/**
	 * @param count the count to set
	 */
	public void setCount(Integer value) {
		this.count = value;
	}

	public void incrementCount() {
		this.count++;
	}
	
	/**
	 * @return the tag
	 */
	public String getTag() {
		return tag;
	}

	/**
	 * @param tag the tag to set
	 */
	public void setTag(String tag) {
		this.tag = tag;
	}

	/**
	 * @return the selector
	 */
	public String getSelector() {
		return selector;
	}

	/**
	 * @param selector the selector to set
	 */
	public void setSelector(String selector) {
		this.selector = selector;
	}

	/**
	 * @return the numWithContent
	 */
	public Integer getNumWithContent() {
		return numWithContent;
	}

	/**
	 * @param numWithContent the numWithContent to set
	 */
	public void setNumWithContent(Integer numWithContent) {
		this.numWithContent = numWithContent;
	}

	public void incrementNumWithContent() {
		this.numWithContent++;
	}
	
	/**
	 * @return the numWithoutContent
	 */
	public Integer getNumWithoutContent() {
		return numWithoutContent;
	}

	/**
	 * @param numWithoutContent the numWithoutContent to set
	 */
	public void setNumWithoutContent(Integer numWithoutContent) {
		this.numWithoutContent = numWithoutContent;
	}

	public void incrementNumWithoutContent() {
		this.numWithoutContent++;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 * (className, count)
	 */
	@Override
	public String toString() {
		return "(Selector: " + selector + ", Count: " + count + ")";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 * Two TripleLiftClassSelectorObjectds with the same selector must hash equally, otherwise merge won't work 
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((selector == null) ? 0 : selector.hashCode());
//		result = prime * result + ((count == null) ? 0 : count.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TripleLiftSelector other = (TripleLiftSelector) obj;
		if (selector == null) {
			if (other.selector != null)
				return false;
		} else if (!selector.equals(other.selector))
			return false;
// should counts have to be equal for the selectors to be the same? I don't think so.
//		if (count == null) {
//			if (other.count != null)
//				return false;
//		} else if (!count.equals(other.count))
//			return false;
		return true;
	}

	@Override
	public int compareTo(TripleLiftSelector o) {
		// compare values
		int c = ((Comparable<Integer>)this.numWithContent).compareTo(o.getNumWithContent());
		if (c !=0){
			return c;
		} else {
			c = ((Comparable<Integer>)this.count).compareTo(o.getCount());
			if (c !=0){
				return c;
			} else {
				return ((Comparable<String>)this.selector).compareTo(o.getSelector());
			}
		} 
	}
	
}
