package JC.client;

import JC.communication.Action;
import JC.communication.Query;
import JC.serveur.Reply;

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
    private BufferedInputStream is; // input stream
    private Scanner scanner;

    private ObjectInputStream readerObject;
    private ObjectOutputStream senderObject;

    private int idClient;

    public Client() {
        connectToServer();
    }

    /**
     * Se connecte au serveur
     */
    private void connectToServer() {
        try {
            smtpSocket = new Socket(ADRESSE_SERVEUR, PORT);

            os = new PrintStream(smtpSocket.getOutputStream());
            is = new BufferedInputStream(smtpSocket.getInputStream());

            scanner = new Scanner(System.in);

            senderObject = new ObjectOutputStream(os);
            readerObject = new ObjectInputStream(is);

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Lis l'entrée de l'utilisateur et envoie au serveur la requête
     * Affiche la réponse du serveur
     */
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

                if(query.isPresent()) {
                    senderObject.writeObject(query.get());
                    os.flush();
                }

                System.out.println((Reply) readerObject.readObject());

            } while(scanner.hasNext());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("Vous quittez le client");
    }

    /**
     * Permet d'enregistrer d'ID du client
     */
    public void registerId() {
        String entry = "";
        System.out.println("Entrer votre ID");
        while (!entry.matches("^[0-9]+$")) {
            entry = scanner.nextLine().trim();
        }

        idClient = Integer.parseInt(entry);
        System.out.println("Vous êtes connecté en tant que l'utilisateur " + idClient);
    }

    /**
     * Ferme la connexion
     */
    public void closeConnexion() {
        try {
            smtpSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Vérifie que la chaine entrée par l'utilisateur correspond à une commande bie nformée
     * @param entry la chaine à examiner
     * @return un objet Query bien formé
     */
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
}
