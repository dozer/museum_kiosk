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
 * Updated Decemeber 2nd 2013
 * @author Sara
 *
 */
public class MuseumItemDAO {

	/**
	 * Queries the database to populate a collection of MuseumItem objects
	 * @return a list of Museum item objects populated with data
	 */
	public static List<MuseumItem> find(){
		List<MuseumItem> list= new ArrayList<MuseumItem>();
		ResultSet result;
		try {
			result = MySqlConnection.sqlQuery("Select ItemName, ItemDescription, ImagePath, AudioPath, VideoPath, LocationX, LocationY FROM MuseumItem");

			while(result.next()){
				int[] location = {result.getInt(6), result.getInt(7)};
				MuseumItem item = new MuseumItem(result.getString(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5), location );
												//ItemName			itemdescription			itemimage			itemaudio			itemvideo			x, y locations
				list.add(item);
			}	

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * Updates entire database by dropping the previous table and inserting values back into the table based on
	 * any changes that were made to the list of museum items
	 * @param list, representing a collection of museum items
	 */
	public static void update(List<MuseumItem> list){
		try {
			MySqlConnection.sqlUpdate("DELETE FROM MuseumItem");

			for(int i = 0; i < list.size(); i++){
				
				int[] location = list.get(i).getLocation();
				MySqlConnection.sqlUpdate("INSERT INTO MuseumItem VALUES('"
						+ list.get(i).getName() + "', '" + list.get(i).getDescription() + "', "
						+ formatString(list.get(i).getImage()) + ", " + formatString(list.get(i).getAudio()) + ", " 
						+ formatString(list.get(i).getVideo()) + ", " + location[0] + ", " + location[1] + ")");
			}	

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Helper function to make sure directory path strings are properly formatted
	 * @param string
	 * @return
	 */
	static String formatString(String string){
		if (string != null){
			string = string.replaceAll("/", "//");
			string = "'" + string + "'";
		}
		return string;
	}

//	/**
//	 * MySqlConnection.sqlQuery takes a sql statement intended only as a query to retrieve a dataset from the museumitem table
//	 * @param sqlStatement
//	 * @return resultset of sql query
//	 * @throws ClassNotFoundException
//	 * @throws SQLException
//	 */
//	public static ResultSet MySqlConnection.sqlQuery(String sqlStatement) throws ClassNotFoundException, SQLException{
//		Class.forName("com.mysql.jdbc.Driver");
//		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/MuseumInformationSystem", "root", "sschlosser3");
//		PreparedStatement statement = con.prepareStatement(sqlStatement);
//		return statement.executeQuery();
//	}
//
//	/**
//	 * MySqlConnection.sqlUpdate takes a sql statement intended only as an update, delete, or insert to the msueumitem table
//	 * @param sqlStatement
//	 * @throws SQLException
//	 * @throws ClassNotFoundException
//	 */
//	public static void MySqlConnection.sqlUpdate(String sqlStatement) throws SQLException, ClassNotFoundException{
//		Class.forName("com.mysql.jdbc.Driver");
//		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/MuseumInformationSystem", "root", "sschlosser3");
//		PreparedStatement statement = con.prepareStatement(sqlStatement);
//		statement.executeUpdate();
//	}

	/**
	 * Method is used as a test case, called from guestView in view folder
	 */
	public static void museumtest(){
		
//		ArrayList mylist = (ArrayList) find();
//		
		ArrayList mylist = new ArrayList();
		int[] location = {-1,-1};
		MuseumItem item1 = new MuseumItem("Z3","First functional program-controlled Turing-complete computer, the Z3, which "
				+ "became operational in May 1941. Thanks to this machine and its predecessors, Konrad Zuse is often regarded"
				+ " as the inventor of the computer. Using 2,300 relays, the Z3 used floating point binary arithmetic and had "
				+ "a 22-bit word length. The original Z3 was destroyed in a bombing raid of Berlin in late 1943. However, Zuse "
				+ "later supervised a reconstruction of the Z3 in the 1960s.", null,null,null, location);
		
		MuseumItem item2 = new MuseumItem("Atanasoff–Berry Computer (ABC)","Built at Iowa State College (now University), "
				+ "the ABC was designed and built by Professor John Vincent Atanasoff and graduate student Cliff Berry "
				+ "between 1939 and 1942. The ABC was at the center of a patent dispute relating to the invention of the "
				+ "computer, which was resolved in 1973 when it was shown that ENIAC co-designer John Mauchly had come to "
				+ "examine the ABC shortly after it became functional. Atanasoff was declared the originator of several "
				+ "basic computer ideas, but the computer as a concept was declared un-patentable and thus was freely open "
				+ "to all. This result has been referred to as the dis-invention of the computer. A full-scale "
				+ "reconstruction of the ABC was completed in 1997 and proved that the ABC machine functioned as Atanasoff "
				+ "had claimed.", "C:\\\\MISDatabase\\\\Generation1\\\\ABC.jpg", null, null, location);
		mylist.add(item1);
		mylist.add(item2);
		update(mylist);
		
		System.out.println((((MuseumItem) mylist.get(0)).getName().toString()));
	}
}

