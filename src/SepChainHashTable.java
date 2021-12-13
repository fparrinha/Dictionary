
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
		// Compiler gives a warning.
		table = (Dictionary<K, V>[]) new Dictionary[arraySize];
		for (int i = 0; i < arraySize; i++)
			// TODO: Original comentado para nao dar erro de compilacao.
			table[i] = new OrderedDoubleList<K, V>();
		// table[i] = null;
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
		else
			return null;
	}

	private void rehash() {
		// TODO Auto-generated method stub

	}

	@Override
	public V remove(K key) {
		V value = this.find(key);
		if(value != null) 
			table[this.hash(key)].remove(key);
		
			
		return value;
	}

	@Override
	public Iterator<Entry<K, V>> iterator() {
		// TODO: Left as an exercise.
		return null;
	}
}