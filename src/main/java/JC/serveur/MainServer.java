package JC.serveur;

import JC.serveur.communication.Response;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author jamatofu on 28/04/17.
 */
public class MainServer {
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
    }
    private void readQuery(String query){

    }
    private void createResponse(Response res){

    }
}
