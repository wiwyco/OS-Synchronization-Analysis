/* Winslow Conneen
 * COSC 3355 Assignment 3.2
 * Dr. Subramanian
 * 4.14.2021
 * Purpose: Showcase concurrency controls with a monitor.
 */

public class winslowConneenSynchronizedProject3 implements Runnable{

		//instantiate global variables to be used by multiple classes
		private static winslowConneenSynchronizedNumberProject3 num = new winslowConneenSynchronizedNumberProject3(0);
		private String name;
		private Thread myThread;
		
		//Constructor begins thread
		winslowConneenSynchronizedProject3(String threadName)
		{
			name = threadName;
			myThread = new Thread(this, name);
			
			System.out.println("New Thread: " + myThread);
			myThread.start();
		}
		
		@Override
		synchronized public void run()
		{
			for(int i = 1; i <= 20; i++)
			{
				//for each iteration, use block synchronization with num as the monitor object
				synchronized(num)
				{
						//I also used method synchronization because for some reason that was the only way it worked
						//I was unable to effectively test the code with only 2 threads and was getting errors without
						//block synchronization for 10 threads, so I wasnt sure if i was getting errors because I was using
						//10 threads or if my synchronization was off. So I did both.
						//Critical section start
						num.increment();
						System.out.println(name + ": " + i + ", num = " + num.getVal());
						//critical section end
				}
						
				try
				{
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
			
			//My computer would not create reliable error for any number of threads less than 7 so 
			//I played it safe and made ten to make sure my code worked. feel free to do 2 instead.
			new winslowConneenSynchronizedProject3("Zero");
			new winslowConneenSynchronizedProject3("One");
			/*new MyThread1("Two");
			new MyThread1("Three");
			new MyThread1("Four");
			new MyThread1("Five");
			new MyThread1("Six");
			new MyThread1("Seven");
			new MyThread1("Eight");
			new MyThread1("Nine");*/
			
			//wait 25 seconds till all threads finish executing
			try
			{
				Thread.sleep(25000);
			}
			catch (InterruptedException e)
			{
				System.out.println("Main thread interrupted.");
			}
			
			System.out.println("Main thread exiting.");
			System.out.println("Final num = " + num);
			
		}
	}
