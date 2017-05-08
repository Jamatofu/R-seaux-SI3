package JC.serveur.data;

/**
 * Created on 28/04/2017.
 * Polytech'Nice Sophia - SI3
 *
 * @author Julien Maureille
 */
public class Idea extends Resource {
    private String description;
    private String name;
    private boolean isIdea;

    private static int idCount = 0;

    public Idea(String description, String name, boolean isIdea) {
        this.description = description;
        this.name = name;
        this.isIdea = isIdea;
        this.id = idCount++;
    }

    public Idea(String description, String name) {
        this(description, name, true);
    }


    public String toString() {
        return "ID :  " + id + " || Titre : " + name + " || Description : " + description + "\n";
    }
}
