package dp.communication.client;

import java.util.Optional;
import java.util.Scanner;

import dp.communication.Action;
import dp.communication.Request;

public class ClientInterface {
	private Scanner input;
	
	public ClientInterface() {
		input = new Scanner(System.in);	
	}
	
	public void start(Request requestToSend){
		System.out.println("Commandes disponibles : ");
		listOfActions();
		Optional<Request> request = execute(readInput());
		if(request.isPresent())
			requestToSend = request.get();
		else 
			start(requestToSend);
	}
	
	private void listOfActions(){
		for(Action action : Action.values())
			System.out.println("\t"+action.getString());
		System.out.println("\"q\" pour quitter !");
	}
	
	private Optional<Request> execute(String input){
		Optional<Request> returnV = Optional.empty();
		if(input.equals("q")){
			returnV = Optional.of(new Request()); 
		}
		else if(Action.valueOf(input)!=Action.NONE)
			returnV = Optional.of(new Request());

		return returnV;
	}
	
	private String readInput() {
		System.out.println(">");
		return input.nextLine();
	}
}
