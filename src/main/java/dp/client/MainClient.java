package dp.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

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
			myClient = new Socket("127.0.0.1", 4444);
			try {
				outputStream = new ObjectOutputStream(myClient.getOutputStream());
				inputStream = new ObjectInputStream(myClient.getInputStream());
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
