package dp.processing;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import dp.exception.IdeaException;

/**
 * 
 * Modelize a repository : a data center with all ideas and projects for a group of students
 * @author David Sene && Pierre Rainero
 *
 */
public class Repository {	
	private List<Idea> ideas;
	
	/**
	 * Default constructor
	 */
	public Repository(){
		ideas = new ArrayList<>();
	}
	
	/**
	 * Allows to add an idea
	 * @param idea idea to add to the repository
	 */
	public void addIdea(Idea idea){
		idea.setId(ideas.size());	
		ideas.add(idea);
	}
	
	/**
	 * Allows to remove an idea
	 * @param idea idea to remove from the repository
	 */
	public void removeIdea(Idea idea){
		ideas.remove(idea);
	}
	
	/**
	 * Allows to list all ideas for this repository
	 * @return list of all ideas
	 */
	public List<Idea> getAllIdeas() {
		return ideas;
	}
	
	/**
	 * Allows to list all ideas for a specific student
	 * @param student student to check 
	 * @return list of all ideas where the student is the admin
	 */
	public List<Idea> getAllIdeas(Student student) {
		List<Idea> ideasForTheStudent = new ArrayList<>();
		Iterator<Idea> iterator = ideas.stream().filter(idea -> idea.getAdmin().equals(student)).iterator();
		while(iterator.hasNext())
			ideasForTheStudent.add(iterator.next());
		return ideasForTheStudent;
	}
	
	/**
	 * Allows to list all contributor fir a specific idea
	 * @param ideaId idea id
	 * @return the list of contributors and theirs status
	 */
	public Map<Student, Boolean> getContributors(int ideaId){
		return ideas.get(ideaId).getContributors();
	}
	
	/**
	 * Allows to add a request for participation
	 * @param ideaId idea id
	 * @param student the applicant student
	 * @throws IdeaException
	 */
	public void paticipateToIdea(int ideaId, Student student) throws IdeaException{
		ideas.get(ideaId).addContributor(student);
	}
	
	/**
	 * Allows to accept a request for participation
	 * @param ideaId idea id
	 * @param student the applicant student
	 * @throws IdeaException
	 */
	public void acceptToIdea(int ideaId, Student student) throws IdeaException{
		ideas.get(ideaId).agreeParticipant(student);
	}
	
	/**
	 * Allows to know the description of an idea
	 * @param ideaId idea id
	 * @return the description
	 */
	public String getDescription(int ideaId){
		return ideas.get(ideaId).getDescription();
	}
	
	/**
	 * Allows to know the title of an idea
	 * @param ideaId idea id
	 * @return the title
	 */
	public String getTitle(int ideaId){
		return ideas.get(ideaId).getTitle();
	}

	/**
	 * Allows to modify the description of an idea
	 * @param ideaId idea id
	 * @param newDesc new description for the idea
	 */
	public void updateIdeaDescription(int ideaId, String newDesc){
		ideas.get(ideaId).setDescription(newDesc);
	}
	
	/**
	 * Allows to modify the title of an idea
	 * @param ideaId idea id
	 * @param newDesc new title for the idea
	 */
	public void updateIdeaTitle(int ideaId, String newTitle){
		ideas.get(ideaId).setTitle(newTitle);
	}
	
	/**
	 * Allows to make an idea become a project
	 * @param ideaId idea id
	 */
	public void changeIdeaState(int ideaId){
		ideas.get(ideaId).changeState();
	}
	
	

}
