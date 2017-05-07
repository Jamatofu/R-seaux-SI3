package dp.common.communication;

/**
 * 
 * All possible actions
 * @author David Sene && Pierre Rainero
 *
 */
public enum Action {
	ADD_CONTRIBUTOR(Resource.IDEA, "addContributor", 2, "A", "ID IDEE (entier) "),
	CHANGE_STATE(Resource.IDEA, "changeIdeaState", 2, "A", "ID IDEE (entier) "),
	SET_TITLE(Resource.IDEA, "setIdeaTitle", 3, "A", "ID IDEE (entier) ", "Nouveau titre (chaine) "),
	SET_DESCRIPTION(Resource.IDEA, "setIdeaDescription", 3, "A", "ID IDEE (entier) ", "Nouvelle description (chaine) "),
	AGREE_CONTRIBUTOR(Resource.IDEA, "agreeAContributor", 2, "A", "ID IDEE (entier) ", "ID ETUDIANT TO AGREE (chaine) "),
	GET_CONTRIBUTORS(Resource.IDEA, "getContributors", 1, "ID IDEE (entier) "),
	GET_ALL_IDEAS(Resource.REPOSITORY, "getAllIdeas", 0),
	GET_ALL_IDEAS_FOR_ONE_STUDENT(Resource.REPOSITORY, "getAllIdeasForOneStudent", 1, "ID ADMIN (chaine) "),
	ADD_IDEA(Resource.REPOSITORY, "addIdea", 3, "ID ADMIN/AUTEUR (chaine) ", "Titre (chaine) ", "Description (chaine) "),
	REMOVE_IDEA(Resource.REPOSITORY, "removeIdea", 2, "A", "ID IDEE (entier) "),
	GET_IDEA(Resource.REPOSITORY, "getIdea", 1, "ID IDEE (entier) "),
	SET_PASSWORD(Resource.STUDENT,"setStudentPassword", 2, "ID ETUDIANT (chaine) ", "Nouveau mot de passe (chaine) "),
	GET_PROJECTS(Resource.REPOSITORY, "getProjects", 0),
	NONE(Resource.CLOSE, "", 0);
	
	private String value;
	private Resource resource;
	private String[] instructions;
	private int currentArg;
	
	/**
	 * Normal constructor
	 * @param resource class to use/modify
	 * @param value value for the RequestOperator
	 * @param argc number of parameters
	 * @param instructions instructions to ask to the client
	 */
	private Action(Resource resource, String value, int argc, String ...instructions){
		this.value = value;
		this.resource = resource;
		currentArg=0;
		this.instructions = instructions;
	}

	/**
	  * Consulting accessor of the value for the RequestOperator
	  * @return value for the RequestOperator
	 */
	public String getString() {
		return value;
	}
	
	/**
	  * Consulting accessor of the resource to use/modify
	  * @return resource to use/modify
	 */
	public Resource getResource(){
		return resource;
	}
	
	/**
	 * Allows to know if there is an other instruction to do
	 * @return true if there is and other instruction, false otherwise
	 */
	public boolean hasNextInstruction(){
		return !(currentArg==instructions.length);
	}
	
	/**
	 * Return the next instruction to deliver to the client and proceeds to the next instruction
	 * @return the next instruction to deliver to the client
	 */
	public String nextInstruction(){
		currentArg++;
		return instructions[currentArg-1];
	}
	
	/**
	 * Return a correct Action object according the value for the RequestOperator
	 * @param actionString value for the RequestOperator
	 * @return action object
	 */
	public static Action getAction(String actionString){
		return Action.findActionFormString(actionString);
	}
	
	private static Action findActionFormString(String actionString){
		for(Action action : Action.values())
			if(action.getString().equals(actionString)){
				return action;
			}
		return Action.NONE;
	}
	
	public void resetInstructions(){
		currentArg=0;
	}
}
