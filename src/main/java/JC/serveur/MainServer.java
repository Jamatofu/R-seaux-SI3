package JC.serveur;

import JC.serveur.communication.Query;
import JC.serveur.communication.Response;
import JC.serveur.data.IdeaManager;
import JC.serveur.data.StudentManager;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author jamatofu on 28/04/17.
 */
public class MainServer {

    private StudentManager students = new StudentManager();
    private IdeaManager ideas = new IdeaManager();

    public static void main(String args[]) {
        ServerSocket myServer = null;
        String line;
        BufferedReader is;
        PrintStream os;
        Socket clientSocket = null;
        try { myServer = new ServerSocket(4000);}
        catch (IOException e) {System.out.println(e); }
        try {
            clientSocket = myServer.accept();
            os = new PrintStream(clientSocket.getOutputStream());
            is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            os.println("");
            while (true) {
                line = is.readLine();
                os.println(line);
                System.out.println(line);
            }
        }
        catch (IOException e) {
            System.out.println(e);}
    }
    private void readQuery(Query query){

    }
    private void createResponse(Response res){

    }
}
