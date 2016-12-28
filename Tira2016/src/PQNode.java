/*
 * Class PQNode describes an implementation of a priority queue node.
 */

public class PQNode {
	public PQNode(long k, Object e, PQNode n, PQNode p) {
		key = k;
		element = e;
		next = n;
		prev = p;
	}
	public long getKey() {
		return key;
	}
	public Object getElement() {
		return element;
	}
	public PQNode getNext() {
		return next;
	}
	public PQNode getPrev() {
		return prev;
	}
	public void setKey(long k) {
		key = k;
	}
	public void setElement(Object e) {
		element = e;
	}
	public void setNext(PQNode n) {
		next = n;
	}
	public void setPrev(PQNode p) {
		prev = p;
	}
	private long key;
	private Object element;
	private PQNode next, prev;
}
