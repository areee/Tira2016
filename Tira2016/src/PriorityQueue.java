/*
 * Class PriorityQueue describes an implementation of a priority queue. 
 */

public class PriorityQueue {
	private static final Object Object = null;
	/*
	 * An empty priority queue.
	 */
	public PriorityQueue() {
		_size = 0;
		_first = _last = null;
	}
	/*
	 * Returns the number of elements in the priority queue.
	 */
	public long size() {
		return _size;
	}
	/*
	 * Returns logical true if the priority queue is empty.
	 */
	public boolean isEmpty() {
		return _size == 0;
	}
	/*
	 * Insert a new key/element pair into the priority queue
	 * according to the principle of insertion sort. Hence,
	 * the nodes of the priority queue are in ascending order
	 * according to the key values.
	 */
	public void insertElement(long key, Object element) {
		
		PQNode node = new PQNode(key, element, null, null);
		
		if (isEmpty()){
			_first = _last = node;
			_size = 1;
			return;
		}
		else if (size() == 1){
			if (key >= _first.getKey()){
				_size ++;
				_first.setNext(node);
				node.setPrev(_first);
				_last = node;
				return;
			}else{
				_size ++;
				_last.setPrev(node);
				node.setNext(_last);
				_first = node;
				return;
			}
		}
		else if (key <= _first.getKey()){
			_size ++;
			node.setNext(_first);
			_first.setPrev(node);
			_first = node;
			return;
		}
		else if(key >= _last.getKey()){
			_size ++;
			node.setPrev(_last);
			_last.setNext(node);
			_last = node;
			return;
		}	
		else{
			PQNode curr = _first;
			PQNode next = curr.getNext();
			while (curr.getNext() != null){
				System.out.println("curr: " + curr.getKey());
				System.out.println("key: " + key);
				System.out.println("next: " + curr.getNext().getKey());
				
				if (key >= curr.getKey() && key <= curr.getNext().getKey()){
					_size ++;
					curr.setNext(node);
					node.setNext(next);
					node.setPrev(curr);
				 	next.setPrev(node);
					break;
				}
				curr = curr.getNext();
				next = curr.getNext();
			}
			if(curr.getNext() == null){
				curr.setNext(node);
				node.setPrev(curr);
				_last = node;
				_size ++;
				return;
			}			
		}
		
	}
	private PQNode PQNode(long key, java.lang.Object element) {
		// TODO Auto-generated method stub
		return null;
	}
	/*
	 * Returns the element with the smallest key, but does not
	 * remove the element from the priority queue.
	 */
	public Object minElement() throws IndexOutOfBoundsException {
		if (isEmpty()) {
			throw new IndexOutOfBoundsException();
		} else {
			return _first.getElement();
		}
	}
	/*
	 * Returns the smallest key.
	 */
	public long minKey() throws IndexOutOfBoundsException {
		if (isEmpty()) {
			throw new IndexOutOfBoundsException();
		} else {
			return _first.getKey();
		}
	}
	/*
	 * Removes and returns the element with the smallest
	 * key.
	 */
	public Object removeMin() {
		Object returnValue;
		
		if(isEmpty()){
			return null;
		}
		else if(size() == 1){
			returnValue = _first.getElement();
			_first = _last = null;
			_size--;
			return returnValue;
		}else{
			returnValue = _first.getElement();
			PQNode next = _first.getNext();
			next.setPrev(null);
			_first.setNext(null);
			_first = next;
			_size --;
			return returnValue;
		}
		
	}
	/*
	 * Returns the element at rank k (k in [1,size]).
	 */
	public Object elementAtRank(long k) throws IndexOutOfBoundsException {
		if (k < 1 || _size < k) {
			throw new IndexOutOfBoundsException();
		} else {
			if (k <= _size/2) {
				PQNode iter = _first;
				for (long i = 1; i < k; i++) {
					iter = iter.getNext();
				}
				return iter.getElement();
			} else {
				PQNode iter = _last;
				for (long i = 1; i < _size - k + 1; i++) {
					iter = iter.getPrev();
				}
				return iter.getElement();
			}
		}
	}
	/*
	 * For testing. Returns true if isEmpty() == true or _first and _last point to the same node.
	 */
	boolean OneAndTheSame() {
		return (_first == null && _last == null) || _first.equals(_last);
	}
	private long _size;
	private PQNode _first, _last;
}
