package model;
/**
 * The purpose of this module is to establish a user object
 * which will be used for employees to login and make changes
 * to the museum database.  Each user object will be made up 
 * of a login ID, password, and a numerical representation of 
 * their member access level
 * Created November 25th 2013
 * @author Sara
 */
public class User {

	private String login;
	private String password;
	private int accessLevel;
	
	/**
	 * User class constructor
	 * @param login
	 * @param password
	 * @param accessLevel
	 */
	public User(String login, String password, int accessLevel){
		this.login = login;
		this.password = password;
		this.accessLevel = accessLevel;
	}
	
	/**
	 * Sets the login name for the user
	 * @param login, a string representing logniID
	 */
	void setLoginID(String login){
		this.login = login;
	}
	
	/**
	 * Sets the password for the user
	 * @param password, a string representing the password
	 */
	void setPassword(String password){
		this.password = password;
	}
	
	/**
	 * Sets the level of access of the user for the application
	 * @param num, an integer representing the access level of the user
	 *
	 */
	void setAccessLevel(int num){
		this.accessLevel = num;
	}
	
	/**
	 * Returns the login name of the user
	 * @return a string representing the login ID for the user
	 */
	String getLoginID(){
		return login;
	}
	
	/**
	 * Returns the password of the user
	 * @return, a string representing the password for the user
	 */
	String getPassword(){
		return password;
	}
	
	/**
	 * Returns a numerical value representing the user's level of access
	 * @return, an integer representing the user's access level
	 */
	int getAccessLevel(){
		return accessLevel;
	}
}
