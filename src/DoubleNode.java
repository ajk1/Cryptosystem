/**
 * 
 */

/**
 * @author Alex Kelly
 * @version 9/16/2015
 *
 */
public class DoubleNode {
	DoubleNode next, prev;
	char c;
	
	/**
	 * 
	 * @param p - pointer to previous node
	 * @param ch - character for this node
	 * @param n - pointer to next node
	 */
	public DoubleNode(DoubleNode p, char ch, DoubleNode n) {
		prev = p;
		next = n;
		c = ch;
	}
	
	/**
	 * constructor with no arguments. Assigns null values to prev, next, and c
	 */
	public DoubleNode() {
		prev = next = null;
		c = '\0';
	}
	
	/**
	 * 
	 * @return - a pointer to the previous node
	 */
	public DoubleNode getPrev() {
		return prev;
	}
	
	/**
	 * 
	 * @param prev
	 */
	public void setPrev(DoubleNode prev) {
		this.prev = prev;
	}
	
	/**
	 * 
	 * @return
	 */
	public DoubleNode getNext() {
		return next;
	}
	
	/**
	 * 
	 * @param next
	 */
	public void setNext(DoubleNode next) {
		this.next = next;
	}
	
	/**
	 * 
	 * @return - the character that this node holds
	 */
	public char getC() {
		return c;
	}
	
	/**
	 * 
	 * @param c - the character to be assigned to this node
	 */
	public void setC(char c) {
		this.c = c;
	}
	
	/**
	 * 
	 * @return - c plus a space as a String
	 */
	public String toString() {
		return c+" ";
	}
	
	/**
	 * test driver for DoubleNode
	 * @param args
	 */
	public static void main(String[] args) {
		
	}
}
