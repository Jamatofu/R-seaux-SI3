package JC.serveur;

import JC.communication.Query;
import JC.communication.Response;
import JC.serveur.data.IdeaManager;
import JC.serveur.data.StudentManager;

/**
 * @author jamatofu on 28/04/17.
 */
public class MainServer {

    private StudentManager students = new StudentManager();
    private IdeaManager ideas = new IdeaManager();

    public static void main(String args[]) {
        Server server = new Server();
        server.runServer();
        server.connectToClient();
    }
    private void readQuery(Query query){

    }
    private void createResponse(Response res){

    }
}
