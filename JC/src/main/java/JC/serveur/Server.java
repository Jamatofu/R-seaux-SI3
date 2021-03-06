package JC.serveur;

import JC.serveur.data.Idea;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Permet de créer un serveur. Celui ci gère les connexions clients et les demandes faites par ces derniers
 * @author jamatofu on 03/05/17.
 */
public class Server {
    private ServerSocket server;
    private Socket clientSocket;
    private boolean isRunning;

    private static final int PORT = 4_000;

    public static List<Idea> ideaList = new ArrayList<Idea>() {{
        add(new Idea("Premiere idee de fou", "Idée 1"));
        add(new Idea("Deuxieme idee de fou", "Idée 2"));
        add(new Idea("Troisieme idee de fou", "Idée 3"));
        add(new Idea("Premier projet de fou", "Projet", false));
    }};

    /**
     * Permet de lancer le serveur
     */
    public void runServer() {
        try {
            this.server = new ServerSocket(PORT);
            this.isRunning = true;
            System.out.println("Création du serveur au port " + PORT + " réussie.");
        } catch (IOException e) {
            System.out.println("Impossible de lancer le serveur");
        }
    }

    /**
     * Permet à un client de se connecter à un serveur
     * Lorsqu'un client se connecte, on crée un thread pour traiter ses demandes
     */
    public void connectToClient() {
        System.out.println("Attente de client...");

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while(isRunning) {
                    try {
                        clientSocket = server.accept();
                        System.out.println("Le client " + clientSocket.getInetAddress().toString() + " s'est connecté.");
                        Thread t = new Thread(new ClientProcessor(clientSocket));
                        t.start();

                    } catch (IOException e) {
                        System.out.println("Le client ne peut pas se connecter");
                    }

                }

                try {
                    server.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    server = null;
                }
            }
        });

        t.start();
    }

    /**
     * Permet de fermer le client proprement
     */
    private void closeClient() {
        try {
            isRunning = false;
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
