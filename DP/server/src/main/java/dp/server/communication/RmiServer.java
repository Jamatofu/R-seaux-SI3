package dp.server.communication;

import java.rmi.*;

import dp.common.communication.Repository;
import dp.common.communication.RepositoryImplem;


public class RmiServer {

	public static void main(String[] args) {
			
		try {
				System.out.println("Objet repository");
				System.out.println("................: Création... ");
				Repository repository = new RepositoryImplem();
				System.out.println("................: Initialisation.... ");
				((RepositoryImplem)repository).fakeInit();
				System.out.println("................: Référencement dans le RMIRegistry...");
				Naming.rebind("repository",repository);
				System.out.println("Attente d'invocations - CTRL-C pour stopper");
		} 
		catch(Exception e) {
				e.printStackTrace();
		}
	}
	
} 