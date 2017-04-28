package JC.serveur.communication;

import java.util.List;

/**
 * Created on 28/04/2017.
 * Polytech'Nice Sophia - SI3
 *
 * @author Julien Maureille
 */
public class Query {
    private String resource; //sous forme : "type:id" -> par ex : "student:45"
    /**
     *
     */
    private String action; //ajouter, supprimer, lister...
    private List<String> parameters;

}
