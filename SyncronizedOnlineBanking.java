package syncronizedOnlineBanking;//created package as syncronizedOnlineBanking

/*
 * Deposit and withdraw functionality for the Online Banking Application without
   using synchronized.
 * 
 * The synchronized methods prevent more than one thread from accessing an object's critical
   method code simultaneously.
 */

public class SyncronizedOnlineBanking implements Runnable {//created class as SyncronizedOnlineBanking

	private Balance bal = new Balance();//created object of Balance class 
	public static void main(String[] args)  //Here public is a access modifier which defines who can access this method
	//Here static a keyword which identifies class related thing
	//void is used to define return type of the method,void means method wont return any value
	//main is name of method
	//declares method String[]
	//String[]args means arguments will be passed into main method and says that main() as a parameter
	{
		
		//creating threads
		Thread thread1 = new Thread(new SyncronizedOnlineBanking());//initializing the names to the threads
		Thread thread2 = new Thread(new SyncronizedOnlineBanking());
		thread1.setName("vamshi");
		thread2.setName("krishna");//starting the threads
		thread1.start();
		thread2.start();
	}

	class Balance {//creating balance class
		private int balance = 50;

		public int getBal() {//Creates getBal method 
			return balance;//returns balance
		}

		public void withdraw(int amount) {//creates withdrawal method
			balance = balance - amount;// It will deduct the amount from the Current balance
		}
	}

	@Override
	public void run() {//creates run method

		for (int r = 0; r < 3; r++) {//creating for loop
			accntStatus(20);//if theres enough in the account make the withdrawal.
			if (bal.getBal() < 0) {
				System.out.println("account is overdrawn!");//system is used to return code
		           //out is a static member
		         	//Println is used to print text  and gives output 
			}
		}
	}
	/**
	 *Synchronization is the solution to prevents race conditions from happening.
	 * The synchronized keyword places a lock on an important object or piece of code 
	    to make sure that only one thread at a time will have access.
	 **/
	
	
	
	// Creating synchronized method to overcome race around condition.
	private synchronized void accntStatus(int amt) {//private methods are inaccessible
		if (bal.getBal() >= amt) {//getbalance
			System.out.println(Thread.currentThread().getName() + " is withdrawling");//system is used to return code
	           //out is a static member
	         	//Println is used to print text  and gives output 
			try {
				Thread.sleep(100);//sleep is state of thread
			} catch (InterruptedException ex) {
			}
			bal.withdraw(amt);//withdrawing amount
			System.out.println(Thread.currentThread().getName() + " is depositing");//system is used to return code
	           //out is a static member
	         	//Println is used to print text  and gives output 
		} else {
			System.err
					.println(Thread.currentThread().getName() + " has not enough balance to withdraw " + bal.getBal());//system is used to return code
	         	//Println is used to print text  and gives output 

		}
	}
}
