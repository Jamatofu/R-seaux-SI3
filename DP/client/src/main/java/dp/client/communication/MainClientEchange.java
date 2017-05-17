package dp.client.communication;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Client Sceance noté
 * Client launcher
 * @author David Sene SI3
 *
 */
public class ClientSceanceNote {
	private static Socket clientSocket = null;
	private static PrintWriter outputStream;
	private static InputStreamReader inputStream;
	private static BufferedReader reader;
	
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
			clientSocket = new Socket("10.212.99.187", 15042); 
			Scanner sc = new Scanner(System.in);
			while(true){
					outputStream = new PrintWriter(clientSocket.getOutputStream());
					System.out.println(">");
					String req = sc.nextLine();
					if(req.equals("q")) break;
					outputStream.println(req);
					outputStream.flush();
					
					inputStream = new InputStreamReader(clientSocket.getInputStream());
					reader = new BufferedReader(inputStream);
					StringBuilder sb = new StringBuilder();
					String s;
					
						s = reader.readLine();
						while ( s!= null) {
							s = reader.readLine();
							sb.append(s);
						}
						System.out.println(sb.toString());
					}	
			outputStream.close();
			inputStream.close();
			reader.close();
		}	
		catch (IOException e) {
					e.printStackTrace();
			}	
	}
}

		


