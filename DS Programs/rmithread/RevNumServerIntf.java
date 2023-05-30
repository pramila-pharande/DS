import java.rmi.*;
public interface RevNumServerIntf extends Remote 
{
	int reverseNum(int n) throws RemoteException;
}
