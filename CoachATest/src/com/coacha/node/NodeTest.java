package com.coacha.node;

import org.junit.Test;

import junit.framework.TestCase;

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
		Node<String> node = new NodeImpl<String>("curr", null, null);
		assertNull(node.next());
		
		Node<String> next = new NodeImpl<String>("next");
		node = new NodeImpl<String>("curr", next, null);
		assertEquals(next, node.next());
	}
	
	@Test
	public void testPrevious() {
		Node<String> node = new NodeImpl<String>("curr", null, null);
		assertNull(node.previous());
		
		Node<String> prev = new NodeImpl<String>("prev");
		node = new NodeImpl<String>("curr", null, prev);
		assertEquals(prev, node.previous());
	}
	
	@Test
	public void testReverse() {
		// TODO
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

	// TODO
	@Test
	public void testAppend() {
		// Append a null %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
		{
			Node<String> node = new NodeImpl<String>("node");
			node.append(null);
		}

		// Base case
		{
			
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
		
		// Base case
		{
			
		}
	}

}
