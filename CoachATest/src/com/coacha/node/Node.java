package com.coacha.node;

/**
 * 
 * @author Mark
 *
 * @param <T>
 */
public interface Node<T> {

	T value();
	
	Node<T> next();
	
	void setNext(Node<T> next);
	
	Node<T> previous();
	
	void setPrevious(Node<T> previous);
	
	void reverse();
	
	Node<T> delete();
	
	void append(Node<T> newNode);
	
	void insertNext(Node<T> newNode);
	
}
