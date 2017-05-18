package dp.common.communication;

import java.io.Serializable;

/**
 * 
 * Modelize a student
 * @author David Sene && Pierre Rainero
 *
 */
public class Student implements Serializable{
	
	private static final long serialVersionUID = 1L;
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


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Student))
			return false;
		Student other = (Student) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}
	
	
}
