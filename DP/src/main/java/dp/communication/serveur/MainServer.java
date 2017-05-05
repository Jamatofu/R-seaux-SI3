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

import dp.communication.Request;


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
	
	public static void main(String[] args) throws ClassNotFoundException{
		initVars();
		startServer();
	}
	
	private static void initVars(){
		serverSocket = null ;
		clientSocket = null;
		listening = true;
	}
	
	private static void startServer() throws ClassNotFoundException{
		try { 
			 serverSocket = new ServerSocket(9001); 
		}
		catch (IOException e){ 
			SERVER_LOGGER.log(Level.SEVERE, "Impossible d'écouter le port : 9001", e);
			System.exit(-1); 
		}
		while (listening){
				System.out.println("listeniing on 4444");
			 try {
				clientSocket = serverSocket.accept();
				System.out.println("accepted");
				inputStream = new ObjectInputStream(clientSocket.getInputStream());
				System.out.println("Fin de lecture");
				Request rq = (Request)inputStream.readObject();
				System.out.println(rq.getResource());
				
				outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
				closeServer();
				
			} catch (IOException e) {
				StringWriter sw = new StringWriter();
				PrintWriter pw = new PrintWriter(sw);
				e.printStackTrace(pw);
				SERVER_LOGGER.log(Level.SEVERE, sw.toString(), e);
				System.exit(-1); 
			}
				 
		}
	}
		
	private static void closeServer() throws IOException{
		serverSocket.close();
		listening = false;
	}
		
}
	
	

