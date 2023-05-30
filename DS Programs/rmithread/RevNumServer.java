import java.net.*;
import java.rmi.*;
public class RevNumServer 
{
	public static void main(String args[]) 
	{
		try 
		{
			RevNumServerImpl revnumServerImpl = new RevNumServerImpl();
			Naming.rebind("REV-NUM-SERVER", revnumServerImpl);
		}
		catch(Exception e) 
		{
			System.out.println("Exception: " + e);
		}
	}
}
