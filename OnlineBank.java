package onlineBanking;//created package as onlineBanking

/*
 * Deposit and withdraw functionality for the Online Banking Application without 
   using synchronized.

 *  Although each time you run this code the output might be a little different.*/

public class OnlineBank implements Runnable {//Creates class as OnlineBank and implements with Runnable interface
	                                        ////classes are the basics of opps(object oriented programming)


	private Balance bal = new Balance(); // Creating object of Balance class

	public static void main(String[] args) //Here public is a access modifier which defines who can access this method
	//Here static a keyword which identifies class related thing
	//void is used to define return type of the method,void means method wont return any value
	//main is name of method
	//declares method String[]
	//String[]args means arguments will be passed into main method and says that main() as a parameter
	{
		 // Creating threads
		Thread thread1 = new Thread(new OnlineBank()); //new thread1
		Thread thread2 = new Thread(new OnlineBank());//new thread2
		//Initializing the names to the threads 
		thread1.setName("vamshi");//names as vamshi
		thread2.setName("krishna");//krishna
		// Starting the threads 
		thread1.start();
		thread2.start();
	}

	class Balance { //Creates Balance class
		private int balance = 50;

		public int getBal() { //Creates getBal method 
			return balance;  //Return balance 
		}

		public void withdrawal(int amount) { //Creates withdrawal method
			balance = balance - amount;  // It will deduct the amount from the Current balance 
		}
	}

	@Override
	public void run() {  // Creates Run Method 

		for (int r = 0; r<3 ; r++) {  // creating for loop 
			accntStatus(20); // If there's enough in the account make the withdrawal.
			if (bal.getBal() < 0 ) { // if user trying to withdraw greater amount
				System.out.println("Insufficient amount");//system is used to return code
		           //out is a static member
		         	//Println is used to print text  and gives output 
			}
		}
	}
/**For the first three attempts,everything is fine.
 This problem is known as a "race condition," where multiple threads can access the same resource 
 and can produce corrupted data.
	**/
	
	// Creating accntStatus method which cause race around condition . 
	private void accntStatus(int amt) { 
		if (bal.getBal() >= amt) { // if user trying to withdraw 
			System.out.println(Thread.currentThread().getName() + " is withdrawling");//system is used to return code
	           //out is a static member
	         	//Println is used to print text  and gives output 
			try {  //try block 
				Thread.sleep(100); // Put thread to sleep 
			} catch (InterruptedException ex) { // Catch block 
			}
			bal.withdrawal(amt); // Calling withdrawal method 
			System.out.println(Thread.currentThread().getName() + " is depositing");//system is used to return code
	           //out is a static member
	         	//Println is used to print text  and gives output 
		} else { // Else statement 
			System.err.println(Thread.currentThread().getName() + " has not enough balance to withdraw " + bal.getBal());//system is used to return code
	         	//Println is used to print text  and gives output 

		}
	}
}