package codingTest;

/**
 * 
 * @author jelpern
 *
 * @param <K>
 * @param <V>
 * 
 * A convenience class for this assignment. Compares by value since value is the 
 * count of how many times a given class or class string appears in the DOM.
 * 
 */
public class TripleLiftElement <K,V> implements Comparable<TripleLiftElement<K,V>>{

	private K key;
	private V value;
	
	public TripleLiftElement(K key, V value) {
		this.key = key;
		this.value = value;
	}

	/**
	 * @return the key
	 */
	public K getKey() {
		return key;
	}

	/**
	 * @param key the key to set
	 */
	public void setKey(K key) {
		this.key = key;
	}

	/**
	 * @return the value
	 */
	public V getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(V value) {
		this.value = value;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 * (key, value)
	 */
	@Override
	public String toString() {
		return "(" + key + ", " + value + ")";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
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
		TripleLiftElement other = (TripleLiftElement) obj;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	@Override
	public int compareTo(TripleLiftElement<K, V> o) {
		// compare values
		int c = ((Comparable<V>)this.value).compareTo(o.getValue());
		if (c !=0){
			return c;
		} else {
			return ((Comparable<K>)this.key).compareTo(o.getKey());
		}
	}
	
}
