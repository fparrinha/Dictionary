public class IteratorClass<E> implements Iterator<E> {

	// Protected variables
	protected int i;
	protected Iterator<E> it;
	protected int maxSize;
	@SuppressWarnings("rawtypes")
	protected SepChainHashTable table;

	// Constructor

	@SuppressWarnings({ "rawtypes" })
	public IteratorClass(SepChainHashTable table) {
		rewind();
		maxSize = table.maxSize;
		this.table = table;
	}

	@Override
	public boolean hasNext() {
		return it.hasNext();

	}

	@SuppressWarnings("unchecked")
	@Override
	public E next() {
		while (i < maxSize && !table.table[i].iterator().hasNext())
			i++;
		it = table.table[i].iterator();
		return it.next();
	}

	@Override
	public E rewind() {
		i = 0;
		it = null;

		return next();
	}

}
