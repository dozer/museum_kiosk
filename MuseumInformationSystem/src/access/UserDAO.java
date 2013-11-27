package access;

import java.util.ArrayList;
import java.util.List;

import model.User;

/**
 * The purpose of this module is to populate the User objects with their
 * corresponding data from the database
 * Created November 26th 2013
 * @author Sara
 *
 */
public class UserDAO {

	/**
	 * Queries the database to populate a collection of User objects
	 * @return a list of User objects populated with data
	 */
	List<User> find(){
		List<User> list = new ArrayList<User>();
		return list;
	}
	
	/**
	 * Sets the login name for the user
	 * @param login representing the User's login ID
	 */
	void setLoginID(String login){
		
	}
	
	/**
	 * Sets the password for the user
	 * @param password representing  apasseord
	 */
	void setPassword(String password){
		
	}
	
	/**
	 * Sets the level of access to application
	 * @param num
	 */
	void setAccessLevel(int num){
	
	}
}

