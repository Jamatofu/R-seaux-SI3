package dp.communication.serveur;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import dp.communication.Query;
import dp.communication.Request;
import dp.exception.IdeaException;
import dp.exception.RepositoryException;
import dp.exception.RequestException;
import dp.processing.Repository;
import dp.processing.RequestOperator;


/**
 * 
 * Server launcher
 * @author David Sene && Pierre Rainero
 *
 */
public class MainServer {
	private static ServerSocket serverSocket;
	private static Socket clientSocket;
	private static boolean listening;
	private static ObjectInputStream inputStream;
	private static ObjectOutputStream outputStream;
	private static final Logger SERVER_LOGGER = Logger.getLogger(MainServer.class.getName());
	private static Repository repository;
	
	/**
	 * Start the client
	 * @param args
	 * @throws ClassNotFoundException
	 */
	public static void main(String[] args) throws ClassNotFoundException{
		initVars();
		startServer();
	}
	
	/**
	 * Initialize needed variables
	 */
	private static void initVars(){
		serverSocket = null ;
		clientSocket = null;
		listening = true;
		repository = new Repository();
		repository.fakeInit();
	}
	
	/**
	 * Start and run the server
	 * @throws ClassNotFoundException
	 */
	private static void startServer() throws ClassNotFoundException{
		try { 
			 serverSocket = new ServerSocket(9003); 
		}
		catch (IOException e){ 
			SERVER_LOGGER.log(Level.SEVERE, "Impossible d'écouter le port : 9003", e);
			System.exit(-1); 
		}
		while (listening){
			 try {
				clientSocket = serverSocket.accept();
				outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
				inputStream = new ObjectInputStream(clientSocket.getInputStream());
				
				Request request = (Request)inputStream.readObject();
				RequestOperator reqOp = new RequestOperator(repository, request);
				Query query = reqOp.execute();
				
				outputStream.writeObject(query);
				outputStream.flush();
				
				inputStream.close();
				outputStream.close();
				
				if(query.getLabel().equals(Query.CLOSE))
					closeServer();
				
			} catch (IOException | NumberFormatException e) {
				StringWriter sw = new StringWriter();
				PrintWriter pw = new PrintWriter(sw);
				e.printStackTrace(pw);
				SERVER_LOGGER.log(Level.SEVERE, sw.toString(), e);
				System.exit(-1); 
			} catch (RequestException | IdeaException | RepositoryException e) {
				StringWriter sw = new StringWriter();
				PrintWriter pw = new PrintWriter(sw);
				e.printStackTrace(pw);
				SERVER_LOGGER.log(Level.WARNING, sw.toString(), e);
			}
				 
		}
	}
	
	/**
	 * Close the client and server socket
	 * @throws IOException
	 */
	private static void closeServer() throws IOException{
		clientSocket.close();
		serverSocket.close();
		listening = false;
	}
		
}
	
	

