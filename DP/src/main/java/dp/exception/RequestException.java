package dp.exception;

/**
 * 
 * Exception class for Request
 * @author David Sene && Pierre Rainero
 *
 */
public class RequestException extends Exception {
	/**
	 * Default serialID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Normal constructor
	 * @param message message of the exception
	 */
	public RequestException(String message){
		super(message);
	}

}
