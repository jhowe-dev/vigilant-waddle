import java.util.*;

public class thread implements Runnable{
 
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
               
                for(int i=0; i < 10000; i++){
                        System.out.println("Child Thread : " + i);
                       
                        try{
                                Thread.sleep(500);
                        }
                        catch(InterruptedException ie){
                                System.out.println("Child thread interrupted! " + ie);
                        }
                }
               
                System.out.println("Child thread finished!");
        }
       
        public static void main(String[] args) {
               
                /*
                 * To create new thread, use
                 * Thread(Runnable thread, String threadName)
                 * constructor.
                 *
                 */
               
                Thread t = new Thread(new thread(), "My Thread");
                Thread s = new Thread(new second(), "second"); 

                /*
                 * To start a particular thread, use
                 * void start() method of Thread class.
                 *
                 * Please note that, after creation of a thread it will not start
                 * running until we call start method.
                 */
               
                t.start();
                s.start(); 
        }
}
