package dp.serveur.communication;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import dp.serveur.processing.Idea;



public class MainServer {
	
	public static void main(String[] args){
		
		ServerSocket serverSocket = null ;
		Socket clientSocket = null;
		boolean listening = true;
		ObjectInputStream inputStream;
		ObjectOutputStream outputStream;
		Object text;
		
		try { 
			 serverSocket = new ServerSocket(4444); 
		}
		catch (IOException e){ 
			 System.err.println("Could not listen on port: 4444.");
			 System.exit(-1); 
		}
		while (listening){
				System.out.println("listeniing on 4444");
			 try {
				clientSocket = serverSocket.accept();
				System.out.println("accepted");
				inputStream = new ObjectInputStream(clientSocket.getInputStream());
				try {
					text = inputStream.readObject();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
				outputStream.writeObject(new Idea(null, "title", "description"));
				
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
	
	

