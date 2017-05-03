package JC.communication;

import JC.serveur.data.Action;

import java.io.Serializable;
import java.util.List;

/**
 * Created on 28/04/2017.
 * Polytech'Nice Sophia - SI3
 *
 * @author Julien Maureille
 */
public class Query implements Serializable {
    private String resource;
    private String action; //ajouter, supprimer, lister...
    private List<String> parameters;

    public Query(String action, String resource, List<String> parameters) {
        this.resource = resource;
        this.action = action;
        this.parameters = parameters;
    }
}
