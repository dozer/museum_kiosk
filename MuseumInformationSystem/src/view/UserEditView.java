package view;

import model.User;

/**
 * Provides a visual interface to create and alter back end users
 * Created November 26th 2013
 * @author Sara
 */
public class UserEditView {


	/**
	 * Opens a window to allow input of a new user
	 * @return a new user
	 */
	User createUser(){
		User user = new User("login", "password", 0);	
		//I am not sure what the intention was to return a user on a view method call
		//method may have to be redesigned - Sara 11/26/13
		return user;
	}
	
	/**
	 * Opens a window to edit an existing user
	 * @param user, representing the user to be edited
	 * @return a user with its updated values
	 */
	User editUser(User user){
		//Still not sure of purpose to return user
		//may not require parameter user either
		return user;	
	}
}
