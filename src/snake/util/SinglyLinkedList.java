package snake.util;

/**
 * @author Braddy Yeoh - 17357376
 * 
 * This singly linked list does not contain a tail as Aonghus said there is no need for one when there is a doubly linked list.
 * This will change the complexity of certain methods like addLast and removeLast. It is now O(N) as opposed to O(1) if we had a tail.
 */

import java.util.Iterator;

public class SinglyLinkedList<E> implements Iterable<E>, List<E> {
	/**
	 * Node of a singly linked list, which stores a reference to its element and to
	 * the subsequent node in the list (or null if this is the last node).
	 */
	private static class Node<E> {

		/** The element stored at this node */
		private E element;

		/** A reference to the subsequent node in the list */
		private Node<E> next;

		/**
		 * Creates a node with the given element and next node.
		 *
		 * @param e: the element to be stored
		 * @param n: reference to a node that should follow the new node
		 */
		public Node(E e, Node<E> n) {
			this.element = e;
			this.next = n;
		}

		/**
		 * Returns the element stored at the node.
		 * 
		 * @return the element stored at the node
		 */
		public E getElement() {
			return this.element;
		}

		/**
		 * Sets element e to current element of Node
		 * 
		 * @param e
		 */
		public void setElement(E e) {
			this.element = e;
		}
		
		/**
		 * Returns the node that follows this one (or null if no such node).
		 * 
		 * @return the following node
		 */
		public Node<E> getNext() {
			return this.next;
		}

		/**
		 * Sets the node's next reference to point to Node n.
		 * 
		 * @param n the node that should follow this one
		 */
		public void setNext(Node<E> n) {
			this.next = n;
		}
		
		/**
		 * @return String representation of the element in the Node
		 */
		@Override
		public String toString() {
			return this.element.toString();
		}
	}

	/**
	 * Instance variables of the SinglyLinkedList
	 */
	
	/** The head node of the list */
	private Node<E> head = null;

	/** Number of nodes in the list */
	private int size = 0;

	/** Constructs an initially empty list. */
	public SinglyLinkedList() {
	}

	/**
	 * Access methods
	 */
	
	/**
	 * Returns the number of elements in the linked list.
	 * 
	 * @return number of elements in the linked list
	 */
	public int size() {
		return this.size;
	}
	
	public void setSize(int size) {
		this.size = size;
	}

	/**
	 * Tests whether the linked list is empty.
	 * 
	 * @return true if the linked list is empty, false otherwise
	 */
	public boolean isEmpty() {
		return this.size == 0;
	}
	
	/**
	 * @return new ListIterator Object
	 */
	public Iterator<E> iterator() {
		return new ListIterator<E>();
	}

	/**
	 * Returns (but does not remove) the first element of the list
	 * 
	 * @return element at the front of the list (or null if empty)
	 */
	public E first() { // returns (but does not remove) the first element
		if (isEmpty()) {
			return null;
		}

		return getNode(0).getElement();
	}

	/**
	 * Returns (but does not remove) the last element of the list.
	 * 
	 * @return element at the end of the list (or null if empty)
	 */
	public E last() { // returns (but does not remove) the last element
		if (isEmpty()) {
			return null;
		}

		return getNode(size-1).getElement();
	}

	/**
	 * Update methods
	 */
	
	/**
	 * Adds an element to the front of the list.
	 * 
	 * @param e the new element to add
	 */
	public void addFirst(E e) {
		this.head = new Node<>(e, head); // Create a new node with element e, and it points to head
		size++; // Increase size of nodes
	}

	/**
	 * Adds an element to the end of the list.
	 * 
	 * @param e the new element to add
	 */
	public void addLast(E e) {
		if (isEmpty()) { // If list is empty
			Node<E> newTail = new Node<>(e, null); // create new node and point next to null
			head = newTail; // newTail becomes head
			size++; // Increase size of list
		}
		
		else { // Else not empty
			Node<E> newTail = getNode(size-1); // Iterate to last node and insert node after it
			linkAfter(newTail, e);
		}
	}

	/**
	 * Insert node before a specified element
	 * 
	 * @param key
	 * @param e
	 */
	public void insertBefore(E key, E e) {
		Node<E> prev = null;
		Node<E> curr = head;

		if (head.getElement().equals(key)) {
			addFirst(e);
			return;
		}

		while (curr != null && !curr.getElement().equals(key)) {
			prev = curr;
			curr = curr.getNext();
		}

		if (curr != null) {
			prev.setNext(new Node<>(e, curr));
		}

		size++;
	}

	/**
	 * Insert node after specified element
	 * 
	 * @param key
	 * @param e
	 */
	public void insertAfter(E key, E e) {
		Node<E> tmp = head;

		while (tmp != null && !tmp.getElement().equals(key)) {
			tmp = tmp.getNext();
		}

		if (tmp != null) {
			tmp.setNext(new Node<>(e, tmp.getNext()));
		}

		size++;
	}

	/**
	 * Removes and returns the first element of the list.
	 * 
	 * @return the removed element (or null if empty)
	 */
	public E removeFirst() {
		if (isEmpty()) {
			return null;
		}
		
		return remove(0);
	}

	/**
	 * Removes and returns the last element of the list
	 * 
	 * @return the removed element (or null if empty)
	 */
	public E removeLast() {
		if (isEmpty()) {
			return null;
		}
		
		return remove(size-1);
	}

