
public class HelloWorldImplem extends UnicastRemoteObject implements HelloWorld {
 
	public String sayHello() throws RemoteException {
			String result = " hello world !!! ";
			System.out.println(" Méthode sayHello invoquée... " + result);
			return result;
	}
	
}
