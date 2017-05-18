package dp.common.communication;

import java.rmi.*;

public interface HelloWorld extends Remote {
	
	public String sayHello() throws RemoteException;
}