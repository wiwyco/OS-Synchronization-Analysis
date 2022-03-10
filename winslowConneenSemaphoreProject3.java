/* Winslow Conneen
 * COSC 3355 Assignment 3.1
 * Dr. Subramanian
 * 4.14.2021
 * Purpose: Showcase concurrency controls with a semaphore.
 */

import java.util.concurrent.Semaphore;

public class winslowConneenSemaphoreProject3 implements Runnable{

	//Instantiate Variables including a thread and a semaphore
	private static int num = 0;
	private String name;
	private Thread myThread;
	private Semaphore sem;
	
	//Use constructor to combine the thread name, thread and start the semaphore.
	winslowConneenSemaphoreProject3(String threadName, Semaphore startSem)
	{
		name = threadName;
		myThread = new Thread(this, name);
		sem = startSem;
		
		//Defines thread name at beginning of thread and starts thread.
		System.out.println("New Thread: " + myThread);
		myThread.start();
	}
	
	@Override
	public void run()
	{
		for(int i = 1; i <= 20; i++)
		{
			try
			{
				//Thread claims the semaphore so that no other threads can run concurrently
				sem.acquire();
				//Critical section start
				
				num++;
				System.out.println(name + ": " + i + ", num = " + num);
				
				//critical section end
				//Thread releases semaphore so that next thread can run
				sem.release();
				
				//Wait 1 second before next iteration for both threads to finish
				Thread.sleep(1000);
			}
			catch (InterruptedException e)
			{
				System.out.println(name + " thread interrupted");
			}
		}
		
		System.out.println(name + " exiting.");
	}
	
	public static void main(String [] args)
	{
		//instantiate and define semaphore to contain 1 open spot for processing
		Semaphore sem = new Semaphore(1);
		//Start two threads
		new winslowConneenSemaphoreProject3("Zero", sem);
		new winslowConneenSemaphoreProject3("One", sem);
		
		//wait 25 seconds for both threads to complete
		try
		{
			Thread.sleep(25000);
		}
		catch (InterruptedException e)
		{
			System.out.println("Main thread interrupted.");
		}
		
		//notate thread termination
		System.out.println("Main thread exiting.");
		System.out.println("Final num = " + num);
		
	}
}
