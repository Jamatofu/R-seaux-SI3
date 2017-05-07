package JC.client;
import JC.communication.Query;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author jamatofu on 28/04/17.
 */


public class MainClient {

    private static final String ADRESSE_SERVEUR = "127.0.0.1";
    private static final int PORT = 4_000;

    public static void main(String[] args) {
        Socket smtpSocket; // le socket client
        PrintStream os; // output stream
        BufferedReader is = null; // input stream
        Scanner scanner = new Scanner(System.in);

        try {
            smtpSocket = new Socket(ADRESSE_SERVEUR, PORT);
            os = new PrintStream(smtpSocket.getOutputStream());
            is = new BufferedReader(new InputStreamReader(smtpSocket.getInputStream()));

            while(scanner.hasNext()) {
                os.print(scanner.nextLine());
                os.flush();
            }

            smtpSocket.close();
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: hostname");
        } catch (IOException e) {
            System.err.println("Connexion impossible au serveur " + ADRESSE_SERVEUR);
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

