import exceptions.EmptyDictionaryException;

import java.io.Serializable;

/**
 * 
 * @author Francisco Parrinha (58360) f.parrinha@campus.fct.unl.pt
 * @author Martin Magdalinchev (58172) m.magdalinchev@campus.fct.unl.pt
 * 
 * @param <K>
 * @param <V>
 */
public class OrderedDoubleList<K extends Comparable<K>, V> implements OrderedDictionary<K,V> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Ordered Double List Node Implementation
	 * 
	 * @author Francisco Parrinha (58360) f.parrinha@campus.fct.unl.pt
	 * @author Martin Magdalinchev (58172) m.magdalinchev@campus.fct.unl.pt
	 * 
	 * @param <E> Generic Element
	 * 
	 */
	static class OrderedDLNode<E> implements Serializable {

		/**
		 * Serial Version UID of the Class
		 */
		static final long serialVersionUID = 0L;

		/**
		 * Element stored in the node.
		 */
		private E element;

		/**
		 * (Pointer to) the previous node.
		 */
		private OrderedDLNode<E> previous;

		/**
		 * (Pointer to) the next node.
		 */
		private OrderedDLNode<E> next;

		/**
		 * 
		 * @param theElement  - The element to be contained in the node
		 * @param thePrevious - the previous node
		 * @param theNext     - the next node
		 */
		public OrderedDLNode(E theElement, OrderedDLNode<E> thePrevious, OrderedDLNode<E> theNext) {
			element = theElement;
			previous = thePrevious;
			next = theNext;
		}

		/**
		 * 
		 * @param theElement to be contained in the node
		 */
		public OrderedDLNode(E theElement) {
			this(theElement, null, null);
		}

		/**
		 * 
		 * @return the element contained in the node
		 */
		public E getElement() {
			return element;
		}

		/**
		 * 
		 * @return the previous node
		 */
		public OrderedDLNode<E> getPrevious() {
			return previous;
		}

		/**
		 * 
		 * @return the next node
		 */
		public OrderedDLNode<E> getNext() {
			return next;
		}

		/**
		 * 
		 * @param newElement - New element to replace the current element
		 */
		public void setElement(E newElement) {
			element = newElement;
		}

		/**
		 * 
		 * @param newPrevious - node to replace the current previous node
		 */
		public void setPrevious(OrderedDLNode<E> newPrevious) {
			previous = newPrevious;
		}

		/**
		 * 
		 * @param newNext - node to replace the next node
		 */
		public void setNext(OrderedDLNode<E> newNext) {
			next = newNext;
		}

	}

	// Protected Variables
	protected OrderedDLNode<Entry<K, V>> head;
	protected OrderedDLNode<Entry<K, V>> tail;
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
		OrderedDLNode<Entry<K,V>> temp = this.getNode(key);
		return temp != null ? temp.getElement().getValue() : null;
	}

	@Override
	public V insert(K key, V value) {
		// Seraches for a node with the same key. If found, changes its value and returns the old value
		OrderedDLNode<Entry<K,V>> temp = this.getNode(key);
		if(temp != null) {
			V oldValue = temp.getElement().getValue();
			((EntryClass<K, V>) temp.getElement()).setValue(value);
			return oldValue;
		}
		
		// If the specified key was not found, insert new node
		if(head.getElement().getKey().compareTo(key) > 0)
			insertFirst(key, value);
		else if(tail.getElement().getKey().compareTo(key) < 0)
			insertLast(key, value);
		else
			insertMiddle(key, value);
		return null;
	}

	@Override
	public V remove(K key) {
		if(key.com)
		return null;
	}

	@Override
	public Iterator<Entry<K, V>> iterator() {
		return null;
	}

	@Override
	public Entry<K, V> minEntry() throws EmptyDictionaryException {
		return head.getElement();
	}

	@Override
	public Entry<K, V> maxEntry() throws EmptyDictionaryException {
		return tail.getElement();
	}
	
	/*
	 * Gets node
	 */
	private OrderedDLNode<Entry<K,V>> getNode(K key){
		int i = 0;
		OrderedDLNode<Entry<K,V>> temp = head;
		
		while(i < currentSize) {
			if(temp.element.getKey().equals(key))
				break;
			temp = temp.getNext();
			i ++;
		}
		
		return temp;
	}
	
	/**
	 * Removes the first node
	 */
	private void removeFirstNode() {
		
	}
	
	/**
	 * Removes the last node
	 */
	private void removeLastNode() {
		
	}
	
	/**
	 * Removes the middle node
	 */
	private void removeMiddleNode() {
		
	}
	
	/**
	 * 
	 */
	private void insertFirst(K key, V value) {
		Entry<K,V> entry = new EntryClass<K,V>(key,value);
		OrderedDLNode<Entry<K,V>> node = new OrderedDLNode<Entry<K,V>>(entry); 
		
		node.setNext(head);
		node.setPrevious(null);
		head.setPrevious(node);
		currentSize ++;
	}
	
	private void insertMiddle(K key, V value) {
		int i = 0;
		Entry<K,V> entry = new EntryClass<K,V>(key,value);
		OrderedDLNode<Entry<K,V>> temp = head;
		OrderedDLNode<Entry<K,V>> node = new OrderedDLNode<Entry<K,V>>(entry);
		
		while (i < currentSize) {
			if(temp.getElement().getKey().compareTo(key) > 0) {
				OrderedDLNode<Entry<K,V>> aux = temp.getPrevious();
				aux.next = node;
				node.next = temp;
				node.previous = aux;
				temp.previous = node;
				currentSize++;
				break;
			}
			temp = temp.getNext();
			i++;	
		}
	}
	
	private void insertLast(K key, V value) {
		Entry<K,V> entry = new EntryClass<K,V>(key,value);
		OrderedDLNode<Entry<K,V>> node = new OrderedDLNode<Entry<K,V>>(entry); 
		
		node.setNext(null);
		node.setPrevious(tail);
		tail.setNext(node);
		currentSize ++;
	}

}
