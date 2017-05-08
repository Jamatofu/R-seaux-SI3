package JC.client;
import JC.communication.Query;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jamatofu on 28/04/17.
 */


public class MainClient {

    public static void main(String[] args) {
        Client client = new Client();
        client.registerId();
        client.readEntry();
    }

    public Query parseQuery(String query_p){
        String[] parts = query_p.split(" ");
        Query query = null;
        String action = parts[0]; // "remove"

        String resourceType = parts[1]; // "student"
        List<String> parameters = new ArrayList<>();

        // add <student|idea> <id> <name>
        if(action.equals("add")) {
            parameters.add(parts[2]); // id
            parameters.add(parts[3]); // name
        }

        // remove <student|idea> <id>
        if(action.equals("remove")) {
            parameters.add(parts[2]); // "5"
        }

        query = new Query(action, resourceType, parameters);

        return query;
    }
}

