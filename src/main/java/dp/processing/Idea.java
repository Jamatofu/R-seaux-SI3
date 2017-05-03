package dp.processing;

import java.util.HashMap;
import java.util.Map;

import dp.exception.ServerException;

public class Idea {
	
	private static final int MaxPerIdea = 3; 
	
	private Student admin; 
	
	private Map<Student, Boolean> participants;

	private String title;

	private String description;
	
	private State state = State.IDEA_STATE;
	
	
	public Idea (Student admin, String title, String description)
	{
		this.admin= admin;
		this.title= title;
		this.description = description;
		this.participants = new HashMap<Student, Boolean>();
	}
	
	
	public void addParicipant(Student student) throws ServerException
	{
		if(this.participants.size()==MaxPerIdea)
			throw new ServerException("Nombre maximale de participants par projet atteint");
		this.participants.put(student, false);
	}
	
	
	public void aggreParticipant(Student student) throws ServerException
	{
		if(participants.containsKey(student))
			participants.replace(student, true);
		else
			throw new ServerException("L'etudiant n'a pas demandé a participer au projet");
	}
	
	public void changeState(){
		state = State.PROJECT_STATE;
	}
	
	public void updateTitle(String title) {
		this.title = title;
	}


	public void updateDescription(String description) {
		this.description = description;
	}

		
	public Student getAdmin() {
		return admin;
	}
	
	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}
	
	
	public State getState() {
		return state;
	}


}
