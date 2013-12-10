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
	public static List<Exhibit> find(){
		List<Exhibit> list = new ArrayList<Exhibit>();
		ResultSet result;
		try {
			result = sqlQuery("Select ExhibitName, ExhibitDescription, LocationX, LocationY "
					+ "FROM ExhibitList");

			while(result.next()){
				int[] location = {result.getInt(3), result.getInt(4)};
				Exhibit exhibit = new Exhibit(result.getString(1), result.getString(2), location);
				//exhibitname                exhibitdescription                x,y locations
				list.add(exhibit);
			}        

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * Queries the database to populate a collection of Museum objects in Exhibit
	 * @return a list of items in exhibit
	 */
	public static List<MuseumItem> findMuseumItemInExhibit(String ExhibitName){
		List<MuseumItem> list = new ArrayList<MuseumItem>();
		ResultSet result;
		try {
			result = sqlQuery("SELECT ItemName, ItemDescription, ItemImage, ItemAudio, "
					+ "ItemVideo, LocationX, LocationY FROM MuseumItem "
					+ "INNER JOIN " + ExhibitName +" ON " + ExhibitName + ".MuseumItemName = MuseumItem.ItemName");
			//                                                " WHERE ItemName = '" + result1.getString(1) + "'");
			while(result.next()){
				int[] location = {result.getInt(6), result.getInt(7)};
				//                                MuseumItem item = new MuseumItem("me", "me", null, null, null, location);
				MuseumItem item = new MuseumItem(result.getString(1), result.getString(2),
						//ItemName                        itemdescription        
						result.getString(3), result.getString(4), result.getString(5), location );
				//itemimage                        itemaudio                        itemvideo                        x, y locations
				list.add(item);
			}        

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * Adds a new row to the exhibitlist table and creates a new table to fill with museum items
	 * @param ExhibitName
	 */
	public static void CreateExhibit(String ExhibitName){
		try {
			sqlUpdate("CREATE TABLE " + ExhibitName + " (MuseumItemName varchar(50))");

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Updates entire database by dropping the previous table and inserting values back into the table based on
	 * any changes that were made to the list of exhibits
	 * @param list, representing a collection of exhibits
	 */
	public static void updateExhibitTable(List<Exhibit> list){
		try {
			for(int i = 0; i < list.size(); i++){

				int[] location = list.get(i).getExhibitLocation();
				sqlUpdate("INSERT INTO ExhibitList VALUES('"
						+ list.get(i).getExhibitName() + "', '" + list.get(i).getExhibitDescription() + "', "
						+ location[0] + ", " + location[1] + ")");
			}        

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Updates entire database by dropping the previous table and inserting values back into the table based on
	 * any changes that were made to the exhibit's list
	 * @param list, representing a collection of museum items
	 */
	public static void updateItemsInExhibit(String ExhibitName, List<MuseumItem> list){
		ResultSet result = null;
		try {
			if (result.next()){
				result = sqlQuery("SELECT COUNT(*) FROM information_schema.tables"
						+ " WHERE table_name = '" + ExhibitName + "'");
			}
			if (result.getInt(1) == 0){
				CreateExhibit(ExhibitName);
			}
			else{

				sqlUpdate("DELETE FROM " + ExhibitName);
			}

			for(int i = 0; i < list.size(); i++){

				sqlUpdate("INSERT INTO " + ExhibitName + " VALUES('" + list.get(i).toString() + "')");
				//MuseumItemName
			}        

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
	public static void exhibittest(){

		//List<Exhibit> exhibitlist = (List<Exhibit> ) find();

		//creating new exhibitlist for testing and updating it to exhibitlist table
		List<Exhibit> exhibitlist = new ArrayList<Exhibit>();
		int[] location = {-1,-1};
		Exhibit exhibit = new Exhibit("Generation1","Description of Generation1", location);        
		exhibitlist.add(exhibit);                                                        
		updateExhibitTable(exhibitlist);

		//creating a table specifically for that exhibit
		CreateExhibit("Generation1");                                        

		//creating a list of museumitems to put in exhibit table        
		ArrayList itemlist = new ArrayList();
		itemlist.add("Z3");
		itemlist.add("Atanasoff–Berry Computer (ABC)");
		updateItemsInExhibit("Generation1", itemlist);

		//printing all exhibits
		for(int i = 0; i < exhibitlist.size(); i++){
			System.out.println((((Exhibit) exhibitlist.get(i)).getExhibitName().toString()));
		}

		List<MuseumItem> itemlist2 = (List<MuseumItem> ) findMuseumItemInExhibit("Generation1");
		//printing all Museumitems in exhibit
		for(int i = 0; i < itemlist2.size(); i++){
			System.out.println("ITEMNAME: "+ itemlist2.get(i).getName() +", DESCRIPTION: "+ itemlist2.get(i).getDescription());
		}


	}
}