package JC.communication;

/**
 * @author jamatofu on 08/05/17.
 */
public enum Action {
    AJOUTER_IDEE(TypeAction.IDEE, "ajouterIdee", 2, "Description de l'idée", "Id de l'auteur"),
    CHANGER_ETAT(TypeAction.IDEE, "changerEtat", 1, "ID de l'idée"),
    SUPPRIMER_IDEE(TypeAction.IDEE, "supprimerIdee", 1, "ID de l'idée"),
    CHANGER_DESCRIPTION(TypeAction.IDEE, "changerDescription", 2, "ID de l'idée", "Nouvelle description"),
    CHANGER_NOM(TypeAction.IDEE, "changerNom", 2, "ID de l'idée", "Nouveau nom"),

    GET_ALL_IDEA(TypeAction.GET, "getAllIdea", 0),
    GET_IDEA(TypeAction.GET, "getIdea", 1, "ID de l'idée"),
    GET_DESCRIPTION(TypeAction.GET, "getDescription", 1, "ID de l'idée"),
    GET_NAME_IDEA(TypeAction.GET, "getNameIdea", 1, "ID de l'idée"),

    PARTICIPATER(TypeAction.INTERACTION, "participer", 2, "ID de l'étudiant", "ID du projet"),
    VALIDER_PARTICIPATION(TypeAction.INTERACTION, "validerParticipation", 2, "ID de l'étudiant", "ID du projet"),
    GET_CANDIDAT(TypeAction.INTERACTION, "getCandidat", 1, "ID du projet"),

    QUIT(TypeAction.QUIT, "quit", 0);


    private TypeAction typeAction;
    private String nomMethode;
    private int nbArg;
    private String[] args;

    Action(TypeAction typeAction, String nomMethode, int nbArg, String... args) {
        this.typeAction = typeAction;
        this.nomMethode = nomMethode;
        this.nbArg = nbArg;
        this.args = args;
    }
}
