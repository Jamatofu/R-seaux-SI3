package dp.server.communication;

import java.rmi.*;

import dp.common.communication.HelloWorld;
import dp.common.communication.HelloWorldImplem;


public class RmiServer {

	public static void main(String[] args) {
			
		try {
				System.out.println("Création de l'objet serveur...");
				HelloWorld hello = new HelloWorldImplem();
				System.out.println("Référencement dans le RMIRegistry...");
				Naming.rebind("HelloWorld",hello);
				System.out.println("Attente d'invocations - CTRL-C pour stopper");
		} 
		catch(Exception e) {
				e.printStackTrace();
		}
	}
	
} 
