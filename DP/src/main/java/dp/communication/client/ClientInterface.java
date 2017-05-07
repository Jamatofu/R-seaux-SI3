package dp.communication.client;

import java.util.Optional;
import java.util.Scanner;

import dp.communication.Action;
import dp.communication.Request;
import dp.communication.Resource;

/**
 * 
 * Allows to communicate (with the terminal) with the user
 * @author David Sene && Pierre Rainero
 *
 */
public class ClientInterface {
	private Scanner input;
	private String studentConnected;
	
	/**
	 * Default constructor
	 */
	public ClientInterface() {
		input = new Scanner(System.in);	
	}
	
	/**
	 * Try to connect the use to the server
	 * @return connection request
	 */
	public Request connect(){
		Request firstCo = new Request(Resource.CONNECTION,null);
		Optional<Request> qTest = Optional.empty();
		qToQuitDisplay();
		
		System.out.println("Veuillez vous connecter :\nID (chaine) :");
		studentConnected = readInput();
		qTest = quitRequest(studentConnected);
		
		if(!qTest.isPresent()){
			firstCo.addArgs(studentConnected);
			System.out.println("Mot de passe (chaine) :");
			String mdp = readInput();
			
			qTest = quitRequest(mdp);
			if(!qTest.isPresent()){
				firstCo.addArgs(mdp);
				qTest = quitRequest(studentConnected);
				return firstCo;
			}
		}
		
		return qTest.get();
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
		qToQuitDisplay();
	}
	
	/**
	 * Display possibility to quit
	 */
	private void qToQuitDisplay(){
		System.out.println("\"q\" pour quitter !");
	}
	
	/**
	 * Catch "q" touch
	 * @param input input write by the user
	 * @return Optional.empty if the user doesn't write "q", the quitRequest otherwise
	 */
	private Optional<Request> quitRequest(String input){
		Optional<Request> returnV = Optional.empty();
		if(input.equals("q"))
			returnV = Optional.of(new Request());
		return returnV;
	}
	
	/**
	 * Create the correct request according to the user's choice
	 * @param input action choiced
	 * @return request to send to the server
	 */
	private Optional<Request> execute(String input){
		Optional<Request> returnV = quitRequest(input);
		if(!returnV.isPresent() && Action.getAction(input)!=Action.NONE){
			Action actionToDo = Action.getAction(input);
			Request tmpRequest = new Request(actionToDo.getResource(), actionToDo);
			while(actionToDo.hasNextInstruction()){
				String instruction = actionToDo.nextInstruction();
				if(instruction.equals("A"))
					tmpRequest.addArgs(studentConnected);
				else{
					System.out.print(instruction);
					tmpRequest.addArgs(readInput());
				}
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
