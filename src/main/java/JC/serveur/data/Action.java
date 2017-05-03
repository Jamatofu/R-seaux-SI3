package JC.serveur.data;

/**
 * Created on 03/05/2017.
 * Polytech'Nice Sophia - SI3
 *
 * @author Julien Maureille
 */
public enum Action {
    AJOUTER_IDEE(2),
    CHANGER_ETAT,
    SUPPRIMER_IDEE,
    CHANGER_DESCRIPTION(2),
    CHANGER_NOM_IDEE(2),

    GET_ALL_IDEE,
    GET_IDEE,
    GET_DESCRIPTION,
    GET_NAME,

    PARTICIPER(2),
    VALIDER_PARTICIPATION(2),
    GET_CANDIDATS,

    GET_PROJETS;

    private int parameters;

    Action() {
        this(0);
    }

    Action(int parameters) {
        this.parameters = parameters;
    }

    public int getParameters() {
        return parameters;
    }
}
