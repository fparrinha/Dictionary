/**
 * Implements an entry
 * 
 * @author AED team
 * @version 1.0
 * 
 * @param <K> Generic type Key, must extend comparable
 * @param <V> Generic type Value
 */
public class EntryClass<K, V> implements Entry<K, V> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Protected variables
	protected K key;
	protected V value;

	// Constructor
	public EntryClass(K key, V value) {
		this.key = key;
		this.value = value;
	}

	@Override
	public K getKey() {
		return key;
	}

	@Override
	public V getValue() {
		return value;
	}

	/**
	 * Changes its value
	 * 
	 * @param value
	 */
	protected void setValue(V value) {
		this.value = value;
	}

}
