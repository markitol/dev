package com.coacha.node;

/**
 * 
 * @author Mark
 * 
 * Basic implementation of the {@link Node} interface.
 *
 */
public class NodeImpl<T> implements Node<T> {
	
	private T value;
	
	private Node<T> next;
	
	private Node<T> previous;
	
	
	public NodeImpl(T value) {
		this.value = value;
	}

	public NodeImpl(T value, Node<T> next, Node<T> previous) {
		this.value = value;
		this.next = next;
		this.previous = previous;
	}
	
	// Methods from the Node superinterface %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

	@Override
	public T value() {
		return value;
	}

	@Override
	public Node<T> next() {
		return next;
	}
	
	@Override
	public void setNext(Node<T> next) {
		this.next = next;
	}

	@Override
	public Node<T> previous() {
		return previous;
	}
	
	@Override
	public void setPrevious(Node<T> previous) {
		this.previous = previous;
	}

	@Override
	public void reverse() {
		// First, get references to the previous and next nodes
		Node<T> back = previous();
		Node<T> front = next();
		
		// Set the node's 'next' pointer to point to the previous node
		setNext(back);
		
		// Iterate towards the start of the list and redirect the pointers of each node
		Node<T> curr = this;
		while (back != null) {
			Node<T> temp = back.previous();
			back.setPrevious(curr);
			back.setNext(temp);
			
			curr = back;
			back = temp;
		}
		
		// Set the node's 'previous' pointer to point to the next node
		setPrevious(front);
		
		// Iterate towards the end of the list and redirect the pointers of each node
		curr = this;
		while (front != null) {
			Node<T> temp = front.next();
			front.setPrevious(temp);
			front.setNext(curr);
			
			curr = front;
			front = temp;
		}
		
	}

	@Override
	public Node<T> delete() {
		Node<T> newNodeAtIndex = null;
		
		// Case: Node is the only element in the list
		if (next == null && previous == null) {
			newNodeAtIndex = null;
		}
		// Case: Node is the first in the list
		else if (next != null && previous == null) {
			next.setPrevious(null);
			newNodeAtIndex = next;
		}
		// Case: Node is the last in the list
		else if (next == null && previous != null) {
			previous.setNext(null);
			newNodeAtIndex = previous;
		}
		// Case: Node is in the middle of the list
		else {
			previous.setNext(next);
			next.setPrevious(previous);
			newNodeAtIndex = next;
		}
		
		// Clean up pointers from this node
		setNext(null);
		setPrevious(null);
		
		return newNodeAtIndex;
	}

	@Override
	public void append(Node<T> newNode) {
		if (newNode == null)
			return;
		
		Node<T> last = this;
		while (last.next() != null) {
			last = last.next();
		}
		
		last.setNext(newNode);
		newNode.setPrevious(last);
	}

	@Override
	public void insertNext(Node<T> newNode) {
		if (newNode == null)
			return;
		
		Node<T> next = next();
		next.setPrevious(newNode);
		newNode.setNext(next);
		
		setNext(newNode);
		newNode.setPrevious(this);
	}

}
