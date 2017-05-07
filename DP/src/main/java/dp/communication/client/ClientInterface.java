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
	
	public Request start(){
		System.out.println("Commandes disponibles : ");
		listOfActions();
		Optional<Request> request = execute(readInput());
		if(request.isPresent())
			return request.get();
		else 
			return new Request();
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
		else if(Action.getAction(input)!=Action.NONE){
			Action actionToDo = Action.getAction(input);
			Request tmpRequest = new Request(actionToDo.getResource(), actionToDo);
			while(actionToDo.hasNextInstruction()){
				System.out.print(actionToDo.nextInstruction());
				tmpRequest.addArgs(readInput());
			}
			returnV = Optional.of(tmpRequest);
		}

		return returnV;
	}
	
	private String readInput() {
		System.out.print(">");
		return input.nextLine();
	}
}
