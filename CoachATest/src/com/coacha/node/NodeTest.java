package com.coacha.node;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

/**
 * JUnit test case for {@link NodeImpl}.
 * 
 * @author Mark
 *
 */
public class NodeTest extends TestCase {
	
	@Test
	public void testValue() {
		Node<Integer> iNode = new NodeImpl<Integer>(100);
		assertEquals(100, iNode.value().intValue());
		
		Node<String> sNode = new NodeImpl<String>("Hello");
		assertEquals("Hello", sNode.value());
	}
	
	@Test
	public void testNext() {
		Node<String> node = new NodeImpl<String>("node", null, null);
		assertNull(node.next());
		
		Node<String> next = new NodeImpl<String>("next");
		node = new NodeImpl<String>("node", next, null);
		assertEquals(next, node.next());
	}
	
	@Test
	public void testPrevious() {
		Node<String> node = new NodeImpl<String>("node");
		assertNull(node.previous());
		
		Node<String> prev = new NodeImpl<String>("prev");
		node = new NodeImpl<String>("node", null, prev);
		assertEquals(prev, node.previous());
	}
	
	@Test
	public void testReverse() {
		// Reverse a single-element list %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
		{
			Node<String> node = new NodeImpl<String>("node");
			
			node.reverse();
			
			// Check the node's pointers
			assertNull(node.next());
			assertNull(node.previous());
		}
		
		// General case %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
		{
			LinkedListSetup<Integer> setup = createLinkedListSetup(10);
			
			System.out.println("\nBefore reversing:");
			printList(setup.first);
			
			System.out.println("After reversing at Node " + setup.node.value() + ":");
			setup.node.reverse();
			
			printList(setup.last);
			
			// Do the check
			int i = setup.size - 1;
			Node<Integer> iter = setup.last;
			while (iter != null) {
				assertEquals(setup.originalList.get(i), iter);
				iter = iter.next();
				i--;
			}
 		}
	}
	
	@Test
	public void testDelete() {
		// Delete the only element %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
		{
			Node<String> node = new NodeImpl<String>("node");
			
			Node<String> result = node.delete();
			
			// Check the result
			assertNull(result);
			
			// Check that the node's pointers are clean
			assertNull(node.next());
			assertNull(node.previous());
		}
		
		// Delete the starting element %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
		{
			Node<String> node = new NodeImpl<String>("node");
			
			Node<String> next = new NodeImpl<String>("next", null, node);
			node.setNext(next);
			
			Node<String> result = node.delete();
			
			// Check the next node's pointer
			assertNull(next.previous());
			
			// Check the return value
			assertEquals(next, result);
			
			// Check that the node's pointers are clean
			assertNull(node.next());
			assertNull(node.previous());
		}

		// Delete the last element %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
		{
			Node<String> node = new NodeImpl<String>("node");
			
			Node<String> prev = new NodeImpl<String>("prev", node, null);
			node.setPrevious(prev);
			
			Node<String> result = node.delete();
			
			// Check the previous node's pointer
			assertNull(prev.next());
			
			// Check the return value
			assertEquals(prev, result);
			
			// Check that the node's pointers are clean
			assertNull(node.next());
			assertNull(node.previous());
		}
		
		// Delete a middle element %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
		{
			Node<String> node = new NodeImpl<String>("node");
			
			Node<String> next = new NodeImpl<String>("next", null, node);
			node.setNext(next);
			
			Node<String> prev = new NodeImpl<String>("prev", node, null);
			node.setPrevious(prev);
			
			Node<String> result = node.delete();
			
			// Check the next node's pointer
			assertEquals(prev, next.previous());
			
			// Check the previous node's pointer
			assertEquals(next, prev.next());
			
			// Check the return value
			assertEquals(next, result);
			
			// Check that the node's pointers are clean
			assertNull(node.next());
			assertNull(node.previous());
		}
	}

	@Test
	public void testAppend() {
		// Append a null %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
		{
			Node<String> node = new NodeImpl<String>("node");
			
			Node<String> next = new NodeImpl<String>("next", null, node);
			node.setNext(next);
			
			Node<String> prev = new NodeImpl<String>("prev", node, null);
			node.setPrevious(prev);
			
			node.append(null);
		}

		// Append to a single-element list %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
		{
			Node<String> node = new NodeImpl<String>("node");
			
			Node<String> newNode = new NodeImpl<String>("newNode");
			
			node.append(newNode);
			
			// Check node's pointers
			assertEquals(newNode, node.next());
			assertEquals(null, node.previous());
			
			// Check newNode's pointers
			assertNull(newNode.next());
			assertEquals(node, newNode.previous());
		}
		
		// Append to a node in the middle of the list %%%%%%%%%%%%%%%%%%%%%%%%%%
		{	
			int n = 10;
			LinkedListSetup<Integer> setup = createLinkedListSetup(n);
			
			System.out.println("\nBefore appending:");
			printList(setup.first);
			
			Node<Integer> newNode = new NodeImpl<Integer>(n + 1);
			setup.node.append(newNode);
			
			System.out.println("After appending " + newNode.value() + " to " + setup.node.value() + ":");
			printList(setup.first);
		}
	}
	
