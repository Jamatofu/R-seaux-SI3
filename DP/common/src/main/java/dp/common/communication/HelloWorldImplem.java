package dp.common.communication;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class HelloWorldImplem extends UnicastRemoteObject implements HelloWorld {
 
	public HelloWorldImplem() throws RemoteException {
		super();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 8125413946567941738L;

	public String sayHello() throws RemoteException {
			String result = " hello world !!! ";
			System.out.println(" Méthode sayHello invoquée... " + result);
			return result;
	}
	
}
