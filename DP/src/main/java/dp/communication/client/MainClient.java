package dp.communication.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import dp.communication.Query;
import dp.communication.Request;
import dp.communication.Resource;

/**
 * 
 * Client launcher
 * @author David Sene && Pierre Rainero
 *
 */
public class MainClient {
	private static Socket clientSocket = null;
	private static ObjectOutputStream outputStream;
	private static ObjectInputStream inputStream;
	private static final Logger CLIENT_LOGGER = Logger.getLogger(MainClient.class.getName());
	private static ClientInterface clientCmd;
	private static boolean talk;
	
	/**
	 * Start the client
	 * @param args
	 */
	public static void main(String[] args){
		initVars();
		while(talk)
			startCli();
	}
	
	/**
	 * Initialize needed variables
	 */
	private static void initVars(){
		clientSocket = null;
		clientCmd = new ClientInterface();
		talk = true;
	}
	
	/**
	 * Start and run the client
	 */
	private static void startCli(){
		try {
			clientSocket = new Socket("192.168.1.14", 9003); //Adresse IP Pierre
			try {
				outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
				inputStream = new ObjectInputStream(clientSocket.getInputStream());
				
				Request req = clientCmd.start();
				outputStream.writeObject(req);
				outputStream.flush();

				Query query = (Query)inputStream.readObject();
				System.out.println("---------------\n"+query.toString());
				
				outputStream.close();
				inputStream.close();
				
				Thread.sleep(2000); //Pour laisser le temps de voir la réponse
				
				if(query.getLabel().equals(Resource.CLOSE.toString()))
					talk = false;
				}
			 	
			catch (IOException | ClassNotFoundException | InterruptedException e) {
				CLIENT_LOGGER.log(Level.SEVERE, e.toString(), e);
			}
		}
		catch (IOException e) {
			CLIENT_LOGGER.log(Level.SEVERE, e.toString(), e);
		}
	}
}