	@Test
	public void testInsertNext() {
		// Insert a null %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
		{
			Node<String> node = new NodeImpl<String>("node");
			
			Node<String> next = new NodeImpl<String>("next", null, node);
			node.setNext(next);
			
			Node<String> prev = new NodeImpl<String>("prev", node, null);
			node.setPrevious(prev);
			
			node.insertNext(null);

			// Check that the pointers are not modified
			assertEquals(next, node.next());
			assertEquals(prev, node.previous());
		}
		
		// Insert a non-null node into the last node %%%%%%%%%%%%%%%%%%%%%%%%%%%
		{
			Node<String> node = new NodeImpl<String>("node");
			
			Node<String> newNode = new NodeImpl<String>("newNode");
			
			node.insertNext(newNode);
			
			// Check node's next pointer
			assertEquals(newNode, node.next());
			
			// Check newNode's pointers
			assertEquals(node, newNode.previous());
			assertNull(newNode.next());
		}
		
		// Insert a non-null node into the middle of the list %%%%%%%%%%%%%%%%%%
		{
			Node<String> node = new NodeImpl<String>("node");
			
			Node<String> next = new NodeImpl<String>("next", null, node);
			node.setNext(next);
			
			Node<String> prev = new NodeImpl<String>("prev", node, null);
			node.setPrevious(prev);
			
			Node<String> newNode = new NodeImpl<String>("newNode");
			
			node.insertNext(newNode);

			// Check node's pointers
			assertEquals(newNode, node.next());
			
			// Check the previous pointer of node's old 'next node'
			assertEquals(newNode, next.previous());
			
			// Check newNode's pointers
			assertEquals(next, newNode.next());
			assertEquals(node, newNode.previous());
		}
	}
	
	// Helper Methods %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	
	/**
	 * Returns a string representation of the list of {@link Node}s.
	 * 
	 * @param start
	 * @return
	 */
	static <T> String listAsString(Node<T> start) {
		StringBuilder sb = new StringBuilder();
		if (start != null) {
			Node<T> iter = start;
			sb.append(iter.value());
			while ((iter = iter.next()) != null) {
				sb.append(" " + iter.value());
			}
		}
		
		return sb.toString();
	}
	
	/**
	 * Prints the list of {@link Node}s starting from <tt>start</tt>.
	 * 
	 * @param start
	 */
	static <T> void printList(Node<T> start) {
		System.out.println(listAsString(start));
	}
	
	/**
	 * Utility method used to return a {@link LinkedListSetup} with Integers.
	 * 
	 * @param size - the size of the list
	 * @return
	 */
	static LinkedListSetup<Integer> createLinkedListSetup(int size) {
		Node<Integer> first = new NodeImpl<Integer>(0);
		Node<Integer> last = first;
		
		// Keep a list with the original ordering for double checking later
		List<Node<Integer>> original = new ArrayList<Node<Integer>>(size);
		original.add(first);
		
		// Pick a random index. We will use the node at this index as 'pivot'.
		int index = (int) (Math.random() * size);
		Node<Integer> node = index == 0 ? first : null;
		
		// Build the list
		for (int i = 1; i < size; i++) {
			Node<Integer> newNode = new NodeImpl<Integer>(i);
			last.append(newNode);
			
			// Set the 'pivot' node
			if (i == index && node == null)
				node = newNode;
			
			last = newNode;
			
			// Add to the original list
			original.add(newNode);
		}
		
		LinkedListSetup<Integer> setup = new LinkedListSetup<Integer>();
		setup.first = first;
		setup.last = last;
		setup.size = size;
		setup.node = node;
		setup.originalList = original;
		
		return setup;
	}
	
	// Inner Classes %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	
	/**
	 * Convenience class for representing a setup containing 
	 * @author Mark
	 *
	 * @param <T>
	 */
	static class LinkedListSetup<T> {
		Node<T> first; // The first node in the list.
		Node<T> last; // The last node in the list.
		int size; // The number of nodes in the list
		List<Node<T>> originalList; // Backing list used to hold the nodes in their original order
		Node<T> node; // Random node in the list. Used to perform the operations.
	}

}
