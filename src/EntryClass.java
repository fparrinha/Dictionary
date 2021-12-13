
public class EntryClass<K,V> implements Entry<K, V> {
	
	protected K key;
	protected V value;
	
	public EntryClass(K key, V value) {
		this.key = key;
		this.value = value;
	}

	@Override
	public K getKey() {
		return key;
	}

	@Override
	public V getValue() {
		return value;
	}
	
	protected void setValue(V value) {
		this.value = value;
	}

}
