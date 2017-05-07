package dp.common.communication;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Modelize a request from the client for the server
 * @author David Sene && Pierre Rainero
 *
 */
public class Request implements Serializable {
	/**
	 * Generated serialID
	 */
	private static final long serialVersionUID = -3812204783588350556L;
	
	private Resource resource;
	private Action method;
	private List<String> args;
	
	/**
	 * Default constructor (close request)
	 */
	public Request(){
		 this.resource = Resource.CLOSE;
	}
	 
	 /**
	  * Normal constructor
	  * @param resource kind of class to modify
	  * @param method method to use
	  */
	 public Request(Resource resource, Action method){
		 this.resource = resource;
		 this.method = method;
		 args = new ArrayList<>();
	 }
	 
	 /**
	  * Add an parameter for the method to use
	  * @param arg parameter to add (order is important)
	  */
	 public void addArgs(String arg){
		 args.add(arg);
	 }
	 
	 /**
	  * Consulting accessor of the resource
	  * @return request's resource
	  */
	 public Resource getResource(){
		 return resource;
	 }
	 
	 /**
	  * Consulting accessor of the method
	  * @return request's method
	  */
	 public Action getMethod() {
		return method;
	 }

	 /**
	  * Consulting accessor of the parameters
	  * @return request's parameters list
	  */
	 public List<String> getArgs() {
		return args;
	 }

}
