package dp.communication.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

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
	
	public static void main(String[] args){
		initVars();
		startCli();
	}
	
	private static void initVars(){
		clientSocket = null;
	}
	
	private static void startCli(){
		Socket myClient = null;
		try {
			myClient = new Socket("192.168.1.14", 9001);
			try {
				outputStream = new ObjectOutputStream(myClient.getOutputStream());
				outputStream.writeObject(new Request("Idea", "addParicipant"));
				outputStream.close();
				
				inputStream = new ObjectInputStream(myClient.getInputStream());
				inputStream.close();
				}
			catch (IOException e) {
				CLIENT_LOGGER.log(Level.SEVERE, e.toString(), e);
			}
		}
		catch (IOException e) {
			CLIENT_LOGGER.log(Level.SEVERE, e.toString(), e);
		}
	}
}
