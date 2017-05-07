package JC.client;
import JC.communication.Query;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jamatofu on 28/04/17.
 */


public class MainClient {

    public static void main(String[] args) {
        Socket smtpSocket = null; // le socket client
        PrintStream os = null; // output stream
        BufferedReader is = null; // input stream
        try {
            smtpSocket = new Socket("127.0.0.1", 4000);
            os = new PrintStream(smtpSocket.getOutputStream());
            is = new BufferedReader(new InputStreamReader(smtpSocket.getInputStream()));


            String line;
            line = is.readLine();
            System.out.println(line);

            os.println("Bonjour serveur");
            os.flush();

        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: hostname");
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: hostname");
        }

    }

    public Query parseQuery(String query_p){
        String[] parts = query_p.split(" ");
        Query query = null;
        String action = parts[0]; // "remove"

        String resourceType = parts[1]; // "student"
        List<String> parameters = new ArrayList<>();

        // add <student|idea> <id> <name>
        if(action.equals("add")) {
            parameters.add(parts[2]); // id
            parameters.add(parts[3]); // name
        }

        // remove <student|idea> <id>
        if(action.equals("remove")) {
            parameters.add(parts[2]); // "5"
        }

        query = new Query(action, resourceType, parameters);

        return query;
    }
}

