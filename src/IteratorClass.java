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
		return it.hasNext();
	}

	@Override
	public Entry<K, V> next() {
		Entry<K, V> temp = it.next();
		int pos;
		if (!it.hasNext() && (pos = findNextList()) != -1) {
			i++;
			it = table[pos].iterator();
		}
		return temp;

	}

	@Override
	public void rewind() {
		i = 0;
		int pos = !table[0].isEmpty() ? 0 : findNextList();
		it = table[pos].iterator();
	}
	
	/*
	private void findList() {
		while (i < table.length - 1 && table[i].isEmpty())
			i++;
		it = table[i].iterator();
	}

	private boolean hasOther() {
		boolean bool = false;
		for (int j = i + 1; j < table.length && !bool; j++)
			if (!table[j].isEmpty())
				bool = true;
		return bool;
	}*/
	
	private int findNextList() {
		int pos = -1;
		int j = i+1;
		while(j < table.length && pos == -1) {
			if(!table[j].isEmpty())
				pos = j;
			j++;
		}
		i = j-1;
		return pos;
	}
}
