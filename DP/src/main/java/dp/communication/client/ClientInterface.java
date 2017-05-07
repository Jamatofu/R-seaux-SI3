package dp.communication.client;

import java.util.Optional;
import java.util.Scanner;

import dp.communication.Action;
import dp.communication.Request;

/**
 * 
 * Allows to communicate (with the terminal) with the user
 * @author PierreRainero
 *
 */
public class ClientInterface {
	private Scanner input;
	
	/**
	 * Default constructor
	 */
	public ClientInterface() {
		input = new Scanner(System.in);	
	}
	
	/**
	 * Start the communication between client and user
	 * @return request to send to the server
	 */
	public Request start(){
		System.out.println("Commandes disponibles : ");
		listOfActions();
		Optional<Request> request = execute(readInput());
		if(request.isPresent())
			return request.get();
		else 
			return new Request();
	}
	
	/**
	 * List all the possible actions
	 */
	private void listOfActions(){
		for(Action action : Action.values())
			System.out.println("\t"+action.getString());
		System.out.println("\"q\" pour quitter !");
	}
	
	/**
	 * Create the correct request according to the user's choice
	 * @param input action choiced
	 * @return request to send to the server
	 */
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
			actionToDo.resetInstructions();
			returnV = Optional.of(tmpRequest);
		}

		return returnV;
	}
	
	/**
	 * Read an input (line write by the user)
	 * @return line write by the user
	 */
	private String readInput() {
		System.out.print(">");
		return input.nextLine();
	}
}
