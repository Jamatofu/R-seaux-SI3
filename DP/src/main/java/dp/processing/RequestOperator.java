package dp.processing;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import dp.communication.Query;
import dp.communication.Request;
import dp.exception.IdeaException;
import dp.exception.RepositoryException;
import dp.exception.RequestException;
import dp.communication.Action;

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
	
	private Query parseResource() throws RequestException, NumberFormatException, IdeaException, RepositoryException{
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
				
			case Query.CLOSE:
				query = new Query(Query.CLOSE);
				query.addSentenceToQuery("Le serveur a bien été fermé !");
				break;
			
			default:
				throw new RequestException("La ressource n'existe pas.");
		}
		
		return query;
	}
	
	private Query parseIdeaMethod() throws RequestException, NumberFormatException, IdeaException, RepositoryException{
		Query query = null;
		Idea tmp = null;
		
		switch(Action.getAction(request.getMethod())){
			case ADD_CONTRIBUTOR:
				if(request.getArgs().size()!=2)
					throw new RequestException("Idea-addContributor : "+BADLY_FORMED);
				
				tmp = repository.getIdea(Integer.valueOf(request.getArgs().get(0)));
				tmp.addContributor(repository.getStudent(request.getArgs().get(1)));
				query = new Query(Query.TOCLI); 
				query.addSentenceToQuery(IDEA+tmp.getTitle());
				query.addSentenceToQuery("Demandeur : "+repository.getStudent(request.getArgs().get(1)).getId());
				query.addSentenceToQuery("Demande effectuée avec succès !");
				break;
				
			case CHANGE_STATE:
				if(request.getArgs().size()!=2)
					throw new RequestException("Idea-changeState : "+BADLY_FORMED);
				
				tmp = repository.getIdea(Integer.valueOf(request.getArgs().get(1)));
				repository.changeState(Integer.valueOf(request.getArgs().get(0)), request.getArgs().get(1));
				query = new Query(Query.TOCLI); 
				query.addSentenceToQuery("\""+tmp.getTitle()+"\" est désormais un projet !");
				break;
				
			case SET_TITLE:
				if(request.getArgs().size()!=3)
					throw new RequestException("Idea-setTitle : "+BADLY_FORMED);
				
				tmp = repository.getIdea(Integer.valueOf(request.getArgs().get(1)));
				query = new Query(Query.TOCLI);
				query.addSentenceToQuery("Ancien nom : "+tmp.getTitle());
				repository.setTitle(Integer.valueOf(request.getArgs().get(0)), request.getArgs().get(1), request.getArgs().get(2));
				query.addSentenceToQuery("Nouveau nom : "+tmp.getTitle());
				break;
				
			case SET_DESCRIPTION:
				if(request.getArgs().size()!=3)
					throw new RequestException("Idea-setDescription : "+BADLY_FORMED);
				
				tmp = repository.getIdea(Integer.valueOf(request.getArgs().get(1)));
				query = new Query(Query.TOCLI);
				query.addSentenceToQuery(IDEA+tmp.getTitle());
				repository.setDescription(Integer.valueOf(request.getArgs().get(0)), request.getArgs().get(1), request.getArgs().get(2));
				query.addSentenceToQuery("Nouvelle description nom : "+tmp.getDescription());
				break;
		
			case AGREE_CONTRIBUTOR:
				if(request.getArgs().size()!=2)
					throw new RequestException("Idea-agreeParticipant : "+BADLY_FORMED);
				
				tmp = repository.getIdea(Integer.valueOf(request.getArgs().get(0)));
				tmp.agreeParticipant(repository.getStudent(request.getArgs().get(1)));
				query = new Query(Query.TOCLI); 
				query.addSentenceToQuery(IDEA+tmp.getTitle());
				query.addSentenceToQuery(repository.getStudent(request.getArgs().get(1)).getId()+" est désormais un participant validé !");
				break;
				
			case GET_CONTRIBUTORS:
				if(request.getArgs().size()!=1)
					throw new RequestException("Idea-getContributors : "+BADLY_FORMED);
				
				tmp = repository.getIdea(Integer.valueOf(request.getArgs().get(0)));
				Map<Student, Boolean> contributors = tmp.getContributors();
				query = new Query(Query.TOCLI);
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
	
	private Query parseRepositoryMethod() throws RequestException, RepositoryException{
		Query query = null;
		Idea tmp = null;
		
		switch(Action.getAction(request.getMethod())){
			case GET_ALL_IDEAS: case GET_ALL_IDEAS_FOR_ONE_STUDENT :
				if(request.getArgs().size()>1)
					throw new RequestException("Repository-getAllIdeas : "+BADLY_FORMED);
				
				if(request.getArgs().size()==0)
					query= allIdeas();
				else
					query = allIdeas(request.getArgs().get(0));
				break;
				
			case GET_IDEA:
				if(request.getArgs().size()!=1)
					throw new RequestException("Repository-getIdea : "+BADLY_FORMED);
				
				tmp = repository.getIdea(Integer.valueOf(request.getArgs().get(0)));
				query = new Query(Query.TOCLI);
				query.addSentenceToQuery(IDEA+tmp.getTitle());
				query.addSentenceToQuery(tmp.getDescription());
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
				
			default:
				throw new RequestException("Repository : La methode n'existe pas.");
		}
		
		return query;
	}
	
	private Query allIdeas(){
		List<Idea> ideas = repository.getAllIdeas();
		Query query = new Query(Query.TOCLI);
		for(int i=0;i<ideas.size();i++)
			query.addSentenceToQuery(ideas.get(i).toString());
		return query;
	}
	
	private Query allIdeas(String studentId){
		List<Idea> ideas = repository.getAllIdeas(repository.getStudent(studentId));
		Query query = new Query(Query.TOCLI);
		for(int i=0;i<ideas.size();i++)
			query.addSentenceToQuery(ideas.get(i).toString());
		return query;
	}
	
	private Query parseStudentMethod() throws RequestException{
		Query query = null;
		
		switch(Action.getAction(request.getMethod())){
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
