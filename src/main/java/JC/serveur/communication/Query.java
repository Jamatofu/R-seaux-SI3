package JC.serveur.communication;

import JC.serveur.data.Action;
import JC.serveur.data.Resource;

import java.io.Serializable;
import java.util.List;

/**
 * Created on 28/04/2017.
 * Polytech'Nice Sophia - SI3
 *
 * @author Julien Maureille
 */
public class Query implements Serializable {
    private Resource resource;
    private Action action; //ajouter, supprimer, lister...
    private List<String> parameters;

}
