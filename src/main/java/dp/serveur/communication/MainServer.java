package dp.serveur.communication;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;



public class MainServer {
	
	public static void main(){
		
		ServerSocket serverSocket = null ;
		Socket clientSocket = null;
		boolean listening = true;
		DataInputStream stream;
		String text;
		
		try { 
			 serverSocket = new ServerSocket(4444); 
		}
		catch (IOException e){ 
			 System.err.println("Could not listen on port: 4444.");
			 System.exit(-1); 
		}
		while (listening){
			 try {
				clientSocket = serverSocket.accept();
				stream = new DataInputStream(clientSocket.getInputStream());
				text = stream.readUTF();
			} catch (IOException e) {
				 System.err.println("erreur");
				 System.exit(-1); 
			}
			
			 
		}
		
		
		try {
			serverSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	} 
		
		
	}
	
	

