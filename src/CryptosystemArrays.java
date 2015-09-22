/**
 * 
 */
import java.math.*;
import java.util.*;

/**
 * @author Alex Kelly
 *
 */
public class CryptosystemArrays {

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
		BigInteger w[] = new BigInteger[8*message.length()]; //w is the basis for the private key
		BigInteger x = BigInteger.valueOf(0); //x holds the value to be added on to w
		BigInteger wSum = BigInteger.valueOf(0);
		for (int i=0; i<8*message.length(); i++) { //generates a 640-length linked list as w (one element per bit)
			x = wSum.add(new BigInteger(2, new Random())).add(BigInteger.valueOf(1));
			w[i] = x; //x is larger than previous element by some integer in [1,256]
			wSum = wSum.add(x); //updates the running sum of w
		} 
		
		BigInteger q = wSum.add(BigInteger.valueOf(1));
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
	 * @param w - an array of BigIntegers that is the basis for the private key
	 * @param q - a number larger than the sum of w
	 * @param r - a number less than q that is coprime to q
	 * @return - the encrypted message represented as a BigInteger
	 * precondition: length of w is at least 8 times larger than length of message
	 * Theta(n)
	 */
	public static BigInteger encrypt(String message, BigInteger[] w, BigInteger q, BigInteger r) {
		BigInteger[] b = new BigInteger[w.length]; //b is the public key
		
		for (int i=0; i<8*message.length(); i++) { //calculates b_i = r*w_i mod q for all i in w
			b[i] = r.multiply(w[i]).mod(q);
		}
		
		byte[] bytes = message.getBytes();
		
		BigInteger encrypted = BigInteger.valueOf(0);
		
		for (int i=0; i<message.length(); i++) { //multiplies each bit in message by corresponding element in w, and adds together
			int value = bytes[i];
			for (int j=0; j<8; j++) {
				encrypted = encrypted.add(b[i*8+j].multiply(BigInteger.valueOf((value&128) == 0 ? 0:1)));
				value <<= 1;
			}
		}
		return encrypted;
	}
	
	/**
	 * Uses the primary key (w,q,r) to decrypt the encrypted BigInteger and convert to a String.
	 * @param encrypted - the encrypted message as a BigInteger
	 * @param w - an array of BigIntegers that is the basis for the private key
	 * @param q - a number larger than the sum of w
	 * @param r - a number less than q that is coprime to q
	 * @return - the decrypted message as a String
	 * precondition: encrypted message is less than the sum of all elements in w
	 * Theta(n)
	 */
	public static String decrypt(BigInteger encrypted, BigInteger[] w, BigInteger q, BigInteger r) {
		BigInteger x = encrypted.multiply(r.modInverse(q)); //multiplies message by the modular inverse of r mod q
		x = x.mod(q);
		
		StringBuilder binary = new StringBuilder();
		StringBuilder decrypted = new StringBuilder();
		
		for (int i=w.length-1; i>=0; i--) { //loops through each byte, adding the decrypted character to StringBuilder decrypted
			if (x.compareTo(w[i]) >= 0) {
				x = x.subtract(w[i]);
				binary.append(1);
			}
			else binary.append(0);
			
			if (binary.length()==8) {
				decrypted.append((char) Integer.parseInt(binary.reverse().toString(), 2));
				binary = new StringBuilder();
			}
		}
		
		return decrypted.reverse().toString(); //decrypted also needs to be reversed due to the reversal of w
	}
}