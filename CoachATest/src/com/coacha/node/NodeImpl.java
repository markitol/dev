package com.coacha.node;

/**
 * Basic implementation of the {@link Node} interface.
 * 
 * @author Mark
 */
public class NodeImpl<T> implements Node<T> {
	
	private T value;
	
	private Node<T> next;
	
	private Node<T> previous;
	
	
	public NodeImpl(T value) {
		this.value = value;
	}

	/**
	 * Constructs a {@link Node} with the specified <tt>value<tt>, <tt>next</tt>,
	 * and <tt>previous</tt> parameters. Note that this constructor does not
	 * modify <tt>next</tt> and <tt>previous</tt> to set their appropriate pointers
	 * to this {@link Node}.
	 * 
	 * @param value - the value that will be stored in the {@link Node}.
	 * @param next - reference to the next {@link Node}.
	 * @param previous - reference to the previous {@link Node}.
	 */
	public NodeImpl(T value, Node<T> next, Node<T> previous) {
		this.value = value;
		this.next = next;
		this.previous = previous;
	}
	
	// Methods from the Node interface %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

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

	/**
	 * Default implementation of @link {@link Node#reverse()}. This method
	 * iterates in two directions: a) from the node to the start of the list;
	 * and b) from the node to the end of the list. In each iteration, the 'next'
	 * and 'previous' pointers are redirected to point to the new nodes.
	 * 
	 * The method runs in O(n) speed and requires O(n) space.
	 */
	@Override
	public void reverse() {
		// First, get references to the previous and next nodes
		Node<T> back = previous();
		Node<T> front = next();
		
		// Set the node's 'next' pointer to point to the previous node
		setNext(back);
		
		// Iterate towards the start of the list and redirect the pointers of each node
		// Complexity: O(i), where i is the index of the node in the list
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
		// Complexity: O(n - i) where i is the index of the node in the list and n is the size of the list
		curr = this;
		while (front != null) {
			Node<T> temp = front.next();
			front.setPrevious(temp);
			front.setNext(curr);
			
			curr = front;
			front = temp;
		}
		
	}

	/**
	 * Default implementation of @link {@link Node#delete()}.
	 * 
	 * The method runs in O(1) speed and requires O(1) space.
	 * 
	 * @return - the {@link Node} that replaced the deleted {@link Node}. May return null.
	 */
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

	/**
	 * Default implementation of {@link Node#append(Node)}.
	 * 
	 * The method runs in O(1) speed and requires O(1) space.
	 * 
	 * @param newNode - the new {@link Node} that was appended into the list.
	 */
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

	/**
	 * Default implementation of @link {@link Node#insertNext(Node)}.
	 * 
	 * The method runs in O(1) speed and requires O(1) space.
	 * 
	 * @param newNode - the new {@link Node} that will be inserted after this {@link Node}.
	 */
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
