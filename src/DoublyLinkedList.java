/**
 * 
 */

/**
 * @author Alex Kelly
 * @version 9/16/2015
 * 
 */
public class DoublyLinkedList {
	DoubleNode head;
	DoubleNode tail;
	
	/**
	 * Constructs a new DoublyLinkedList with head and tail as null
	 * Theta(1)
	 */
	public DoublyLinkedList() {
		head = tail = null;
	}
	
	/**
	 * returns true if the list is empty, false otherwise
	 * @return true if the list is empty
	 * Theta(1)
	 */
	public boolean isEmpty() {
		return head==null;
	}
	
	/**
	 * adds a node containing the character c to the end of the list
	 * @param c - a single character
	 * Theta(1)
	 */
	public void addCharAtEnd(char c) {
		if (this.isEmpty()) {
			head = tail = new DoubleNode(null, c, null);
		}
		else {
			tail.setNext(new DoubleNode(tail, c, null));
			tail = tail.getNext();
		}
	}
	
	/**
	 * adds a node containing the character c to the front of the list
	 * @param c - a single character
	 * Theta(1)
	 */
	public void addCharAtFront(char c) {
		if (this.isEmpty()) {
			head = tail = new DoubleNode(null, c, null);
		}
		else {
			head.setPrev(new DoubleNode(null, c, head));
			head = head.getPrev();
		}
	}
	
	/**
	 * removes and returns the character at the front of the list
	 * @return - the character at the front of the list
	 * precondition: list is not empty. If list is empty, returns null char
	 * Theta(1)
	 */
	public char removeCharFromFront() {
		if (this.isEmpty()) return '\0';
		
		char c = head.getC();
		head = head.getNext();
		
		if (head != null) head.setPrev(null);
		
		return c;
	}
	
	/**
	 * removes and returns the character at the end of the list
	 * @return - the character at the end of the list
	 * precondition: list is not empty. If list is empty, returns null char
	 * Theta(1)
	 */
	public char removeCharAtEnd() {
		if (this.isEmpty()) return '\0';
		
		char c = tail.getC();
		tail = tail.getPrev();
		
		if (tail != null) tail.setNext(null);
		else head = null;
		return c;
	}
	
	/**
	 * counts the number of nodes in the list
	 * @return - number of nodes in the list between head and tail inclusive
	 * Theta(n)
	 */
	public int countNodes() {
		int counter = 0;
		DoubleNode pointer = head;
		while (pointer != null) {
			pointer = pointer.getNext();
			counter++;
		}
		return counter;
	}
	
	/**
	 * deletes the first occurance of the character c from the list, unless it does not exist
	 * @param c - the character to be deleted
	 * @return true if a character was deleted, false if the character was not in the list
	 * Theta(n)
	 */
	public boolean deleteChar(char c) {
		DoubleNode pointer = head;
		while (pointer != null) {
			if (pointer.getC() == c) {
				if (pointer != head) pointer.getPrev().setNext(pointer.getNext());
				else head = head.getNext();
				if (pointer != tail) pointer.getNext().setPrev(pointer.getPrev());
				else tail = tail.getPrev();
				return true;
			}
			pointer = pointer.getNext();
		}
		return false;
	}
	
	/**
	 * reverses the order of the list
	 * Theta(n)
	 */
	public void reverse() {
		DoubleNode pointer1 = head;
		head = tail;
		tail = pointer1;
		DoubleNode pointer2 = head;
		while (pointer2 != null) {
			pointer1 = pointer2.getNext();
			pointer2.setNext(pointer2.getPrev());
			pointer2.setPrev(pointer1);
			pointer2 = pointer2.getNext();
		}
	}
	
	/**
	 * returns the list as a String by calling the toString method for DoubleNode 
	 * @return a String containing the characters in the list, separated by spaces
	 * Theta(n)
	 */
	public String toString() {
		if (this.isEmpty()) return "/NULL/";
		
		StringBuilder s = new StringBuilder();
		DoubleNode pointer = head;
		while (pointer != null) {
			s.append(pointer.toString());
			pointer = pointer.getNext();
		}
		return s.toString();
	}
	
	/**
	 * test driver for DoublyLinkedList
	 * @param a
	 */
	public static void main(String a[]) {
		
		DoublyLinkedList list = new DoublyLinkedList();
		list.addCharAtEnd('H');
		list.addCharAtEnd('e');
		list.addCharAtEnd('l');
		list.addCharAtEnd('l');
		list.addCharAtEnd('o');
		System.out.println(list);
		System.out.println("Deleting l");
		list.deleteChar('l');
		System.out.println(list);
		System.out.println("Deleting H");
		list.deleteChar('H');
		System.out.println(list);
		System.out.println("Deleting o");
		list.deleteChar('o');
		System.out.println(list);
		System.out.println("Deleting e");
		list.deleteChar('e');
		System.out.println(list);
		System.out.println("Deleting l");
		list.deleteChar('l');
		System.out.println(list);
		list.addCharAtFront('o');
		list.addCharAtFront('l');
		list.addCharAtFront('l');
		list.addCharAtFront('e');
		list.addCharAtFront('H');
		System.out.println(list);
		
		System.out.println(list.countNodes());
		
		System.out.println("Popping everything");
		while(!list.isEmpty()){
			System.out.println(list.removeCharFromFront());
		}
		
		list.addCharAtFront('o');
		list.addCharAtFront('l');
		list.addCharAtFront('l');
		list.addCharAtFront('e');
		list.addCharAtFront('H');
		
		System.out.println("Popping everything from the end");
		while(!list.isEmpty()){
			System.out.println(list.removeCharAtEnd());
		}
		System.out.println(list.countNodes());
		
		list.addCharAtEnd('o');
		list.addCharAtEnd('l');
		list.addCharAtEnd('l');
		list.addCharAtEnd('e');
		list.addCharAtEnd('H');
	
		list.reverse();
		System.out.println(list);
		
		list.reverse();
		System.out.println(list);
		
	}

}
