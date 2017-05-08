package JC.serveur;

import JC.communication.Query;
import JC.serveur.data.Idea;

import java.io.*;
import java.net.Socket;

/**
 * S'occupe des demandes d'un seul client
 * @author jamatofu on 03/05/17.
 */
public class ClientProcessor implements Runnable {
    private Socket socket;

    private PrintStream writer;
    private BufferedInputStream reader;

    private ObjectInputStream readerObject;
    private ObjectOutputStream senderObject;

    public ClientProcessor(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        System.out.println("Traitement de la connexion client");

        while (!socket.isClosed() || !socket.isConnected()) {
            try {
                writer = new PrintStream(socket.getOutputStream());
                reader = new BufferedInputStream(socket.getInputStream());

                readerObject = new ObjectInputStream(reader);
                senderObject = new ObjectOutputStream(writer);

                Reply reply = executeQuery((Query) readerObject.readObject());
                senderObject.writeObject(reply);
                writer.flush();

                readerObject.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                System.out.println("Requete mal faites");
            }
        }

        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Permet de lire la demande d'un client
     * @return la demande du client
     * @throws IOException
     */
    private String read() throws IOException {
        String response= "";
        int stream;
        byte[] b = new byte[1024];

        stream = reader.read(b);
        if(stream != -1) {
            response = new String(b, 0, stream);
        } else {
            System.out.println("Fermeture de la connexion avec le client " + socket.getInetAddress());
            socket.close();
        }
        return response;
    }

    private Reply executeQuery(Query query) {
        String[] param = query.getParameters();
        Reply reply = new Reply();

        switch (query.getAction()) {
            case AJOUTER_IDEE:
                Server.ideaList.add(new Idea(param[0], param[1]));
                System.out.println("Le client " + socket.getInetAddress() + " a ajouté une idée");
                reply.addSentence("Votre idée a bien été ajoutée.");
                break;
            case GET_ALL_IDEA:
                System.out.println("Le client " + socket.getInetAddress() + " récupère toutes les idées.");
                for(Idea idea : Server.ideaList) {
                    reply.addSentence(idea.toString());
                }
                break;
            default:
                reply.addSentence("Votre requête est incorrecte.");
                break;
        }

        return reply;
    }
}
