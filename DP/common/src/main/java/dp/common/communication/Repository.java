package dp.common.communication;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import dp.common.exception.IdeaException;
import dp.common.exception.RepositoryException;


public interface Repository extends Remote {
	
	
	/**
	 * Allows to add an idea
	 * @param idea idea to add to the repository
	 */
	public void addIdea(Idea idea) throws RemoteException;
	
	/**
	 * Allows to remove an idea
	 * @param idea idea to remove from the repository
	 * @param applicantId id of the operation applicant's
	 * @throws RepositoryException 
	 */
	public void removeIdea(int ideaId, String applicantId) throws RepositoryException, RemoteException;
	
	
	
	/**
	 * Allows to add a student
	 * @param idea student to add to the repository
	 */
	public void addStudent(Student student)throws RemoteException;	
	
	/**
	 * Allows to remove a student
	 * @param idea student to remove from the repository
	 */
	public void removeStudent(Student student)throws RemoteException;
	
	
	/**
	 * Allows to list all ideas for this repository
	 * @return list of all ideas
	 */
	public List<Idea> getAllIdeas()throws RemoteException;
	
	
	/**
	 * Allows to list all ideas for a specific student
	 * @param student student to check 
	 * @return list of all ideas where the student is the admin
	 */
	public List<Idea> getAllIdeas(Student student)throws RemoteException;
	
	/**
	 * Allows to list all ideas validate as projects
	 * @return list of all projects
	 */
	public List<Idea> getProjets()throws RemoteException;

	
	/**
	 * Allows to get an idea from her id
	 * @param ideaId id of the idea
	 * @return the idea object
	 */
	public Idea getIdea(int ideaId)throws RemoteException;
	
	/**
	 * Allows to get a student from his id
	 * @param id id of the student
	 * @return the student object
	 * @throws RepositoryException 
	 */
	public Student getStudent(String id) throws RepositoryException,RemoteException;
	
	/**
	 * Allows to change the state of a specific idea
	 * @param ideaId id of the idea
	 * @param applicantId id of the applicant student (should be admin)
	 * @throws RepositoryException
	 */
	public void changeState(int ideaId, String applicantId) throws RepositoryException,RemoteException;
	
	/**
	 * Allows to change the title of a specific idea
	 * @param ideaId id of the idea
	 * @param applicantId id of the applicant student (should be admin)
	 * @param newTitle new title for the idea
	 * @throws RepositoryException
	 */
	public void setTitle(int ideaId, String applicantId, String newTitle) throws RepositoryException,RemoteException;
	
	/**
	 * Allows to change the description of a specific idea
	 * @param ideaId id of the idea
	 * @param applicantId id of the applicant student (should be admin)
	 * @param newDescription new description for the idea
	 * @throws RepositoryException
	 */
	public void setDescription(int ideaId, String applicantId, String newDescription) throws RepositoryException,RemoteException;
	
	/**
	 * Allows to accept a contributor to a specific idea
	 * @param ideaId id of the idea
	 * @param applicantId id of the applicant student (should be admin)
	 * @param idToAgree id of the student to agree
	 * @throws RepositoryException
	 * @throws IdeaException
	 */
	public void agreeContributor(int ideaId, String applicantId, String idToAgree) throws RepositoryException, IdeaException,RemoteException;

}
