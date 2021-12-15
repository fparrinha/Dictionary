
/**
 * Separate Chaining Hash table implementation
 * 
 * @author AED Team
 * @version 1.0
 * @param <K> Generic Key, must extend comparable
 * @param <V> Generic Value
 */

public class SepChainHashTable<K extends Comparable<K>, V> extends HashTable<K, V> {
	/**
	 * Serial Version UID of the Class.
	 */
	static final long serialVersionUID = 0L;

	/**
	 * The array of dictionaries.
	 */
	protected Dictionary<K, V>[] table;

	/**
	 * Constructor of an empty separate chaining hash table, with the specified
	 * initial capacity. Each position of the array is initialized to a new ordered
	 * list maxSize is initialized to the capacity.
	 * 
	 * @param capacity defines the table capacity.
	 */
	@SuppressWarnings("unchecked")
	public SepChainHashTable(int capacity) {
		
		int arraySize = HashTable.nextPrime((int) (1.1 * capacity));
		table = (Dictionary<K, V>[]) new Dictionary[arraySize];
		
		for (int i = 0; i < arraySize; i++)
			table[i] = new OrderedDoubleList<K, V>();
		
		maxSize = capacity;
		currentSize = 0;
	}

	public SepChainHashTable() {
		this(DEFAULT_CAPACITY);
	}

	/**
	 * Returns the hash value of the specified key.
	 * 
	 * @param key to be encoded
	 * @return hash value of the specified key
	 */
	protected int hash(K key) {
		return Math.abs(key.hashCode()) % table.length;
	}

	@Override
	public V find(K key) {

		return table[this.hash(key)].find(key);
	}

	@Override
	public V insert(K key, V value) {
		if (this.isFull())
			this.rehash();

		V oldValue = this.find(key);
		table[this.hash(key)].insert(key, value);

		if (oldValue != null)
			return oldValue;
		else {
			currentSize++;
			return null;
		}
	}

	private void rehash() {
		int arraySize = HashTable.nextPrime((int) (1.1 * 2 * this.maxSize));
		@SuppressWarnings("unchecked")
		Dictionary<K, V>[] aux = new Dictionary[arraySize];
		for (int i = 0; i < arraySize; i++)
			aux[i] = new OrderedDoubleList<K, V>();
		
		Iterator<Entry<K, V>> it = this.iterator();
		while (it.hasNext()) {
			Entry<K, V> tmp = it.next();
			aux[hash(tmp.getKey())].insert(tmp.getKey(), tmp.getValue());
		}
		table = aux;

	}

	@Override
	public V remove(K key) {
		V value = this.find(key);
		if (value != null) {
			table[this.hash(key)].remove(key);
			currentSize--;
		}
		return value;
	}

	@Override
	public Iterator<Entry<K, V>> iterator() {
		return new IteratorClass<K, V>(table);
	}
}
