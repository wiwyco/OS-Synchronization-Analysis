/* Winslow Conneen
 * COSC 3355 Assignment 3.2
 * Dr. Subramanian
 * 4.14.2021
 * Purpose: Create monitor object for use in synchronization
 */

public class winslowConneenSynchronizedNumberProject3 {

	private int val;
	
	//Constructor
	winslowConneenSynchronizedNumberProject3(int value)
	{
		val = value;
	}

	//Getters and setters
	public synchronized int getVal() {
		return val;
	}

	public synchronized void setVal(int val) {
		this.val = val;
	}
	
	//Add 1 to val
	public synchronized void increment()
	{
		val++;
	}

	//toString method
	@Override
	public String toString() {
		return Integer.toString(val);
	}
}
