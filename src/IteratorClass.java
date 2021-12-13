public class IteratorClass<K,V> implements Iterator<Entry<K,V>> {

	// Protected variables
	protected int i;
	protected Iterator<Entry<K,V>> it;
	protected Dictionary<K,V> []table;

	// Constructor

	@SuppressWarnings({ "rawtypes" })
	public IteratorClass(Dictionary<K,V> []table) {
		rewind();
		this.table = table;
	}

	@Override
	public boolean hasNext() {
		return it.hasNext();

	}

	@Override
	public Entry<K,V> next() {

		Entry<K,V> next = it != null ? it.next() : null;
		
		if(next != null)
			findList();
		
		return it.next();
	}

	@Override
	public Entry<K,V> rewind() {
		i = 0;
		it = null;

		return next();
	}
	
	private void findList() {
		while (i < table.length && !table[i].iterator().hasNext())
			i++;
		it = table[i].iterator();
	}

}
