package util;

import java.util.Iterator;
import java.util.NoSuchElementException;

import model.IAction;

/**
 * Based on the Queue class in the book Introduction to Programming in Java by
 * Robert Sedgewick and Kevin Wayne.
 */
public class Queue<T extends IAction> implements Iterable<T> {

	private int size; // number of elements on queue
	private Node first; // beginning of queue
	private Node last; // end of queue

	// create an empty queue
	public Queue() {
		first = null;
		last = null;
	}

	// helper linked list class
	private class Node {
		private T t;
		private Node next;
	}

	public boolean empty() {
		return first == null;
	}

	public int size() {
		return size;
	}

	// add an item to the queue
	public void enqueue(T t) {
		Node x = new Node();
		x.t = t;
		if (empty()) {
			first = x;
			last = x;
		} else {
			last.next = x;
			last = x;
		}
		size++;
	}

	// remove and return the least recently added item
	public T dequeue() {
		if (empty())
			throw new RuntimeException("Queue underflow");
		T t = first.t;
		first = first.next;
		size--;
		return t;
	}

	public Iterator<T> iterator() {
		return new QueueIterator();
	}

	private class QueueIterator implements Iterator<T> {
		private Node current = first;

		public boolean hasNext() {
			return current != null;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}

		public T next() {
			if (!hasNext())
				throw new NoSuchElementException();
			Node previous = current;
			current = current.next;
			return previous.t;
		}
	}

}
