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
 * Updated Decemeber 2nd 2013
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
			result = sqlQuery("Select Login, Password, AccessLevel FROM User");

			while(result.next()){
				User user = new User(result.getString(1), result.getString(2), result.getInt(3));
				list.add(user);
			}	

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * Updates entire database by dropping the previous table and inserting values back into the table based on
	 * any changes that were made to the list of users
	 * @param list, representing the list of users
	 */
	static void update(List<User> list){
		try {
			sqlUpdate("DELETE FROM User");

			for(int i = 0; i < list.size(); i++){
				sqlUpdate("INSERT INTO User VALUES('"
						+ list.get(i).getLogin() + "', '" + list.get(i).getPassword() + "', "
						+ list.get(i).getAccessLevel() + ")");
			}	

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
	
	/**
	 * Method is used as a test case, called from guestView in view folder
	 */
	public static void usertest(){	
		ArrayList mylist = (ArrayList) find();
		System.out.println((((User) mylist.get(0)).getLogin().toString()));
	}
}


