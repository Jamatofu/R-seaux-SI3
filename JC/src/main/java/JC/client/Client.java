package JC.client;

import JC.communication.Action;
import JC.communication.Query;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.Scanner;

/**
 * Modélise le client
 */
public class Client {
    private static final String ADRESSE_SERVEUR = "127.0.0.1";
    private static final int PORT = 4_000;

    private Socket smtpSocket; // le socket client
    private PrintStream os; // output stream
    private BufferedReader is; // input stream
    private Scanner scanner;
    private ObjectOutputStream senderObject;

    private int idClient;

    public Client() {
        connectToServer();
    }

    private void connectToServer() {
        try {
            smtpSocket = new Socket(ADRESSE_SERVEUR, PORT);
            os = new PrintStream(smtpSocket.getOutputStream());
            is = new BufferedReader(new InputStreamReader(smtpSocket.getInputStream()));
            scanner = new Scanner(System.in);
            senderObject = new ObjectOutputStream(os);

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readEntry() {
        String entry;
        Optional<Query> query;

        try {
            do {
                System.out.println("\nTaper votre commande : ");
                entry = scanner.nextLine();

                if(Objects.equals(entry, "q")) {
                    break;
                }

                query = verifyEntry(entry);
                // TODO verifier que l'entrée est une action => la transformer => l'envoyer

                if(query.isPresent()) {
                    senderObject.writeObject(query.get());
                    os.flush();
                    System.out.println("Requete " + query.get().getAction() + " bien envoyée.");
                }

                // TODO ecrire la réponse
            } while(scanner.hasNext());
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Vous quittez le client");
    }

    public void registerId() {
        String entry = "";
        System.out.println("Entrer votre ID");
        while (!entry.matches("^[0-9]+$")) {
            entry = scanner.nextLine().trim();
        }

        idClient = Integer.parseInt(entry);
        System.out.println("Vous êtes connecté en tant que l'utilisateur " + idClient);
    }

    public void closeConnexion() {
        try {
            smtpSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Optional<Query> verifyEntry(String entry) {
        String arg[] = entry.split(" ");
        Action action;

        try {
            action = Action.valueOf(arg[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            action = null;
        }

        if(action != null && (arg.length - 1) == action.getNbArg()) {
           return Optional.of(new Query(action, Arrays.copyOfRange(arg, 1, arg.length)));
        }

        System.out.println("Commande non correcte.");
        return Optional.empty();
    }

    private void sendQuery() {

    }
}
