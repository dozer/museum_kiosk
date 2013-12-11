package control;

import java.util.ArrayList;

import access.UserDAO;
import model.User;

/**
 * The purpose of the Login Controller is to verify the User Id and User Password 
 * supplied by the user.  If the D and password is verified, Login
 * Controller will set the User Credentials 
 * Created November 26th 2013
 * @author Sara, Casey
 *
 */
public class LoginController{
	
	/**
	 * Verify UserID and UserPassword from list of Users.  Set global User to 
	 * User that matches ID and Password.  Calls determineView()
	 * @param UserID representing the UserID
	 * @param UserPassword representing the User Password
	 * @return the value of determineView()
	 */
	public static int verifyUser(String userID, String userPassword){
		ArrayList<User> list = (ArrayList<User>) UserDAO.find();

		for(User u: list)
		{
			if((u.getLogin().toLowerCase()).equals(userID.toLowerCase()))
			{
				if((u.getPassword().toLowerCase()).equals(userPassword.toLowerCase()))
					return u.getAccessLevel();  //return USER;
			}
		}
		
		return -1; //return GUEST;
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
