/**
 * 
 */
import java.math.*;
import java.util.*;
/**
 * @author Alex Kelly
 * @version 9/16/2015
 *
 */
public class Cryptosystem {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String message;
		
		System.out.println("**Merkle-Hellman Cryptosystem**");
		
		do {
			System.out.println("Enter message to encrypt (up to 80 chars):");
			message = sc.nextLine();
			if (message.length() > 80) System.out.println("Message is too long, please try again.");
		} while (message.length() > 80);

		System.out.println("Clear text: "+ message +"\nNumber of Bytes: "+ message.length());
		
		//randomly generates private key
		SinglyLinkedList w = new SinglyLinkedList(); //w is the basis for the private key
		BigInteger x = BigInteger.valueOf(0); //x holds the value to be added on to w
		for (int i=0; i<8*message.length(); i++) { //generates a 640-length linked list as w (one element per bit)
			x = w.sum().add(new BigInteger(2, new Random())).add(BigInteger.valueOf(1));
			w.addIntAtEnd(x); //x is larger than previous element by some integer in [1,256]
		}
		
		BigInteger q = w.sum().add(BigInteger.valueOf(1));
		q = q.add(new BigInteger(8, new Random())); //generates some q larger than sum of w
		BigInteger r;
		do { //generates a random r that is coprime to q
			r = BigInteger.valueOf((long) (q.longValue()*Math.random()));
		} while (q.gcd(r).longValue() != 1);
		
		BigInteger encrypted = encrypt(message, w, q, r); 
		System.out.println("Encrypted message: "+ encrypted.toString());
		System.out.println("Decrypted message: "+ decrypt(encrypted, w, q, r));
	} 
	
	/**
	 * Uses the primary key (w,q,r) to generate the public key and encrypt message into a BigInteger.
	 * @param message
	 * @param w - a SinglyLinkedList that is the basis for the private key
	 * @param q - a number larger than the sum of w
	 * @param r - a number less than q that is coprime to q
	 * @return - the encrypted message represented as a BigInteger
	 * precondition: length of w is at least 8 times larger than length of message
	 * Theta(n)
	 */
	public static BigInteger encrypt(String message, SinglyLinkedList w, BigInteger q, BigInteger r) {
		SinglyLinkedList b = new SinglyLinkedList(); //b is the public key
		SingleNode pointer = w.getHead();
		
		for (int i=0; i<8*message.length(); i++) { //calculates b_i = r*w_i mod q for all i in w
			b.addIntAtEnd(r.multiply(pointer.getValue()).mod(q)); 
			pointer = pointer.getNext();
		}
		
		byte[] bytes = message.getBytes();
		
		BigInteger encrypted = BigInteger.valueOf(0);
		pointer = b.getHead();
		
		for (int i=0; i<message.length(); i++) { //multiplies each bit in message by corresponding element in w, and adds together
			int value = bytes[i];
			for (int j=0; j<8; j++) {
				encrypted = encrypted.add(pointer.getValue().multiply(BigInteger.valueOf((value&128) == 0 ? 0:1)));
				value <<= 1;
				pointer = pointer.getNext();
			}
		}
		return encrypted;
	}
	
	/**
	 * Uses the primary key (w,q,r) to decrypt the encrypted BigInteger and convert to a String.
	 * @param encrypted - the encrypted message as a BigInteger
	 * @param w - a SinglyLinkedList that is the basis for the private key
	 * @param q - a number larger than the sum of w
	 * @param r - a number less than q that is coprime to q
	 * @return - the decrypted message as a String
	 * precondition: encrypted message is less than the sum of all nodes in w
	 * Theta(n)
	 */
	public static String decrypt(BigInteger encrypted, SinglyLinkedList w, BigInteger q, BigInteger r) {
		BigInteger x = encrypted.multiply(r.modInverse(q)); //multiplies message by the modular inverse of r mod q
		x = x.mod(q);
		w.reverse(); //must reverse w to a superdecreasing order to decrypt
		SingleNode pointer = w.getHead();
		
		StringBuilder binary;
		StringBuilder decrypted = new StringBuilder();
		
		for (int i=0; i<w.countNodes()/8; i++) { //loops through each byte, adding the decrypted character to StringBuilder decrypted
			binary = new StringBuilder();
			for (int j=0; j<8; j++) { //converts to binary one byte at a time
				if (x.compareTo(pointer.getValue()) >= 0) {
					x = x.subtract(pointer.getValue());
					binary.append(1);
				}
				else binary.append(0);
				pointer = pointer.getNext();
			}
			decrypted.append((char) Integer.parseInt(binary.reverse().toString(), 2));
		}
		
		return decrypted.reverse().toString(); //decrypted also needs to be reversed due to the reversal of w
	}
}
