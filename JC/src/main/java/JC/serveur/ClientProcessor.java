package JC.serveur;

import JC.communication.Query;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * S'occupe des demandes d'un seul client
 * @author jamatofu on 03/05/17.
 */
public class ClientProcessor implements Runnable {
    private Socket socket;
    private PrintWriter writer;
    private BufferedInputStream reader;
    private ObjectInputStream readerObject;

    public ClientProcessor(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        System.out.println("Traitement de la connexion client");

        while (!socket.isClosed()) {
            try {
                writer = new PrintWriter(socket.getOutputStream());
                reader = new BufferedInputStream(socket.getInputStream());
                readerObject = new ObjectInputStream(reader);

                System.out.println(((Query) readerObject.readObject()).toString());

                readerObject.close();
//                System.out.println(read());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                System.out.println("Requete mal faites");
            }
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

    private void write() {
        writer.write("Salut mon poulet <3");
        writer.flush();
    }
}
