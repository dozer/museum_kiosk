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
			result = sqlQuery("Select * FROM User");

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


	/**
	 * Method is used as a test case, called from guestView in view folder
	 */
	public static void usertest(){	
		ArrayList mylist = (ArrayList) find();
		System.out.println((((User) mylist.get(0)).getLoginID().toString()));
	}

	/**
	 * Adds a new user to database
	 * @param login
	 * @param password
	 * @param accessLevel
	 */
	public void addUser(String login, String password, int accessLevel){
		try {
			//sqlUpdate("INSERT INTO USER VALUES(default, '"+ login + "', '" + password + "', " + accessLevel);
			sqlUpdate("INSERT INTO USER VALUES('"+ login + "', '" + password + "', " + accessLevel);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Sets the login name for the user
	 * @param login representing the User's login ID
	 */
	public void setLoginID(String login, int userID){
		try {
			sqlUpdate("UPDATE USER SET Login = '" + login + "' WHERE UserID = " + userID);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Sets the password for the user
	 * @param password representing  password
	 */
	public void setPassword(String password, int userID){
		try {
			sqlUpdate("UPDATE USER SET Password = '" + password + "' WHERE UserID = " + userID);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Sets the level of access to application
	 * @param num
	 */
	public void setAccessLevel(int num, int userID){
		try {
			sqlUpdate("UPDATE USER SET AccessLevel = '" + num + "' WHERE UserID = " + userID);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * sqlQuery takes a sql statement intended only as a query to retrieve a dataset from the user table
	 * @param sqlStatement
	 * @return resultset of sql query
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static ResultSet sqlQuery(String sqlStatement) throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/MuseumInformationSystem", "root", "sschlosser3");
		PreparedStatement statement = con.prepareStatement(sqlStatement);
		return statement.executeQuery();
	}

	/**
	 * sqlUpdate takes a sql statement intended only as an update, delete, or insert to the user table
	 * @param sqlStatement
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static void sqlUpdate(String sqlStatement) throws SQLException, ClassNotFoundException{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/MuseumInformationSystem", "root", "sschlosser3");
		PreparedStatement statement = con.prepareStatement(sqlStatement);
		statement.executeUpdate();
	}

}


