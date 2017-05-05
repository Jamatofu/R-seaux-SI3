package dp.processing;

import java.util.List;

import dp.communication.Query;
import dp.communication.Request;
import dp.exception.IdeaException;
import dp.exception.RequestException;

/**
 * 
 * Allows to understand and execute a request
 * @author David Sene && Pierre Rainero
 *
 */
public class RequestOperator {
	private Repository repository;
	private Request request;
	
	/**
	 * Normal constructor
	 * @param repository respository used
	 * @param request resquest to used
	 */
	public RequestOperator(Repository repository, Request request){
		this.request = request;
		this.repository = repository;
	}
	
	/**
	 * Parse and execute the client's request
	 * @return the query to send to the client
	 * @throws IdeaException 
	 * @throws RequestException 
	 * @throws NumberFormatException 
	 */
	public Query execute() throws NumberFormatException, RequestException, IdeaException{
		return parseResource();
	}
	
	private Query parseResource() throws RequestException, NumberFormatException, IdeaException{
		Query query = null;
		
		switch(request.getResource()){
			case "Idea":
				query = parseIdeaMethod();
				break;
			case "Repository":
				query = parseRepositoryMethod();
				break;
			case "Student":
				query = parseStudentMethod();
				break;
			
			default:
				throw new RequestException("La ressource n'existe pas.");
		}
		
		return query;
	}
	
	private Query parseIdeaMethod() throws RequestException, NumberFormatException, IdeaException{
		Query query = null;
		
		switch(request.getMethod()){
			case "addContributor":
				if(request.getArgs().size()!=2)
					throw new RequestException("Idea-addContributor : La requête est mal formée.");
				repository.getIdea(Integer.valueOf(request.getArgs().get(0))).addContributor(repository.getStudent(request.getArgs().get(1)));
				query = new Query(Query.CLOSE); //TODO Normalement c'est pas un close, mais pour l'instant je fais qu'une requête (donc je close à chaque fois) pour être sur de pouvoir réutiliser le même port. Je ferais la requête qui call le close à la fin 
				query.addSentenceToQuery("Idea : "+repository.getIdea(Integer.valueOf(request.getArgs().get(0))).getTitle());
				query.addSentenceToQuery("Demandeur : "+repository.getStudent(request.getArgs().get(1)).getId());
				query.addSentenceToQuery("Demande effectuée avec succès !");
				break;
		
			default:
				throw new RequestException("Idea : La methode n'existe pas.");
		}
		
		return query;
	}
	
	private Query parseRepositoryMethod() throws RequestException{
		Query query = null;
		
		switch(request.getMethod()){
			case "getAllIdeas":
				List<Idea> ideas = repository.getAllIdeas();
				query = new Query(Query.CLOSE); //TODO same
				for(int i=0;i<ideas.size();i++)
					query.addSentenceToQuery(ideas.get(i).toString());
				break;
				
			default:
				throw new RequestException("Repository : La methode n'existe pas.");
		}
		
		return query;
	}
	
	private Query parseStudentMethod() throws RequestException{
		Query query = null;
		
		switch(request.getMethod()){
			case "setPassword":
				if(request.getArgs().size()!=2)
					throw new RequestException("Student-setPassword : La requête est mal formée.");
				repository.getStudent(request.getArgs().get(0)).setPassword(request.getArgs().get(1));
				query = new Query(Query.CLOSE);
				query.addSentenceToQuery("Demandeur : "+repository.getStudent(request.getArgs().get(0)).getId());
				query.addSentenceToQuery("Mot de passe modifié !");
				break;
		
			default:
				throw new RequestException("Student : La methode n'existe pas.");
			
		}
		
		return query;
	}
}
