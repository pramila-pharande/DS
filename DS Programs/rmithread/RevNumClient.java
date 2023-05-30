import java.util.*;  
import java.rmi.*;
public class RevNumClient extends Thread implements Runnable 
	{
		public void run()  
	    	{    
			System.out.println("Thread is running..."); 
			System.out.println(Thread.currentThread().getName()); 
		try 
		{        	
			
			String revnumServerURL = "rmi://localhost/REV-NUM-SERVER";
			RevNumServerIntf revnumServerIntf = (RevNumServerIntf)Naming.lookup(revnumServerURL);
			Scanner sc= new Scanner(System.in); 		
			int n;
			System.out.println("Enter number : ");
			n = sc.nextInt(); 
			System.out.println("The reverse number is: " + revnumServerIntf.reverseNum(n)); 
			}
			catch(Exception e) 
			{
			System.out.println("Exception: " + e);
			}  
    		}    
	
		public static void main(String args[]) 
		{
						
			RevNumClient thread1=new RevNumClient(); 
			RevNumClient thread2=new RevNumClient();    
               		thread1.start();
               		thread2.start();        
        		
		}
}
