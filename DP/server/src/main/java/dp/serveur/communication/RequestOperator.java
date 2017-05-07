package dp.serveur.communication;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import dp.common.communication.Query;
import dp.common.communication.Request;
import dp.common.communication.Resource;
import dp.server.exception.IdeaException;
import dp.server.exception.RepositoryException;
import dp.server.exception.RequestException;
import dp.server.processing.Idea;
import dp.server.processing.Repository;
import dp.server.processing.Student;

/**
 * 
 * Allows to understand and execute a request
 * @author David Sene && Pierre Rainero
 *
 */
public class RequestOperator {
	private Repository repository;
	private Request request;
	private final static String IDEA = "Idée : ";
	private final static String BADLY_FORMED = "La requête est mal formée.";
	
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
	 * @throws RepositoryException 
	 */
	public Query execute() throws NumberFormatException, RequestException, IdeaException, RepositoryException{
		return parseResource();
	}
	
	/**
	 * Parse in fonction of the resource
	 * @return the query to send to the client
	 * @throws RequestException
	 * @throws NumberFormatException
	 * @throws IdeaException
	 * @throws RepositoryException
	 */
	private Query parseResource() throws RequestException, NumberFormatException, IdeaException, RepositoryException{
		Query query = null;
		
		switch(request.getResource()){
			case IDEA:
				query = parseIdeaMethod();
				break;
				
			case REPOSITORY:
				query = parseRepositoryMethod();
				break;
				
			case STUDENT:
				query = parseStudentMethod();
				break;
			
			case CONNECTION:
				if(request.getArgs().size()!=2)
					throw new RequestException("Connexion : "+BADLY_FORMED);
				
				Student tmp = repository.getStudent(request.getArgs().get(0));
				if(tmp.getPassword().equals(request.getArgs().get(1))){
					query = new Query(Query.CONNECTED.toString());
					query.addSentenceToQuery("Connexion réussit !");
				}else{
					query = new Query(Query.CONNECTION_FAIL.toString());
					query.addSentenceToQuery("Mot de passe incorrect !");
				}
				break;
				
			case CLOSE:
				query = new Query(Resource.CLOSE.toString());
				query.addSentenceToQuery("Le serveur a bien été fermé !");
				break;
			
			default:
				throw new RequestException("La ressource n'existe pas.");
		}
		
		return query;
	}
	
	/**
	 * Parse and execute the method for Idea's resource
	 * @return the query to send to the client
	 * @throws RequestException
	 * @throws NumberFormatException
	 * @throws IdeaException
	 * @throws RepositoryException
	 */
	private Query parseIdeaMethod() throws RequestException, NumberFormatException, IdeaException, RepositoryException{
		Query query = null;
		Idea tmp = null;
		
		switch(request.getMethod()){
			case ADD_CONTRIBUTOR:
				if(request.getArgs().size()!=2)
					throw new RequestException("Idea-addContributor : "+BADLY_FORMED);
				
				tmp = repository.getIdea(Integer.valueOf(request.getArgs().get(1)));
				tmp.addContributor(repository.getStudent(request.getArgs().get(0)));
				query = new Query(Query.TOCLI); 
				query.addSentenceToQuery(IDEA+tmp.getTitle());
				query.addSentenceToQuery("Demandeur : "+repository.getStudent(request.getArgs().get(0)).getId());
				query.addSentenceToQuery("Demande effectuée avec succès !");
				break;
				
			case CHANGE_STATE:
				if(request.getArgs().size()!=2)
					throw new RequestException("Idea-changeState : "+BADLY_FORMED);
				
				tmp = repository.getIdea(Integer.valueOf(request.getArgs().get(1)));
				repository.changeState(Integer.valueOf(request.getArgs().get(1)), request.getArgs().get(0));
				query = new Query(Query.TOCLI); 
				query.addSentenceToQuery("\""+tmp.getTitle()+"\" est désormais un projet !");
				break;
				
			case SET_TITLE:
				if(request.getArgs().size()!=3)
					throw new RequestException("Idea-setTitle : "+BADLY_FORMED);
				
				tmp = repository.getIdea(Integer.valueOf(request.getArgs().get(1)));
				query = new Query(Query.TOCLI);
				query.addSentenceToQuery("Ancien nom : "+tmp.getTitle());
				repository.setTitle(Integer.valueOf(request.getArgs().get(1)), request.getArgs().get(0), request.getArgs().get(2));
				query.addSentenceToQuery("Nouveau nom : "+tmp.getTitle());
				break;
				
			case SET_DESCRIPTION:
				if(request.getArgs().size()!=3)
					throw new RequestException("Idea-setDescription : "+BADLY_FORMED);
				
				tmp = repository.getIdea(Integer.valueOf(request.getArgs().get(1)));
				query = new Query(Query.TOCLI);
				query.addSentenceToQuery(IDEA+tmp.getTitle());
				repository.setDescription(Integer.valueOf(request.getArgs().get(1)), request.getArgs().get(0), request.getArgs().get(2));
				query.addSentenceToQuery("Nouvelle description nom : "+tmp.getDescription());
				break;
		
			case AGREE_CONTRIBUTOR:
				if(request.getArgs().size()!=3)
					throw new RequestException("Idea-agreeParticipant : "+BADLY_FORMED);
				
				tmp = repository.getIdea(Integer.valueOf(request.getArgs().get(1)));
				repository.agreeContributor(Integer.valueOf(request.getArgs().get(1)), request.getArgs().get(0), request.getArgs().get(2));
				query = new Query(Query.TOCLI); 
				query.addSentenceToQuery(IDEA+tmp.getTitle());
				query.addSentenceToQuery(repository.getStudent(request.getArgs().get(2)).getId()+" est désormais un participant validé !");
				break;
				
			case GET_CONTRIBUTORS:
				if(request.getArgs().size()!=1)
					throw new RequestException("Idea-getContributors : "+BADLY_FORMED);
				
				tmp = repository.getIdea(Integer.valueOf(request.getArgs().get(0)));
				Map<Student, Boolean> contributors = tmp.getContributors();
				query = new Query(Query.TOCLI);
				defaultEmptyMessage(query, contributors.values());
				for(Entry<Student, Boolean> entry : contributors.entrySet()){
					String status = entry.getValue() ? " : Validé" : " : En attente";
					query.addSentenceToQuery(entry.getKey().getId() + status);
				}
				break;
			default:
				throw new RequestException(IDEA+"La methode n'existe pas.");
		}
		
		return query;
	}
	
