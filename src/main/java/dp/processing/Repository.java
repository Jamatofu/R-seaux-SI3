package dp.processing;

import java.util.List;
import java.util.Map;

public class Repository {
	
	private List<Idea> ideas;
	
	public void addIdea(Idea idea){}
	
	public void removeIdea(Idea idea){}
	
	
	public List<Idea> getAllIdeas() {return null;}
	
	public List<Idea> getAllIdeas(Student student) {return null;}
	
	public Map<Student, Boolean> getParticipants(Idea idea){ return null;}
	
	public void paticiperToIdea(Idea idea , Student student){}
	
	
	public String getDescription(Idea idea){return null;}
	
	public String getTitle(Idea idea){return null;}
	
	
	public void updateIdeaName(Idea idea, String newName){};
	
	public void updateIdeaDescription(Idea idea, String newDesc){};
	
	public void updateIdeaTitle(Idea idea, String newTitle){};
	
	public void changeIdeaState(Idea idea){};
	
	

}
