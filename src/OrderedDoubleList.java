import exceptions.EmptyDictionaryException;

public class OrderedDoubleList<K, V> implements OrderedDictionary {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected Entry<K, V> minEntry;

	protected Entry<K, V> maxEntry;

	protected int currentSize;

	public OrderedDoubleList() {
		minEntry = null;
		maxEntry = null;
		currentSize = 0;
	}

	@Override
	public boolean isEmpty() {
		return currentSize == 0;
	}

	@Override
	public int size() {
		return currentSize;
	}

	@Override
	public Object find(Object key) {
		for (int i = 0; i < this.size(); i++) {

		}
		return null;
	}

	@Override
	public Entry<K,V> insert(Object key, Object value) {
		return null;
	}

	@Override
	public Entry<K,V> remove(Object key) {
		return null;
	}

	@Override
	public Iterator<Entry<K,V>> iterator() {
		return null;
	}

	@Override
	public Entry<K,V> minEntry() throws EmptyDictionaryException {
		return this.minEntry();
	}

	@Override
	public Entry<K,V> maxEntry() throws EmptyDictionaryException {
		return this.maxEntry();
	}

}
