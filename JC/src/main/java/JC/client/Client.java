package JC.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Objects;
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
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readEntry() {
        String entry;
        do {
            System.out.println("\nTaper votre commande : ");
            entry = scanner.nextLine();

            if(Objects.equals(entry, "q")) {
                break;
            }

            // TODO verifier que l'entrée est une action => la transformer => l'envoyer

            os.print(entry);
            os.flush();

            // TODO ecrire la réponse
        } while(scanner.hasNext());

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

    private void sendQuery() {

    }
}
