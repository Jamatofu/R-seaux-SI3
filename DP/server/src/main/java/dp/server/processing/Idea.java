package dp.server.processing;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import dp.server.exception.IdeaException;;

/**
 * 
 * Modelize an idea
 * @author David Sene && Pierre Rainero
 *
 */
public class Idea {
	private int id;
	private static final int MaxPerIdea = 3; 
	private Student admin; 
	private Map<Student, Boolean> contributors;
	private String title;
	private String description;
	private State state = State.IDEA_STATE;
	
	/**
	 * Normal constructor
	 * @param admin the author and admin of the idea
	 * @param title the title of the idea
	 * @param description the description of the idea
	 */
	public Idea (Student admin, String title, String description){
		this.admin= admin;
		this.title= title;
		this.description = description;
		this.contributors = new HashMap<Student, Boolean>();
	}
	
	/**
	 * Add a contributor (not validated by the admin) if the maximum number of contributors has not been reached
	 * @param student the new contributor
	 * @throws IdeaException
	 */
	public void addContributor(Student student) throws IdeaException{
		if(getValidatedContributorsSize()<MaxPerIdea)
			contributors.put(student, false);
		else
			throw new IdeaException("Le nombre maximale de participants pour ce projet a été atteint.");
	}
	
	private int getValidatedContributorsSize(){
		int validatedContributor = 0;
		Iterator<Entry<Student, Boolean>> it = contributors.entrySet().stream().filter(map -> map.getValue()==true).iterator();
		while(it.hasNext()){
			validatedContributor++;
			it.next();
		}
		return validatedContributor;
	}
	
	/**
	 * Validate the participation for a contributor
	 * @param student the contributor to validate
	 * @throws IdeaException
	 */
	public void agreeParticipant(Student student) throws IdeaException{
		if(contributors.containsKey(student))
			contributors.replace(student, true);
		else
			throw new IdeaException("L'étudiant n'a pas demandé a participer au projet.");
	}
	
	/**
	 * Switch the idea state to "project"
	 */
	public void changeState(){
		state = State.PROJECT_STATE;
	}

	/**
	 * Consulting accessor of the admin
	 * @return current admin
	 */
	public Student getAdmin() {
		return admin;
	}
	
	/**
	 * Consulting accessor of the title
	 * @return current title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Consulting accessor of the description
	 * @return current description
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Consulting accessor of the idea state
	 * @return current state
	 */
	public State getState() {
		return state;
	}

    /**
	 * Consulting accessor of the id
	 * @return current id
     */
	public int getId() {
		return id;
	}
	
	/**
	 * Consulting accessor of contributors
	 * @return currents contributors
	 */
	public Map<Student, Boolean> getContributors() {
		return contributors;
	}
	
	/**
	 * Change accessor of the id
	 * @param id new id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Change accessor of the title
	 * @param title new title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Change accessor of the description
	 * @param description new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/** {@inheritDoc}
	 */
	@Override
	public String toString(){
		return "____________\nID : "+id + "\n" + title + "\nParticipants : "+getValidatedContributorsSize()+"/"+MaxPerIdea+"\n____________\n";
	}
}
