package dp.serveur.communication;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import dp.common.communication.Query;
import dp.common.communication.Request;
import dp.common.communication.Resource;
import dp.server.exception.IdeaException;
import dp.server.exception.RepositoryException;
import dp.server.exception.RequestException;
import dp.server.processing.Repository;


/**
 * 
 * Server launcher
 * @author David Sene && Pierre Rainero
 *
 */
public class ServerTread extends Thread {
	
	private  Socket clientSocket;
	private  ObjectInputStream inputStream;
	private  ObjectOutputStream outputStream;
	private  final Logger SERVER_LOGGER = Logger.getLogger(ServerTread.class.getName());
	private  Repository repository;
	ServerSocket socketServer;
	
	public ServerTread(Socket socket, Repository repository, ServerSocket socketServer){
		this.clientSocket = socket;
		this.repository = repository;
		this.socketServer = socketServer;
	}
	
	
	@Override
	public void run() {
		Query query = null;
		
		 try {
			outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
			inputStream = new ObjectInputStream(clientSocket.getInputStream());
			Request request = (Request)inputStream.readObject();
			RequestOperator reqOp = new RequestOperator(repository, request);
			query = reqOp.execute();
			sendQuery(query);
			
			if(query.getLabel().equals(Resource.CLOSE.toString()))
				socketServer.close(); //close() lance une exception puisque le serveur est blocké sur serverSocket.accept() 
							         //on catch l'exception pour pour arreter le serveur
			clientSocket.close();
							   
		} catch (IOException | NumberFormatException e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			SERVER_LOGGER.log(Level.SEVERE, sw.toString(), e);
			System.exit(-1); 
		} catch (RequestException | IdeaException | RepositoryException | ClassNotFoundException e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			SERVER_LOGGER.log(Level.WARNING, sw.toString(), e);
			
			query = new Query(Query.TOCLI);
			query.addSentenceToQuery(e.getMessage());
			try {
				sendQuery(query);
			} catch (IOException e1) {
				sw = new StringWriter();
				pw = new PrintWriter(sw);
				e.printStackTrace(pw);
				SERVER_LOGGER.log(Level.SEVERE, sw.toString(), e);
				System.exit(-1); 
			}
		}
	}


	
	
	/**
	 * Send the query to the client
	 * @param query query according to the resquest received
	 * @throws IOException
	 */
	private  void sendQuery(Query query) throws IOException{
		outputStream.writeObject(query);
		outputStream.flush();
		
		inputStream.close();
		outputStream.close();
	}
	
		
		
}