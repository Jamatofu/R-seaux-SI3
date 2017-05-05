package dp.communication.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import dp.communication.Query;
import dp.communication.Request;

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
	
	/**
	 * Start the client
	 * @param args
	 */
	public static void main(String[] args){
		initVars();
		startCli();
	}
	
	/**
	 * Initialize needed variables
	 */
	private static void initVars(){
		clientSocket = null;
	}
	
	/**
	 * Start and run the client
	 */
	private static void startCli(){
		try {
			clientSocket = new Socket("192.168.1.14", 9002); //Adresse IP Pierre
			try {
				outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
				inputStream = new ObjectInputStream(clientSocket.getInputStream());
				
				Request req = new Request("Idea", "addContributor");
				req.addArgs("0");
				req.addArgs("ab012987");
				outputStream.writeObject(req);
				outputStream.flush();

				Query query = (Query)inputStream.readObject();
				System.out.println(query.toString());
				outputStream.close();
				inputStream.close();
				}
			catch (IOException | ClassNotFoundException e) {
				CLIENT_LOGGER.log(Level.SEVERE, e.toString(), e);
			}
		}
		catch (IOException e) {
			CLIENT_LOGGER.log(Level.SEVERE, e.toString(), e);
		}
	}
}
