package JC.communication;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Created on 28/04/2017.
 * Polytech'Nice Sophia - SI3
 *
 * @author Julien Maureille
 */
public class Query implements Serializable {
    private Action action; //ajouter, supprimer, lister...
    private String[] parameters;

    public Query(Action action, String[] parameters) {
        this.action = action;
        this.parameters = parameters;
    }

    @Override
    public String toString() {
        return "Query{" +
                "action=" + action +
                ", parameters=" + Arrays.toString(parameters) +
                '}';
    }

    public Action getAction() {
        return action;
    }

    public String[] getParameters() {
        return parameters;
    }
}
