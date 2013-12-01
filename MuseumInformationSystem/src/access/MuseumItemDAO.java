package access;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.MuseumItem;

/**
 * The purpose of this module is to populate MuseumItem objects with
 * their corresponding data from the database
 * Created November 27th 2103
 * @author Sara
 *
 */
public class MuseumItemDAO {

	/**
	 * Queries the database to populate a collection of MuseumItem objects
	 * @return a list of Museum item objects populated with data
	 */
	static List<MuseumItem> find(){
		List<MuseumItem> list= new ArrayList<MuseumItem>();
		ResultSet result;
		try {
			result = sqlQuery("Select * FROM MuseumItem WHERE Enabled = 1");
			
			while(result.next()){
				MuseumItem item = new MuseumItem(result.getString(2), result.getString(3), result.getString(4), result.getString(5), result.getString(6));
				list.add(item);
			}	
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * Sets the name of an item
	 * @param name representing the new name to be assigned to the item
	 */
	void setName(String name){
		try {
			sqlUpdate("UPDATE MUSEUMITEM SET ItemTitle = '" + name + "'");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Sets the description for an item
	 * @param description representing the description of the item
	 */
	void setDesciption(String description, String name){
		try {
			sqlUpdate("UPDATE MUSEUMITEM SET ItemDescription = '" + description + "' WHERE ItemTitle = '" + name + "'");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Sets the directory path for the items image file
	 * @param path representing the directory path
	 */
	void setImage(String path, String name){
		try {
			sqlUpdate("UPDATE MUSEUMITEM SET ItemImage = '" + path + "' WHERE ItemTitle = '" + name + "'");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Sets the directory path for the item's audio file
	 * @param path representing the directory path
	 */
	void setAdudio(String path, String name){
		try {
			sqlUpdate("UPDATE MUSEUMITEM SET ItemAudio = '" + path + "' WHERE ItemTitle = '" + name + "'");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Sets the directory path for the item's video file
	 * @param path representing the directory path
	 */
	static void setVideo(String path, String name){
		try {
			sqlUpdate("UPDATE MUSEUMITEM SET ItemVideo = '" + path + "' WHERE ItemTitle = '" + name + "'");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * sqlQuery takes a sql statement intended only as a query to retrieve a dataset from the museumitem table
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
	 * sqlUpdate takes a sql statement intended only as an update, delete, or insert to the msueumitem table
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
	public static void museumtest(){	
		setVideo("mypathtest", "Abacus");
		ArrayList mylist = (ArrayList) find();
		System.out.println((((MuseumItem) mylist.get(0)).getVideo().toString()));
	}
}

