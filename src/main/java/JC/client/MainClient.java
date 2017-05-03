package JC.client;
import java.io.*;
import java.net.*;
/**
 * @author jamatofu on 28/04/17.
 */


public class MainClient {

    public static void main(String[] args) {
        Socket smtpSocket = null; // le socket client
        PrintStream os = null; // output stream
        BufferedReader is = null; // input stream
        try {
            smtpSocket = new Socket("10.212.124.235", 4000);
            System.out.println("ok");
            os = new PrintStream(smtpSocket.getOutputStream());
            is = new BufferedReader(new InputStreamReader(smtpSocket.getInputStream()));
            os.println("hello");
            os.flush();

            String line;
            line = is.readLine();
            System.out.println(line);

        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: hostname");
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: hostname");
        }

    }
}

