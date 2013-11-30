package access;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
	static List<User> find(){
		List<User> list = new ArrayList<User>();
		ResultSet result;
		try {
			result = sqlQuery("Select * from User");
			
			while(result.next()){
				User user = new User(result.getString(2), result.getString(3), result.getInt(4));
				list.add(user);
			}	
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}


	public static void test(){
		ArrayList mylist = (ArrayList) find();
		System.out.println((((User) mylist.get(0)).getLoginID().toString()));
	}


	/**
	 * Sets the login name for the user
	 * @param login representing the User's login ID
	 */
	void setLoginID(String login, int userID){
		try {
			sqlQuery("UPDATE USER SET Login = '" + login + "'");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Sets the password for the user
	 * @param password representing  password
	 */
	void setPassword(String password, int userID){
		try {
			sqlQuery("UPDATE USER SET Password = '" + password + "' WHERE UserID = " + userID);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Sets the level of access to application
	 * @param num
	 */
	void setAccessLevel(int num, int userID){
		try {
			sqlQuery("UPDATE USER SET AccessLevel = '" + num + "' WHERE UserID = " + userID);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public static ResultSet sqlQuery(String sqlStatement) throws ClassNotFoundException, SQLException{

		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/MuseumInformationSystem", "root", "sschlosser3");
		PreparedStatement statement = con.prepareStatement(sqlStatement);
		return statement.executeQuery();

		//    	while(result.next()){
		//    		System.out.println(result.getString(2) + " " + result.getString(3));
		//    		
	}

}


