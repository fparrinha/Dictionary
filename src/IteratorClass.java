public class IteratorClass<E> implements Iterator<E> {

	// Protected variables
	protected int i;
	protected Iterator<E> it;

	// Constructor
	public IteratorClass() {
		rewind();
	}

	@Override
	public boolean hasNext() {
		return i < ;
	}

	@Override
	public E next() {
		

		return ;
	}

	@Override
	public E rewind() {
		i = 0;
		it = null;

		return next();
	}

}
