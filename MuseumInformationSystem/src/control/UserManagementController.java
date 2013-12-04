package control;

import java.util.ArrayList;
import java.util.List;

import model.User;
import access.UserDAO;

/**
 * The purpose of the User Management Controller is to manage a list of type
 * User. User management Controller will be able to add, remove, and edit Users
 * on a list, by using User Model functions Created November 26th 2013
 *
 * @author Sara, Joe
 *
 */
public class UserManagementController {

    /**
     * Creates a new User object using given parameters, adds new User to list
     *
     * @param ID representing the User's login ID
     * @param password representing the User's password
     * @param accessLevel representing the User's access level
     */
    public void addToUserList(String ID, String password, int accessLevel) {
        List<User> list = UserDAO.find();
        boolean doesNotExist = true;

        for (User u : list) {
            if (u.getLogin() == ID) {
                doesNotExist = false;
                break;
            }
        }

        if (doesNotExist) {
            list.add(new User(ID, password, accessLevel));
            UserDAO.update(list);
        }


    }

    /**
     * Removes the User from the list that matches the UserID
     *
     * @param ID representing the User's login ID
     */
    public void removeFromUserList(String ID) {
        List<User> list = UserDAO.find();
        for (User u : list) {
            if (u.getLogin() == ID) {
                list.remove(u);
                break;
            }
        }

        UserDAO.update(list);
    }

    /**
     * Edits either the password or the access level of the User matching the
     * given ID
     *
     * @param ID representing the User's login ID
     * @param password representing the User's password
     * @param accessLevel representing the User's accessLevel
     */
    public void editUserList(String ID, String password, int accessLevel) {
        List<User> list = UserDAO.find();
        for (User u : list) {
            if (u.getLogin() == ID) {
                if (password != null) {
                    u.setPassword(password);
                }
                if (accessLevel != -1) {
                    u.setAccessLevel(accessLevel);
                }

                break;
            }
        }
        UserDAO.update(list);
    }
}
