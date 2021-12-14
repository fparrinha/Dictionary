import exceptions.*;


/**
 * 
 * @author Francisco Parrinha (58360) f.parrinha@campus.fct.unl.pt
 * @author Martin Magdalinchev (58172) m.magdalinchev@campus.fct.unl.pt
 * 
 * @param <K>
 * @param <V>
 */
public class OrderedDoubleList<K extends Comparable<K>, V> implements OrderedDictionary<K, V> {

	private static final long serialVersionUID = 1L;

	// Protected Variables
	protected DoubleListNode<Entry<K, V>> head;
	protected DoubleListNode<Entry<K, V>> tail;
	protected int currentSize;

	// Ordered Double List Constructor
	public OrderedDoubleList() {
		head = null;
		tail = null;
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
	public V find(K key) {
		return this.findNode(key).getPrevious().getElement().getValue();
	}

	@Override
	public V insert(K key, V value) {
		// Next and previous nodes initialization
		DoubleListNode<Entry<K, V>> prevNode = findNode(key);			// VAI FALHAR
		DoubleListNode<Entry<K, V>> nextNode = prevNode.getNext();

		// Checks if there is an entry with the same key, if so, replace its value
		if (prevNode != null && prevNode.getElement().getKey().compareTo(key) == 0) {
			V oldValue = prevNode.getElement().getValue();
			((EntryClass<K, V>) nextNode.getElement()).setValue(value);
			return oldValue;
		}

		// Adds a new node with an entry with a different key
		Entry<K, V> entry = new EntryClass<K, V>(key, value);
		DoubleListNode<Entry<K, V>> newNode = new DoubleListNode<Entry<K, V>>(entry);

		if (prevNode != null)
			prevNode.setNext(newNode);
		newNode.setNext(newNode);
		newNode.setPrevious(prevNode);
		if (nextNode != null)
			nextNode.setPrevious(newNode);

		return null;

	}

	@Override
	public V remove(K key) {
		DoubleListNode<Entry<K, V>> node = findNode(key);
		return null;
	}

	@Override
	public Iterator<Entry<K, V>> iterator() {
		return new DoubleListIterator<Entry<K, V>>(head, tail);
	}

	@Override
	public Entry<K, V> minEntry() throws EmptyDictionaryException {
		return head.getElement();
	}

	@Override
	public Entry<K, V> maxEntry() throws EmptyDictionaryException {
		return tail.getElement();
	}

	/**
	 * Gets the first node with a key bigger than the search key
	 */
	private DoubleListNode<Entry<K, V>> findNode(K key) {
		DoubleListNode<Entry<K, V>> current = head;
		while (current != null && key.compareTo(current.getElement().getKey()) > 0)
			current = current.getNext();
		return current;
	}
}
