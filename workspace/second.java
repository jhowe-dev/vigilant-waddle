import java.util.*;

public class second implements Runnable{
 
        /*
         * A class must implement run method to implement Runnable
         * interface. Signature of the run method is,
         *
         * public void run()
         *
         * Code written inside run method will constite a new thread.
         * New thread will end when run method returns.
         */
        public void run(){
			Scanner sc = new Scanner(System.in);
			while(sc.hasNextInt()){
				int butt = sc.nextInt();
				System.out.println("dafsdfasdfasdfasdfasdf");
			}
        }
}
 
