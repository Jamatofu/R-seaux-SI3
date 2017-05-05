package dp.communication;

import java.io.Serializable;
import java.util.List;

public class Query implements Serializable {
	/**
	 * Generated serialID
	 */
	private static final long serialVersionUID = -4871192679406809330L;
	
	public final static String TOCLI = "tocli";
	public final static String CLOSE = "close";
	private String label;
	private List<String> structuredQuery;
	
	public Query(String label){
		this.label = label;
	}
	
	public String getLabel() {
		return label;
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
