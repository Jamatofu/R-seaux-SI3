package dp.client.communication;

import java.rmi.Naming;
import java.util.List;
import dp.common.communication.Idea;
import dp.common.communication.Repository;
import dp.common.communication.Student;

public class ClientRmi {
	
	 public static void main(String[] args) {
		 try {
			 	System.out.println("Recherche de l'objet serveur...");
			 	Repository remoteRepository =(Repository)Naming.lookup("rmi://localhost/repository");
			 	
			 	
			 	List<Idea> ideas = remoteRepository.getAllIdeas();
			 	System.out.println("Affichage de liste initiale d'idées :");
			 	printIdeasList(ideas);
			 	
			 	Student student = new Student("154864","kjskdj");
			 	Idea idea = new Idea(student, "idee de test", "testons pour pouvoir");
			 	remoteRepository.addIdea(idea);
			 	ideas = remoteRepository.getAllIdeas();
			 	System.out.println("Affichage de la liste des idée apres ajout d'une idée:");
			 	printIdeasList(ideas);
			 	
			 	ideas = remoteRepository.getAllIdeas(student);
			 	System.out.println("Affichage de la liste des idée d'un etudiant");
			 	printIdeasList(ideas);
			 	
			 	
			 		
		 } 
		 catch(Exception e) {
			 e.printStackTrace();
		 }
	 }

	private static void printIdeasList(List<Idea> ideas) {
		for (Idea idea1 : ideas) {
			System.out.println(idea1);
		}
	}
	 
	 
	 

}
