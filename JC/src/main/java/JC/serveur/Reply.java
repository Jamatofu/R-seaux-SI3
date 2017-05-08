package JC.serveur;

import java.io.Serializable;

/**
 * @author jamatofu on 08/05/17.
 */
public class Reply implements Serializable{
    private StringBuilder content;

    public Reply() {
        this.content = new StringBuilder();
    }

    public void addSentence(String sentence) {
        content.append(sentence);
    }

    public String toString() {
        return content.toString();
    }
}
