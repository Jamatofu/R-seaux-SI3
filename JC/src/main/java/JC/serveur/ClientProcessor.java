package JC.serveur;

import java.io.BufferedInputStream;
import java.io.IOException;
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

                System.out.println(read());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Permet de lire la demande d'un client
     * @return la demande du client
     * @throws IOException
     */
    private String read() throws IOException {
        String response = "";
        int stream;
        byte[] b = new byte[4096];
        stream = reader.read(b);
        response = new String(b, 0, stream);
        return response;
    }

    private void write() {
        writer.write("Salut mon poulet <3");
        writer.flush();
    }
}
