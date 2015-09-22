/**
 * 
 */
import java.math.*;
/**
 * @author Alex Kelly
 * @version 9/16/2015
 *
 * All edge cases are accounted for in each method. Thus none of the methods have preconditions.
 */
public class SinglyLinkedList {
	SingleNode head, tail;
	
	/**
	 * constructs a new SinglyLinkedList with head and tail as null
	 */
	public SinglyLinkedList() {
		head = null;
		tail = null;
	}
	
	/**
	 * returns true if list is empty, false otherwise
	 * @return - true if list is empty
	 * Theta(1)
	 */
	public boolean isEmpty() {
		return head==null;
	}
	
	/**
	 * returns the first element of the linked list
	 * @return - head of linked list or null if the list is empty
	 * Theta(1)
	 */
	public SingleNode getHead() {
		return head;
	}
	
	/**
	 * adds a new SingleNode at the end of the list
	 * @param i - the BigInteger to be added to the list
	 * Tehta(1)
	 */
	public void addIntAtEnd(BigInteger i) {
		if (this.isEmpty()) {
			head = tail = new SingleNode(i, null);
		}
		else {
			tail.setNext(new SingleNode(i, null));
			tail = tail.getNext();
		}
	}
	
	/**
	 * adds a new SingleNode at the front of the list
	 * @param i - the BigInteger to be added to the list
	 * Theta(1)
	 */
	public void addIntAtFront(BigInteger i) {
		if (this.isEmpty()) {
			head = tail = new SingleNode(i, null);
		}
		else {
			head = new SingleNode(i, head);
		}
	}
	
	/**
	 * counts the number of nodes in the list
	 * @return - the number of nodes in the linked list
	 * Theta(n)
	 */
	public int countNodes() {
		int counter = 0;
		SingleNode pointer = head;
		while (pointer != null) {
			pointer = pointer.getNext();
			counter++;
		}
		return counter;
	}

	/**
	 * calculates the sum of every node in the list
	 * @return - the sum of the value of every node
	 * Theta(n)
	 */
	public BigInteger sum() {
		SingleNode pointer = head;
		BigInteger s = BigInteger.valueOf(0);
		while (pointer != null) {
			s = s.add(pointer.getValue());
			pointer = pointer.getNext();
		}
		return s;
	}
	
	/**
	 * reverses the list's order
	 * Theta(n)
	 */
	public void reverse() {
		SingleNode a = null; //previous pointer
		SingleNode b = head; //current pointer
		SingleNode c; //next pointer
		
		while (b != null) {
			c = b.getNext();
			b.setNext(a); //reverses the direction of b's next pointer
			a = b;
			b = c;
		}
		head = a;
	}
	
	/**
	 * returns a string representation of every node in the list or empty string if list is empty
	 * Theta(n)
	 */
	public String toString() {
		StringBuilder s = new StringBuilder();
		SingleNode pointer = head;
		while (pointer != null) {
			s.append(pointer.toString());
			pointer = pointer.getNext();
		}
		return s.toString();
	}
}
