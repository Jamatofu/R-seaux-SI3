package dp.processing;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.List;

public class RepositoryTest {
	private Repository repository;
	private Student student1;
	private Student student2;
	private Student student3;
	
	@Before
	public void defineContext(){
		repository = new Repository();
		
		student1 = new Student("user1", "pass1");
		student2 = new Student("user2", "pass2");
		student3 = new Student("user3", "pass3");
	}
	
	@Test
	public void listIdeasForAllStudent(){
		Idea idea11 = new Idea(student1, "idea11", "");
		Idea idea21 = new Idea(student2, "idea21", "");
		repository.addIdea(idea11);
		repository.addIdea(idea21);
		
		List<Idea> returnV = repository.getAllIdeas();
		
		assertFalse(returnV.isEmpty());
		assertEquals(2, returnV.size());
	}
	
	@Test
	public void listIdeasForOneStudent(){
		Idea idea11 = new Idea(student1, "idea11", "");
		Idea idea12 = new Idea(student1, "idea12", "");
		Idea idea21 = new Idea(student2, "idea21", "");
		Idea idea13 = new Idea(student1, "idea13", "");
		
		repository.addIdea(idea11);
		repository.addIdea(idea12);
		repository.addIdea(idea21);
		repository.addIdea(idea13);
		
		List<Idea> returnV = repository.getAllIdeas(student1);
		
		assertFalse(returnV.isEmpty());
		assertTrue(returnV.contains(idea11));
		assertTrue(returnV.contains(idea12));
		assertFalse(returnV.contains(idea21));
		assertTrue(returnV.contains(idea13));
		
		returnV = repository.getAllIdeas(student2);
		assertFalse(returnV.isEmpty());
		assertTrue(returnV.contains(idea21));
		
		returnV = repository.getAllIdeas(student3);
		assertTrue(returnV.isEmpty());
	}
	
}
