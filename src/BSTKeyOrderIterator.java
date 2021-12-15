public class BSTKeyOrderIterator<K, V> implements Iterator<Entry<K, V>> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private BSTNode<K, V> root;

	private DoubleList<BSTNode<K, V>> list;

	private Iterator<BSTNode<K, V>> it;

	public BSTKeyOrderIterator(BSTNode<K, V> root) {
		this.root = root;
		rewind();
	}

	@Override
	public boolean hasNext() {
		return it.hasNext();
	}

	@Override
	public Entry<K, V> next()  {
		return it.next().getEntry();
	}

	@Override
	public void rewind() {
		list = new DoubleList<BSTNode<K, V>>();
		inorder(root);
		it = new DoubleListIterator<BSTNode<K, V>>(list.head, list.tail);
	}

	protected void inorder(BSTNode<K, V> root) {
		if (root != null) {
			this.inorder(root.getLeft());
			list.addLast(root);
			this.inorder(root.getRight());
		}
	}
}
