package dp.communication.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import dp.communication.Request;

/**
 * @author DAVID ET PIERRE
 */
public class MainClient {
	private Socket clientSocket = null;
	private static ObjectOutputStream outputStream;
	private static ObjectInputStream inputStream;
	
	public static void main(String[] args){
		startCli();
	}
	
	private static void startCli(){
		Socket myClient = null;
		try {
			myClient = new Socket("127.0.0.1", 9001);
			try {
				outputStream = new ObjectOutputStream(myClient.getOutputStream());
				outputStream.writeObject(new Request("Idea", "addParicipant"));
				outputStream.close();
				
				inputStream = new ObjectInputStream(myClient.getInputStream());
				inputStream.close();
				}
			catch (IOException e) {
				System.err.println(e);
				}
		}
		catch (IOException e) {
			System.out.println(e);
		}
	}
}
