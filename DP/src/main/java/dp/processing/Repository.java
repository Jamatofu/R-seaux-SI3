package dp.processing;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
	 */
	public void removeIdea(Idea idea){
		ideas.remove(idea);
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
	 */
	public Student getStudent(String id){
		return students.stream().filter(student -> student.getId().equals(id)).findFirst().get();
	}
}
