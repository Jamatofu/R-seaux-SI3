package dp.server.processing;

/**
 * 
 * Modelize a student
 * @author David Sene && Pierre Rainero
 *
 */
public class Student {
	private String id;
	private String password;

	/**
	 * Normal constructor
	 * @param id id of the student
	 * @param password password of the student
	 */
	public Student(String id, String password) {
		this.id = id;
		this.password = password;
	}

	/**
	 * Consulting accessor of the id
	 * @return student id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Consulting accessor of the password
	 * @return current student password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Change accessor of the password
	 * @param newPass new password
	 */
	public void setPassword(String newPass){
		password = newPass;
	}
}
