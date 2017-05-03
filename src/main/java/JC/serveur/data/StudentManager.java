package JC.serveur.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 03/05/2017.
 * Polytech'Nice Sophia - SI3
 *
 * @author Julien Maureille
 */
public class StudentManager {
    private List<Student> students = new ArrayList<>();

    public void add(Student id) {
        students.add(id);
    }

    public void remove(int id) {
        students.remove(id);
    }

    public String list() {
        return students.toString();
    }
}
