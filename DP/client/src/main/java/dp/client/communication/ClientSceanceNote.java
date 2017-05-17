package dp.client.communication;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
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
public class MainClientEchange {
	private static Socket clientSocket = null;
	private static PrintWriter outputStream;
	private static InputStreamReader inputStream;
	private static BufferedReader reader;
	private static final Logger CLIENT_LOGGER = Logger.getLogger(MainClientEchange.class.getName());
	
	
	/**
	 * Start the client
	 * @param args
	 */
	public static void main(String[] args){
		
			startCli();
	}
	

	/**
	 * Start and run the client
	 */
	private static void startCli(){
		try {
			clientSocket = new Socket("10.212.99.187", 15042); /** Adresse de Pierre **/
			try {
				String req = "GET_IDEAS";
				outputStream = new PrintWriter(clientSocket.getOutputStream());
				outputStream.print(req);
				outputStream.flush();
				
				inputStream = new InputStreamReader(clientSocket.getInputStream());
				reader = new BufferedReader(inputStream);
				StringBuilder sb = new StringBuilder();
				String s = reader.readLine();
				while ( s!= null) {
					s = reader.readLine();
					sb.append(s);
				}
				System.out.println(sb.toString());
				outputStream.close();
				inputStream.close();
				reader.close();
			}	
			catch (IOException  e) {
				CLIENT_LOGGER.log(Level.SEVERE, e.toString(), e);
			}
		}
		catch (IOException e) {
			CLIENT_LOGGER.log(Level.SEVERE, e.toString(), e);
		}
	}
	
	
}
