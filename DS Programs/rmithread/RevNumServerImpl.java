import java.rmi.*;
import java.rmi.server.*;
public class RevNumServerImpl extends UnicastRemoteObject implements RevNumServerIntf 
{
	public RevNumServerImpl() throws RemoteException 
	{
						
	}
	public int reverseNum(int n) throws RemoteException 
	{
		int reverse = 0;  
		while(n!= 0)   
		{  
			int remainder = n % 10;  
			reverse = reverse * 10 + remainder;  
			n = n/10;  
		}  
		return reverse;
	}
}
