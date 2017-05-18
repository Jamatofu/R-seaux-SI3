package dp.server.processing;

import org.junit.Before;
import org.junit.Test;
import org.junit.Rule;

import static org.junit.Assert.*;
import org.junit.rules.ExpectedException;

import java.util.Map;

import dp.common.communication.Idea;
import dp.common.communication.Student;
import dp.common.exception.IdeaException;

/**
 * 
 * Test class for Idea methods
 * @author David Sene && Pierre Rainero
 *
 */
public class IdeaTest {
	private Idea idea;
	private Student student1;
	private Student student2;
	
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    
	@Before
	public void defineContext(){
		idea = new Idea(new Student("author", "osef"), "id1", "description");
		
		student1 = new Student("user1", "pass1");
		student2 = new Student("user2", "pass2");
	}
	
	@Test
	public void agreeForSomStudents() throws IdeaException{
		assertTrue(idea.getContributors().isEmpty());
		
		idea.addContributor(student1);
		idea.addContributor(student2);
		
		Map<Student, Boolean> contributors = idea.getContributors();
		assertFalse(contributors.isEmpty());
		assertFalse(contributors.get(student1));
		assertFalse(contributors.get(student2));
		
		idea.agreeParticipant(student1);
		assertTrue(contributors.get(student1));
		assertFalse(contributors.get(student2));
	}
	
	@Test
	public void reachMaxSize() throws IdeaException{
		Student s3 = new Student("s3", "p3");
		Student s4 = new Student("s4", "p4");
		idea.addContributor(student1);
		idea.addContributor(student2);
		idea.addContributor(s3);
		idea.agreeParticipant(student1);
		idea.agreeParticipant(student2);
		idea.agreeParticipant(s3);
		
        thrown.expect(IdeaException.class);
        thrown.expectMessage("Le nombre maximale de participants pour ce projet a été atteint.");
        idea.addContributor(s4);
	}
	
	@Test
	public void agreeNonExistentContributor() throws IdeaException{
		idea.addContributor(student1);
        thrown.expect(IdeaException.class);
        thrown.expectMessage("L'étudiant n'a pas demandé a participer au projet.");
        idea.agreeParticipant(student2);
	}
}
