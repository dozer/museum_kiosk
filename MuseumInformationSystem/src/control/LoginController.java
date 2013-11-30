package control;

import java.util.ArrayList;

import model.User;

/**
 * The purpose of the Login Controller is to verify the User Id and User Password 
 * supplied by the user.  If the D and password is verified, Login
 * Controller will set the User Credentials 
 * Created November 26th 2013
 * @author Sara
 *
 */
public class LoginController {

	/**
	 * Verify UserID and UserPassword from list of Users.  Set global User to 
	 * User that matches ID and Password.  Calls determineView()
	 * @param UserID representing the UserID
	 * @param UserPassword representing the User Password
	 * @return the value of determineView()
	 */
	
	int verifyID(ArrayList<User> list, String userID, String userPassword){
		for(User u: list)
		{
			if(u.getLoginID() == userID)
			{
				if(u.getPassword() == userPassword)
					return 1;  //return USER;
			}
		}
		
		return 0; //return GUEST;
		
				//not sure what Usertype is exactly referring to?? access level??
				//If it is access level, then the return value should maybe be an int
				//or referring to enum
		
				//Casey Comment: in keeping with Sara's comment, I am returning a 1 for
				//access level User, 0 for access level Guest.  Can switch to enum if
				//people think thats better
	}
	
	/**
	 * Determine what view should be displayed depending on the User privilege level
	 * @param user representing a user object
	 * @return a value representing either Admin or Guest
	 */
	
	int verifyID(User user){
		if(user.getAccessLevel() == 1)
			return 1;	//return USER;
		else
			return 0;	//return GUEST;
	}
	
}
