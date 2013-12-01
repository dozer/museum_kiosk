package access;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Exhibit;
import model.MuseumItem;

/**
 * The purpose of this module is to populate Exhibit objects with their 
 * corresponding data from the database
 * Created November 27th 2013
 * @author Sara
 *
 */
public class ExhibitDAO {

	/**
	 * Queries the database to populate a collection of Exhibit objects
	 * @return a list of Exhibit objects populated with data
	 */
	List<Exhibit> find(){
		List<Exhibit> list = new ArrayList<Exhibit>();
		return list;
	}
	
	/**
	 * Sets the location of collection of items
	 * @param location representing the location inside the museum
	 */
	void setExhibitLocation(int[] location){
		//I changed the location parameter from a string into an array of size 2 to stay 
		//	formalized with other objects representing location
	}
	
	/**
	 * Adds a museum item to the collection
	 * @param item representing an item inside the museum
	 */
	void addItem(MuseumItem item){
//		try {
//			sqlUpdate("INSERT INTO MUSEUMITEM VALUES( ItemDescription = '" + description + "' WHERE ItemTitle = '" + name + "'");
//		} catch (ClassNotFoundException | SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
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
}
