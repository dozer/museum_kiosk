package access;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Exhibit;

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
		ResultSet result;
		try {
			result = sqlQuery("Select * FROM ExhibitList WHERE Enabled = 1");

			while(result.next()){
				int[] location = {result.getInt(4), result.getInt(5)};
				Exhibit exhibit = new Exhibit(result.getString(2), result.getString(3), location);
											//exhibitname		exhibitdescription		locationx, locationy
				list.add(exhibit);
			}	

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * Sets the location of collection of items
	 * @param string representing exhibit name
	 * @param location representing the location inside the museum
	 */
	public static void  setExhibitLocation(String ExhibitName, int[] location){
		try {
			sqlUpdate("UPDATE ExhibitList SET LocationX = " + location[0] + ", LocationY = " + location[1] + "WHERE ExhibitName = '" + 
					ExhibitName + "'");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Sets the description of the exhibit on the exhibitlist table
	 * @param ExhibitName
	 * @param ExhibitDescription
	 */
	public static void  setExhibitDescriptin(String ExhibitName, String ExhibitDescription){
		try {
			sqlUpdate("UPDATE ExhibitList SET ExhibitDescription = '" + ExhibitDescription + "' WHERE ExhibitName = '" + 
					ExhibitName + "'");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Adds a new row to the exhibitlist table and creates a new table to fill with museum items
	 * @param ExhibitName
	 * @param ExhibitDescription
	 * @param LocationX
	 * @param LocationY
	 */
	public static void  CreateExhibit(String ExhibitName, String ExhibitDescription, int LocationX, int LocationY){
		try {
			sqlUpdate("INSERT INTO ExhibitList VALUES(default, " + ExhibitName + "," + ExhibitDescription + ", " +  LocationX + ", " + LocationY + ")");
			
			sqlUpdate("CREATE TABLE " + ExhibitName + " (ExhibtID int not null autoincrement, ExhibitName varchar(50) not null"
					+ ", Enabled int, Primary Key (ExhibitID))");
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Adds a museum item to the collection
	 * @param string representing exhibit name
	 * @param string representing an item inside the museum
	 */
	public static void  addItem(String ExhibitName, String item){
		try {
			sqlUpdate("INSERT INTO " + ExhibitName + " VALUES(default, '" + item + "', 1)");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Removes (disables) item from exhibit list
	 * @param ExhibitName representing the exhibit name
	 * @param item representing the item to be removed from list
	 */
	public static void removeItem(String ExhibitName, String item){
		try {
			sqlUpdate("UPDATE " + ExhibitName + " SET Enabled = 0 WHERE MuseumItemName = '" + item + "'");
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
}
