
/**
 * Iterator Specifications
 * 
 * @author Francisco Parrinha (58360) f.parrinha@campus.fct.unl.pt
 * @author Martin Magdalinchev (58172) m.magdalinchev@campus.fct.unl.pt
 * @version 1.0
 * @param <E> Generic Entry
 */
public interface Iterator<E> {

	/**
	 * Checks if there is a next ordered list that is not free
	 * 
	 * @return
	 */
	boolean hasNext();

	/**
	 * Returns the next non free ordered list
	 * 
	 * @return
	 */
	E next();

	/**
	 * Goes to the first non free ordered list
	 * 
	 * @return
	 */
	void rewind();
}
