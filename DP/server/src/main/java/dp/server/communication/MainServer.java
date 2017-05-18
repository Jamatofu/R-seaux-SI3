package dp.server.communication;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import dp.server.processing.Repository;


/**
 * 
 * Server launcher
 * @author David Sene && Pierre Rainero
 *
 */
public class MainServer {
	private static ServerSocket serverSocket;
	private static boolean listening;
	private static final Logger SERVER_LOGGER = Logger.getLogger(MainServer.class.getName());
	static final Repository repository = new Repository();
	private static List<ServerTread> threads = new ArrayList<>();
	
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
		listening = true;
		repository.fakeInit();
	}
	
	/**
	 * Start and run the server
	 * @throws ClassNotFoundException
	 * @throws  
	 */
	private static void startServer() throws ClassNotFoundException{

		try { 
			 serverSocket = new ServerSocket(9100); 
		}
		catch (IOException e){ 
			SERVER_LOGGER.log(Level.SEVERE, "Impossible d'écouter le port : 9100", e);
			System.exit(-1); 
		}
		
		Socket clientSocket = null;
		while (listening){
			try {
				clientSocket = serverSocket.accept(); 
			} catch (IOException e) {
				break;                                //Un thread a fermé le socket
			}
			
			ServerTread tread = new ServerTread(clientSocket,repository,serverSocket);			
			threads.add(tread);
			tread.start();
		}
		
		waitPendingthreads();
	}

	
	private static void waitPendingthreads() {
		for(ServerTread st : threads){
			if(st.isAlive())
				try {
					st.join();
				} 
			catch (InterruptedException e) {
				SERVER_LOGGER.log(Level.SEVERE, "Erreur a l'attente d'un thead", e);
			}
		}
	}
		
}
	
	

