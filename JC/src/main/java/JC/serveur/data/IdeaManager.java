package JC.serveur.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 03/05/2017.
 * Polytech'Nice Sophia - SI3
 *
 * @author Julien Maureille
 */
public class IdeaManager {
    private List<Idea> ideas = new ArrayList<>();

    public void add(Idea id) {
        ideas.add(id);
    }

    public void remove(int id) {
        ideas.remove(id);
    }

    public String list() {
        return ideas.toString();
    }
}
