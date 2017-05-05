package dp.processing;

import dp.communication.Query;
import dp.communication.Request;

/**
 * 
 * Allows to understand and execute a request
 * @author David Sene && Pierre Rainero
 *
 */
public class RequestOperator {
	private Request request;
	
	/**
	 * Normal constructor
	 */
	public RequestOperator(Request request){
		this.request = request;
	}
	
	public Query execute(){
		return new Query(Query.CLOSE);
	}
}
