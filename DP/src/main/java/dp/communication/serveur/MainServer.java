package dp.communication.serveur;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import dp.communication.Request;



public class MainServer {
	
	public static void main(String[] args) throws ClassNotFoundException{
		
		ServerSocket serverSocket = null ;
		Socket clientSocket = null;
		boolean listening = true;
		ObjectInputStream inputStream;
		ObjectOutputStream outputStream;
		Object text;
		
		try { 
			 serverSocket = new ServerSocket(9001); 
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
				System.out.println("Fin de lecture");
				Request rq = (Request)inputStream.readObject();
				System.out.println(rq.getResource());
				
				try {
					serverSocket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				try {
					text = inputStream.readObject();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
				
			} catch (IOException e) {
				 System.err.println("erreur");
				 System.exit(-1); 
			}
				 
		}
		

	} 
		
		
	}
	
	

