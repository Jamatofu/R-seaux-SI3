package dp.server.communication;

import java.rmi.Naming;

import dp.common.communication.HelloWorld;

public class ClientRmi {
	
	

	 public static void main(String[] args) {
		 try {
			 	System.out.println("Recherche de l'objet serveur...");
			 	HelloWorld hello =(HelloWorld)Naming.lookup("rmi://localhost/HelloWorld");
			 	System.out.println("Invocation de la méthode sayHello...");
			 	String result = hello.sayHello();
			 	System.out.println("Affichage du résultat :");
			 	System.out.println(result);
			 	System.exit(0);
		 } 
		 catch(Exception e) {
			 e.printStackTrace();
		 }
	 }
	 

}
