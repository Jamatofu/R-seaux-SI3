package dp.common.exception;

/**
 * 
 * Exception class for Repository
 * @author David Sene && Pierre Rainero
 *
 */
public class RepositoryException extends Exception {
	/**
	 * Default serialID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Normal constructor
	 * @param message message of the exception
	 */
	public RepositoryException(String message){
		super(message);
	}
}
