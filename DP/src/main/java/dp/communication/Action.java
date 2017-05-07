package dp.communication;

public enum Action {
	ADD_CONTRIBUTOR(Resource.IDEA, "addContributor", 2, "ID ETUDIANT (chaine) ", "ID IDEE (entier) "),
	CHANGE_STATE(Resource.IDEA, "changeIdeaState", 2, "ID ETUDIANT (chaine) ", "ID IDEE (entier) "),
	SET_TITLE(Resource.IDEA, "setIdeaTitle", 3, "ID ETUDIANT (chaine) ", "ID IDEE (entier) ", "Nouveau titre (chaine)"),
	SET_DESCRIPTION(Resource.IDEA, "setIdeaDescription", 3),
	AGREE_CONTRIBUTOR(Resource.IDEA, "agreeAContributor", 2),
	GET_CONTRIBUTORS(Resource.IDEA, "getContributors", 1),
	GET_ALL_IDEAS(Resource.REPOSITORY, "getAllIdeas", 0),
	GET_ALL_IDEAS_FOR_ONE_STUDENT(Resource.REPOSITORY, "getIdea", 1),
	ADD_IDEA(Resource.REPOSITORY, "addIdea", 3),
	GET_IDEA(Resource.REPOSITORY, "getIdea", 1),
	REMOVE_IDEA(Resource.REPOSITORY, "removeIdea", 2),
	SET_PASSWORD(Resource.STUDENT,"setStudentPassword", 2),
	NONE(Resource.CLOSE, "", 0);
	
	private String value;
	private Resource resource;
	private String[] instructions;
	private int currentArg;
	
	private Action(Resource resource, String value, int argc, String ...instructions){
		this.value = value;
		this.resource = resource;
		currentArg=0;
		this.instructions = instructions;
	}

	public String getString() {
		return value;
	}
	
	public Resource getResource(){
		return resource;
	}
	
	public boolean hasNextInstruction(){
		return !(currentArg==instructions.length);
	}
	
	public String nextInstruction(){
		currentArg++;
		return instructions[currentArg-1];
	}
	
	public static Action getAction(String actionString){
		return Action.findActionFormString(actionString);

	}
	
	private static Action findActionFormString(String actionString){
		for(Action action : Action.values())
			if(action.getString().equals(actionString))
				return action;
		return Action.NONE;
	}
}
