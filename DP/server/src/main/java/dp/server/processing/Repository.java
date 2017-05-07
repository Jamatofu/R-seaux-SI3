package dp.server.processing;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import dp.server.exception.IdeaException;
import dp.server.exception.RepositoryException;

/**
 * 
 * Modelize a repository : a data center with all ideas and projects for a group of students
 * @author David Sene && Pierre Rainero
 *
 */
public class Repository {	
	private List<Idea> ideas;
	private List<Student> students;
	
	/**
	 * Default constructor
	 */
	public Repository(){
		ideas = new ArrayList<>();
		students = new ArrayList<>();
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
	 * @param applicantId id of the operation applicant's
	 * @throws RepositoryException 
	 */
	public void removeIdea(int ideaId, String applicantId) throws RepositoryException{
		checkAdmin(ideaId, applicantId);
		removeIdea(ideas.get(ideaId));
	}
	
	private void checkAdmin(int ideaId, String applicantId) throws RepositoryException{
		if(!ideas.get(ideaId).getAdmin().getId().equals(applicantId))
			throw new RepositoryException("Vous (\""+applicantId+"\") n'est pas administrateur de cette idée.");
	}
	
	private void removeIdea(Idea ideaId){
		int i=ideas.indexOf(ideaId);
		ideas.remove(ideaId);
		for(;i<ideas.size();i++){
			ideas.get(i).setId(i);
		}
	}
	
	/**
	 * Allows to add a student
	 * @param idea student to add to the repository
	 */
	public void addStudent(Student student){
		students.add(student);
	}
	
	/**
	 * Allows to remove a student
	 * @param idea student to remove from the repository
	 */
	public void removeStudent(Student student){
		students.remove(student);
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
	 * Allows to list all ideas validate as projects
	 * @return list of all projects
	 */
	public List<Idea> getProjets(){
		List<Idea> projects = new ArrayList<>();
		Iterator<Idea> iterator = ideas.stream().filter(idea -> idea.getState()==State.PROJECT_STATE).iterator();
		while(iterator.hasNext())
			projects.add(iterator.next());
		return projects;
	}

	/**
	 * Generate some datas to test
	 */
	public void fakeInit(){
		students.add(new Student("ty852456", "datPassWd"));
		students.add(new Student("ab012987", "azerty123"));
		
		ideas.add(new Idea(students.get(0), "L'idée Noumero 1", "Mais pas la dernière !"));
	}
	
	/**
	 * Allows to get an idea from her id
	 * @param ideaId id of the idea
	 * @return the idea object
	 */
	public Idea getIdea(int ideaId){
		return ideas.get(ideaId);
	}
	
	/**
	 * Allows to get a student from his id
	 * @param id id of the student
	 * @return the student object
	 * @throws RepositoryException 
	 */
	public Student getStudent(String id) throws RepositoryException{
		Student student = null;
		try{
			student = students.stream().filter(etu -> etu.getId().equals(id)).findFirst().get();
		}catch(NoSuchElementException e){
			throw new RepositoryException("Il n'existe pas d'étudiant avec l'id : "+id);
		}
		return student;
	}
	
	/**
	 * Allows to change the state of a specific idea
	 * @param ideaId id of the idea
	 * @param applicantId id of the applicant student (should be admin)
	 * @throws RepositoryException
	 */
	public void changeState(int ideaId, String applicantId) throws RepositoryException{
		checkAdmin(ideaId, applicantId);
		ideas.get(ideaId).changeState();
	}
	
	/**
	 * Allows to change the title of a specific idea
	 * @param ideaId id of the idea
	 * @param applicantId id of the applicant student (should be admin)
	 * @param newTitle new title for the idea
	 * @throws RepositoryException
	 */
	public void setTitle(int ideaId, String applicantId, String newTitle) throws RepositoryException{
		checkAdmin(ideaId, applicantId);
		ideas.get(ideaId).setTitle(newTitle);
	}
	
	/**
	 * Allows to change the description of a specific idea
	 * @param ideaId id of the idea
	 * @param applicantId id of the applicant student (should be admin)
	 * @param newDescription new description for the idea
	 * @throws RepositoryException
	 */
	public void setDescription(int ideaId, String applicantId, String newDescription) throws RepositoryException{
		checkAdmin(ideaId, applicantId);
		ideas.get(ideaId).setDescription(newDescription);
	}
	
	/**
	 * Allows to accept a contributor to a specific idea
	 * @param ideaId id of the idea
	 * @param applicantId id of the applicant student (should be admin)
	 * @param idToAgree id of the student to agree
	 * @throws RepositoryException
	 * @throws IdeaException
	 */
	public void agreeContributor(int ideaId, String applicantId, String idToAgree) throws RepositoryException, IdeaException{
		checkAdmin(ideaId, applicantId);
		ideas.get(ideaId).agreeParticipant(getStudent(idToAgree));
	}
}
