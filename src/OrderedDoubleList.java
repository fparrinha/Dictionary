import exceptions.*;

/**
 * Ordered Double List Implementation
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
		return this.findNode(key).getElement().getValue();
	}

	@Override
	public V insert(K key, V value) {
		// Next and previous nodes initialization
		DoubleListNode<Entry<K, V>> node = findNode(key);

		// Checks if there is an entry with the same key, if so, replace its value
		if (node != null) {
			V oldValue = node.getElement().getValue();
			((EntryClass<K, V>) node.getElement()).setValue(value);
			return oldValue;
		}

		// Adds a new node with an entry with a different key
		if (this.isEmpty()) {
			insertFirst(key, value);
		} else {
			if (key.compareTo(head.getElement().getKey()) < 0)
				insertFirst(key, value);
			else if (key.compareTo(tail.getElement().getKey()) > 0)
				insertLast(key, value);
			else
				insertMiddle(key, value);
		}
		return null;
	}

	@Override
	public V remove(K key) {
		DoubleListNode<Entry<K, V>> node = findNode(key);

		if (node == null)
			return null;

		if (node == head)
			removeFirst();
		else if (node == tail)
			removeLast();
		else
			removeMiddle(node);
		return node.getElement().getValue();
	}

	@Override
	public Iterator<Entry<K, V>> iterator() {
		return new DoubleListIterator<Entry<K, V>>(head, tail);
	}

	@Override
	public Entry<K, V> minEntry() throws EmptyDictionaryException {
		if (isEmpty())
			throw new EmptyDictionaryException();
		return head.getElement();
	}

	@Override
	public Entry<K, V> maxEntry() throws EmptyDictionaryException {
		if (isEmpty())
			throw new EmptyDictionaryException();
		return tail.getElement();
	}

	/**
	 * Finds a node with the given key
	 * 
	 * @param key
	 * @return
	 */
	private DoubleListNode<Entry<K, V>> findNode(K key) {
		DoubleListNode<Entry<K, V>> current = head;

		while (current != null) {
			if (current.getElement().getKey().compareTo(key) == 0)
				return current;
			current = current.getNext();
		}
		return null;
	}

	/**
	 * Finds a position on the list with the given key
	 * 
	 * @param key
	 * @return
	 */
	private DoubleListNode<Entry<K, V>> findPosition(K key) {
		DoubleListNode<Entry<K, V>> current = head;

		while (current != null && key.compareTo(current.getElement().getKey()) > 0) {
			current = current.getNext();
		}
		return current;
	}

	/**
	 * Adds a new node in the first position of the list
	 * 
	 * @param key
	 * @param value
	 */
	private void insertFirst(K key, V value) {
		Entry<K, V> entry = new EntryClass<K, V>(key, value);
		DoubleListNode<Entry<K, V>> newNode = new DoubleListNode<Entry<K, V>>(entry);

		if (this.isEmpty())
			tail = newNode;
		else
			head.setPrevious(newNode);
		head = newNode;
		currentSize++;
	}

	/**
	 * Adds a new node in the last position of the list
	 * 
	 * @param key
	 * @param value
	 */
	private void insertLast(K key, V value) {
		Entry<K, V> entry = new EntryClass<K, V>(key, value);
		DoubleListNode<Entry<K, V>> newNode = new DoubleListNode<Entry<K, V>>(entry);

		if (this.isEmpty())
			head = newNode;
		else
			tail.setNext(newNode);
		tail = newNode;
		currentSize++;
	}

	/**
	 * Adds a new node in the middle of the list
	 * 
	 * @param key
	 * @param value
	 */
	private void insertMiddle(K key, V value) {
		Entry<K, V> entry = new EntryClass<K, V>(key, value);
		DoubleListNode<Entry<K, V>> nextNode = findPosition(key);
		DoubleListNode<Entry<K, V>> prevNode = nextNode.getPrevious();
		DoubleListNode<Entry<K, V>> newNode = new DoubleListNode<Entry<K, V>>(entry, prevNode, nextNode);

		prevNode.setNext(newNode);
		nextNode.setPrevious(newNode);

		currentSize++;
	}

	/**
	 * Removes the first node
	 * 
	 * @param key
	 * @param value
	 */
	private void removeFirst() {
		head = head.getNext();

		if (head == null)
			tail = null;
		else
			head.setPrevious(null);
		currentSize--;
	}

	/**
	 * Removes the last node
	 * 
	 * @param key
	 * @param value
	 */
	private void removeLast() {
		tail = tail.getPrevious();

		if (tail == null)
			head = null;
		else
			tail.setNext(null);
		currentSize--;
	}

	/**
	 * Removes one node from the middle
	 * 
	 * @param key
	 * @param value
	 */
	private void removeMiddle(DoubleListNode<Entry<K, V>> node) {
		DoubleListNode<Entry<K, V>> prevNode = node.getPrevious();
		DoubleListNode<Entry<K, V>> nextNode = node.getNext();

		prevNode.setNext(nextNode);
		nextNode.setPrevious(prevNode);

		currentSize--;
	}
}
