package dp.client.communication;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import dp.common.communication.Query;
import dp.common.communication.Request;
import dp.common.communication.Resource;

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
	private static boolean connected;
	
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
		connected = false;
	}
	
	/**
	 * Start and run the client
	 */
	private static void startCli(){
		try {
			clientSocket = new Socket("127.0.0.1", 9100); /** Adresse de Pierre **/
			try {
				outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
				inputStream = new ObjectInputStream(clientSocket.getInputStream());
				Request req;
				
				if(!connected)
					req = clientCmd.connect();
				else
					req = clientCmd.start();
				outputStream.writeObject(req);
				outputStream.flush();

				Query query = (Query)inputStream.readObject();
				System.out.println("---------------\n"+query.toString());
				
				outputStream.close();
				inputStream.close();
				
				labelManagement(query.getLabel());
				
				Thread.sleep(1500); /** Pour laisser le temps de bien voir la réponse **/
			}
			 	
			catch (IOException | ClassNotFoundException | InterruptedException e) {
				CLIENT_LOGGER.log(Level.SEVERE, e.toString(), e);
			}
		}
		catch (IOException e) {
			CLIENT_LOGGER.log(Level.SEVERE, e.toString(), e);
		}
	}
	
	/**
	 * Manage the label (connection/close)
	 * @param label query label to manage
	 */
	private static void labelManagement(String label){
		if(label.equals(Resource.CLOSE.toString()))
			talk = false;
		
		if(label.equals(Query.CONNECTED))
			connected = true;
	}
}