	/**
	 * @return element at index i
	 */
	@Override
	public E get(int index) {
		return getNode(index).getElement();
	}

	/**
	 * Set new element to Node of given index
	 * 
	 * @param index to find node
	 * @param e element to set to node found
	 */
	@Override
	public void set(int index, E e) {
		if (isEmpty()) {
			return;
		}
		
		Node<E> node = getNode(index);
		node.setElement(e);
	}
	
	/**
	 * Add new Node by iterating through list given index
	 * 
	 * @param index to add new Node
	 * @param e element of new Node
	 */
	@Override
	public void add(int index, E e) {
		if (index == size) {
			addLast(e);
		} 
		
		else if (index == 0) {
			addFirst(e);
		} 
		
		else {
			linkAfter(getNode(index - 1), e);
		}
	}

	/**
	 * Removes node by iterating through list given index
	 * 
	 * @param index of node to remove
	 * @return e element of Node removed
	 */
	@Override
	public E remove(int index) {
		
		if (index == 0) {
			if (head.getNext() == null) {
				E element = head.getElement();
				head = null;
				size--;
				return element;
			}
			
			else {
				E element = head.getElement(); // Assign tmp the element of head
				head = head.getNext(); // Head is the next head
				size--;
				
				return element;
			}
		}
		
		else {
			Node<E> prev = getNode(index - 1);
			Node<E> curr = prev.getNext();
			E element = curr.getElement();
			prev.setNext(curr.getNext()); // skip to the next node, curr is no longer in the list
			size--;
			return element;
		}
	}

	/**
	 * Copies elements of current list to new list
	 * 
	 * @return new a copy of list
	 */
	public SinglyLinkedList<E> copy() {
		SinglyLinkedList<E> newList = new SinglyLinkedList<E>(); // Creating a new singly linked list

		for (int i=0; i<size(); i++) {
			newList.add(i, get(i));
		}

		return newList; // Return new singly linked list
	}

	/**
	 * Insert after given Node
	 * 
	 * @param node
	 * @param e
	 */
	private void linkAfter(Node<E> node, E e) {
		Node<E> newNode = new Node<E>(e, node.getNext());
		node.setNext(newNode);
		size++;
	}

	/**
	 * Iterate through list to find given Node
	 * 
	 * @param index
	 * @return Node at given index
	 */
	private Node<E> getNode(int index) {
		Node<E> curr = head;
		for (int i=0; i<index; i++) {
			curr = curr.next;
		}
		
		return curr;
	}

	private class ListIterator<T> implements Iterator<T> {

		@SuppressWarnings("unchecked")
		Node<T> curr = (Node<T>) head;

		/**
		 * @return boolean value of whether end of list
		 */
		@Override
		public boolean hasNext() {
			return curr != null;
		}

		/**
		 * @return generic T element of node
		 */
		@Override
		public T next() {
			T value = curr.getElement();
			curr = curr.getNext();
			return value;
		}
	}

	/**
	 * Reverse list
	 */
	public void reverse() {
		Node<E> prev = null;
		Node<E> curr = head;
		Node<E> next = curr.getNext();

		while (curr != null) { // Curr is not null
			next = curr.getNext(); // Next is the Node after curr
			curr.setNext(prev); // Curr next node is the current Node at prev
			prev = curr; // Switch Nodes
			curr = next; // Switch Nodes
		}
		
		head = prev; // The head is now end of the list, i.e reversed
	}

	/**
	 * Print out list
	 * 
	 * @return String representation of list
	 */
	public String toString() {
		String output = new String();

		output += "Size: " + size + "\n";
		for (E element : this) {
			output += "[" + element + "] ";
		}

		return output;
	}
	
	/**
	 * Test functions
	 */
	public static void test() {
		SinglyLinkedList<Integer> list = new SinglyLinkedList<Integer>();
		
		list.addLast(1);
		list.addLast(2);
		list.addLast(3);
		list.addLast(4);
		list.addLast(5);
		//list.addFirst(0);
		System.out.println("Size " + list.get(list.size()-1));
		System.out.println(list.head);
		
		System.out.println(list.first());
		System.out.println(list.last());
		System.out.println(list.removeFirst());
	
		list.removeLast();
		list.removeLast();
		
		System.out.println(list);
	}
	
	/**
	 * Test reverse function
	 */
	public static void testReverse() {
		SinglyLinkedList<Integer> list = new SinglyLinkedList<Integer>();
		list.addLast(1);
		list.addLast(2);
		list.addLast(3);
		list.addLast(4);
		list.addLast(5);
		list.addLast(6);
		list.remove(2);
		list.add(2, 10);
		System.out.println("\nBefore reverse" + "\n" + list);
		
		list.reverse();
		System.out.println("After reverse" + "\n" + list);
	}
	
	public static void testAddFirst() {
		SinglyLinkedList<Integer> list = new SinglyLinkedList<Integer>();
		list.addFirst(1);
		list.addFirst(2);
		list.addFirst(3);
		list.addFirst(4);
		list.addFirst(5);
		list.addFirst(6);
		
		System.out.println(list);
	}
	
	public static void main(String[] args) {
		//test();
		//testReverse();
		testAddFirst();
	}
}