package JC.client;
import java.io.*;
import java.net.*;
/**
 * @author jamatofu on 28/04/17.
 */


public class MainClient {
    public static void main(String args[]) {
        ServerSocket echoServer = null;
        String line;
        DataInputStream is;
        PrintStream os;
        Socket clientSocket = null;
        try { echoServer = new ServerSocket(9999);}
        catch (IOException e) {System.out.println(e); }
        try {
            clientSocket = echoServer.accept();
            is = new DataInputStream(clientSocket.getInputStream());
            os = new PrintStream(clientSocket.getOutputStream());
            while (true) {
                line = is.readLine();
                os.println(line);
            }
        }
        catch (IOException e) {
            System.out.println(e);}
    } }

