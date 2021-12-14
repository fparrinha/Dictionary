public class IteratorClass<K, V> implements Iterator<Entry<K, V>> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Protected variables
	protected int i;
	protected Iterator<Entry<K, V>> it;
	protected Dictionary<K, V>[] table;

	// Constructor
	public IteratorClass(Dictionary<K, V>[] table) {
		this.table = table;
		rewind();
	}

	@Override
	public boolean hasNext() {
		return (it.hasNext() || hasOther());

	}

	@Override
	public Entry<K, V> next() {
		if (!it.hasNext()) {
				i++;
			findList();}
			return it.next();

		

	}

	@Override
	public void rewind() {
		i = 0;
		findList();
	}

	private void findList() {
		while (i < table.length-1 && table[i].isEmpty())
			i++;
		it = table[i].iterator();
	}

	private boolean hasOther() {
		boolean bool = false;
		for (int j = i+1; j < table.length && !bool; j++)
			if (!table[j].isEmpty())
				bool = true;
		return bool;
	}
}
