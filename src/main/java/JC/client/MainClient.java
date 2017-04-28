package JC.client;
import java.io.*;
import java.net.*;
/**
 * @author jamatofu on 28/04/17.
 */


public class MainClient {

    public static void main(String[] args) {
        Socket smtpSocket = null; // le socket client
        DataOutputStream os = null; // output stream
        DataInputStream is = null; // input stream
        try {
            smtpSocket = new Socket("hostname", 25);
            os = new DataOutputStream(smtpSocket.getOutputStream());
            is = new DataInputStream(smtpSocket.getInputStream());
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: hostname");
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: hostname");
        }

    }
}

