package codingTest;

/**
 * 
 * @author jelpern
 *
 * @param classString - The count of the class attribute. Usually a single class, but sometimes multiple classes 
 * @param count - How many times this classSelector appears in the DOM.
 * 
 * A convenience class for this assignment. 
 * 
 * Compares by occurrences first since this is the main characteristic of the object: 
 * how many times a given class or class string appears in the DOM.
 * 
 * OPEN: Should it extend org.jsoup.nodes.Element? This would require giving it a single tag
 * 
 */
public class TripleLiftClassSelectorObject /*extends org.jsoup.nodes.Element*/ implements Comparable<TripleLiftClassSelectorObject>{

	private String classString; 
	private Integer count;
	
	public TripleLiftClassSelectorObject(String classString, Integer count) {
		this.classString = classString;
		this.count = count;
	}

	/**
	 * @return the className
	 */
	public String getClassString() {
		return classString;
	}

	/**
	 * @param className the className to set
	 */
	public void setClassString(String classString) {
		this.classString = classString;
	}

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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 * (className, count)
	 */
	@Override
	public String toString() {
		return "(" + classString + ", " + count + ")";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((classString == null) ? 0 : classString.hashCode());
		result = prime * result + ((count == null) ? 0 : count.hashCode());
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
		TripleLiftClassSelectorObject other = (TripleLiftClassSelectorObject) obj;
		if (classString == null) {
			if (other.classString != null)
				return false;
		} else if (!classString.equals(other.classString))
			return false;
		if (count == null) {
			if (other.count != null)
				return false;
		} else if (!count.equals(other.count))
			return false;
		return true;
	}

	@Override
	public int compareTo(TripleLiftClassSelectorObject o) {
		// compare values
		int c = ((Comparable<Integer>)this.count).compareTo(o.getCount());
		if (c !=0){
			return c;
		} else {
			return ((Comparable<String>)this.classString).compareTo(o.getClassString());
		}
	}
	
}
