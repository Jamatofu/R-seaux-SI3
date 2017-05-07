package dp.communication;

public enum Action {
	ADD_CONTRIBUTOR("addContributor"),
	CHANGE_STATE("changeIdeaState"),
	SET_TITLE("setIdeaTitle"),
	SET_DESCRIPTION("setIdeaDescription"),
	AGREE_CONTRIBUTOR("agreeAContributor"),
	GET_CONTRIBUTORS("getContributors"),
	GET_ALL_IDEAS("getAllIdeas"),
	GET_ALL_IDEAS_FOR_ONE_STUDENT("getIdea"),
	ADD_IDEA("addIdea"),
	REMOVE_IDEA("removeIdea"),
	SET_PASSWORD("setStudentPassword"),
	GET_IDEA("getIdea"),
	NONE("");
	
	private String value;
	
	private Action(String value){
		this.value = value;
	}

	public String getString() {
		return value;
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