	/**
	 * Parse and execute the method for Repository's resource
	 * @return the query to send to the client
	 * @throws RequestException
	 * @throws RepositoryException
	 */
	private Query parseRepositoryMethod() throws RequestException, RepositoryException{
		Query query = null;
		Idea tmp = null;
		
		switch(request.getMethod()){
			case GET_ALL_IDEAS: case GET_ALL_IDEAS_FOR_ONE_STUDENT :
				if(request.getArgs().size()>1)
					throw new RequestException("Repository-getAllIdeas : "+BADLY_FORMED);
				
				if(request.getArgs().size()==0)
					query= allIdeas();
				else
					query = allIdeas(request.getArgs().get(0));
				break;
				
			case ADD_IDEA:
				if(request.getArgs().size()!=3)
					throw new RequestException("Repository-addIdea : "+BADLY_FORMED);
				
				tmp = new Idea(repository.getStudent(request.getArgs().get(0)), request.getArgs().get(1), request.getArgs().get(2));
				repository.addIdea(tmp);
				query = new Query(Query.TOCLI);
				query.addSentenceToQuery(IDEA+tmp.getTitle());
				query.addSentenceToQuery("A bien été crée !");
				break;
				
			case REMOVE_IDEA:
				if(request.getArgs().size()!=2)
					throw new RequestException("Repository-removeIdea : "+BADLY_FORMED);
				
				tmp = repository.getIdea(Integer.valueOf(request.getArgs().get(1)));
				query = new Query(Query.TOCLI);
				query.addSentenceToQuery(IDEA+tmp.getTitle());
				repository.removeIdea(Integer.valueOf(request.getArgs().get(1)), request.getArgs().get(0));
				query.addSentenceToQuery("A bien été supprimé !");
				break;
				
			case GET_IDEA:
				if(request.getArgs().size()!=1)
					throw new RequestException("Repository-getIdea : "+BADLY_FORMED);
				
				tmp = repository.getIdea(Integer.valueOf(request.getArgs().get(0)));
				query = new Query(Query.TOCLI);
				query.addSentenceToQuery(tmp.toString());
				query.addSentenceToQuery("Description :");
				query.addSentenceToQuery(tmp.getDescription());
				break;
				
			case GET_PROJECTS:
				query = new Query(Query.TOCLI);
				query= allProjects();
				break;
				
			default:
				throw new RequestException("Repository : La methode n'existe pas.");
		}
		
		return query;
	}
	
	/**
	 * Add all ideas to the query
	 * @return the query to send to the client
	 */
	private Query allIdeas(){
		List<Idea> ideas = repository.getAllIdeas();
		Query query = new Query(Query.TOCLI);
		defaultEmptyMessage(query, ideas);
		for(int i=0;i<ideas.size();i++)
			query.addSentenceToQuery(ideas.get(i).toString());
		return query;
	}
	
	/**
	 * Add all ideas to the query
	 * @return the query to send to the client
	 * @throws RepositoryException 
	 */
	private Query allIdeas(String studentId) throws RepositoryException{
		List<Idea> ideas = repository.getAllIdeas(repository.getStudent(studentId));
		Query query = new Query(Query.TOCLI);
		defaultEmptyMessage(query, ideas);
		for(int i=0;i<ideas.size();i++)
			query.addSentenceToQuery(ideas.get(i).toString());
		return query;
	}
	
	/**
	 * Add all projects to the query
	 * @return the query to send to the client
	 */
	private Query allProjects(){
		List<Idea> ideas = repository.getProjets();
		Query query = new Query(Query.TOCLI);
		defaultEmptyMessage(query, ideas);
		for(int i=0;i<ideas.size();i++)
			query.addSentenceToQuery(ideas.get(i).toString());
		return query;
	}
	
	private void defaultEmptyMessage(Query query, Collection<?> collec){
		if(collec.isEmpty())
			query.addSentenceToQuery("Il n'y a rien le moment...");
	}
	
	/**
	 * Parse and execute the method for Student's resource
	 * @return the query to send to the client
	 * @throws RequestException
	 * @throws RepositoryException 
	 */
	private Query parseStudentMethod() throws RequestException, RepositoryException{
		Query query = null;
		
		switch(request.getMethod()){
			case SET_PASSWORD:
				if(request.getArgs().size()!=2)
					throw new RequestException("Student-setPassword : "+BADLY_FORMED);
				
				repository.getStudent(request.getArgs().get(0)).setPassword(request.getArgs().get(1));
				query = new Query(Query.TOCLI);
				query.addSentenceToQuery("Demandeur : "+repository.getStudent(request.getArgs().get(0)).getId());
				query.addSentenceToQuery("Mot de passe modifié !");
				break;
		
			default:
				throw new RequestException("Student : La methode n'existe pas.");
			
		}
		
		return query;
	}
}
