package dp.exception;

/**
 * 
 * Exception class for Idea
 * @author David Sene && Pierre Rainero
 *
 */
public class IdeaException extends Exception {
	/**
	 * Default serialID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Normal constructor
	 * @param message message of the exception
	 */
	public IdeaException(String message){
		super(message);
	}
}
