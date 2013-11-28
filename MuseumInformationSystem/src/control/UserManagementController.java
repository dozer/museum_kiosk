package control;

import java.util.ArrayList;

import model.User;

/**
 * The purpose of the User Management Controller is to manage a list of type
 * User.  User management Controller will be able to add, remove, and edit Users
 * on a list, by using User Model functions
 * Created November 26th 2013 
 * @author Sara
 *
 */
public class UserManagementController {

	/**
	 * Creates a new User object using given parameters, adds new User to list
	 * @param list representing list to be added to
	 * @param ID representing the User's login ID
	 * @param password representing the User's password
	 * @param accessLevel representing the User's acess level
	 *//*
	ArrayList<User> addToUserList(ArrayList<User> list, String ID, String password, int accessLevel){
		boolean doesNotExist = true; 
		
		for(User u: list)
		{
			if(u.getUserID == ID)
			{
				doesNotExist = false;
				break;
			}
		}
		
		if(doesNotExist)
			list.add(new User(ID, password,accessLevel));
		
		return list;
	}*/
	
	/**
	 * Removes the User from the list that matches the UserID
	 * @param list representing list to be removed from
	 * @param ID representing the User's login ID
	 *//*
	ArrayList<User> removeFromUserList(ArrayList<User> list, String ID){
		for(User u: list)
		{
			if(u.getUserID() == ID)
			{
				list.remove(u);
				break;
			}
		}
		
		return list;
	}*/
	
	/** 
	 * Edits either the password or the access level of the User matching the given ID
	 * @param list representing list to be edited
	 * @param ID representing the User's login ID
	 * @param password representing the User's password
	 * @param accessLevel representing the User's accessLevel
	 *//*
	ArrayList<User> editUserList(ArrayList<User> list, String ID, String password, int accessLevel){
		for(User u: list)
		{
			if(u.getUserID() == ID)
			{
				if(password == null)
					password = u.getPassword();
				if(accessLevel == -1)
					accessLevel = u.getAccessLevel();				
				
				list.set(list.indexOf(u), new User(ID, password, accessLevel));
				
				break;
			}
		}
		
	}*/
	
}
