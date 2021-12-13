public class IteratorClass<K,V> implements Iterator<Entry<K,V>> {

	// Protected variables
	protected int i;
	protected Iterator<Entry<K,V>> it;
	protected Dictionary<K,V> []table;

	// Constructor

	public IteratorClass(Dictionary<K,V> []table) {
		this.table = table;
		rewind();
	}

	@Override
	public boolean hasNext() {
		return it.hasNext();

	}

	@Override
	public Entry<K,V> next() {

		Entry<K,V> next = it != null ? it.next() : null;
		
		if(next != null)
			i++;
			findList();
		
		return it.next();
	}

	@Override
	public void rewind() {
		i=0;
		findList();
	}
	
	private void findList() {
		while (i < table.length && table[i].isEmpty())
			i++;
		it = table[i].iterator();
	}

}
