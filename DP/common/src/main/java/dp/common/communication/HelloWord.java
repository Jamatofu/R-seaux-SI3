
import java.rmi.*;
interface HelloWorld extends Remote {
	public String sayHello() throws RemoteException;
}