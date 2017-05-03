package dp.communication;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Request implements Serializable {
	 private String resource;
	 private String method;
	 private List<String> args;
	 
	 public Request(String resource, String method){
		 this.resource = resource;
		 this.method = method;
		 args = new ArrayList<>();
	 }
	 
	 public void addArgs(String arg){
		 args.add(arg);
	 }
	 
	 public String getResource(){
		 return resource;
	 }
}
