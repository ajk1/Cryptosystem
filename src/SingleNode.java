/**
 * 
 */
import java.math.*;
/**
 * @author Alex Kelly
 * @version 9/16/2015
 * 
 */
public class SingleNode {
	SingleNode next;
	BigInteger value;
	
	/**
	 * constructs a new SingleNode with value initialized to v and next initialized to n
	 * @param v - BigInteger value for this node
	 * @param n - pointer to next node
	 */
	public SingleNode(BigInteger v, SingleNode n) {
		next = n;
		value = v;
	}
	
	/**
	 * constructs a new SingleNode with value and next initialized to null
	 */
	public SingleNode() {
		next = null;
		value = null;
	}
	
	/**
	 * 
	 * @return - pointer to next SingleNode in the list
	 */
	public SingleNode getNext() {
		return next;
	}
	
	/**
	 * 
	 * @param n - SingleNode to be set as the next node in the list
	 */
	public void setNext(SingleNode n) {
		next = n;
	}
	
	/**
	 * 
	 * @return - the BigInteger value that is held by this node
	 */
	public BigInteger getValue() {
		return value;
	}
	
	/**
	 * 
	 * @param v - a BigInteger value to be held by this node
	 */
	public void setValue(BigInteger v) {
		value = v;
	}
	
	/**
	 * returns the value of the BigInteger as a string
	 */
	public String toString() {
		return value.longValue() +", ";
	}
}
