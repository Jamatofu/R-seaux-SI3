package dp.common.communication;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Modelize a query from the server for the client
 * @author David Sene && Pierre Rainero
 *
 */
public class Query implements Serializable {
	/**
	 * Generated serialID
	 */
	private static final long serialVersionUID = -4871192679406809330L;
	
	/**
	 * Static value to notify there is a normal query (not a "close server")
	 */
	public final static String TOCLI = "tocli";
	/**
	 * Static value to notify the student is now connected
	 */
	public final static String CONNECTED = "connected";
	/**
	 * Static value to notify the student isn't connected
	 */
	public final static String CONNECTION_FAIL = "fail";
	private String label;
	private List<String> structuredQuery;
	
	/**
	 * Normal constructor
	 * @param label label of the query
	 */
	public Query(String label){
		this.label = label;
		structuredQuery = new ArrayList<>();
	}
	
	/**
	  * Consulting accessor of the label
	  * @return request's label
	 */
	public String getLabel() {
		return label;
	}
	
	/**
	 * Add a sentence to the query
	 * @param sentence sentence to add
	 */
	public void addSentenceToQuery(String sentence){
		structuredQuery.add(sentence);
	}
	
	/** {@inheritDoc}
	 */
	@Override
	public String toString(){
		StringBuilder stB = new StringBuilder();
		for(int i=0;i<structuredQuery.size();i++)
			stB.append(structuredQuery.get(i)+"\n");
		
		return stB.toString();
	}
}
