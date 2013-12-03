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
	static List<MuseumItem> find(){
		List<MuseumItem> list= new ArrayList<MuseumItem>();
		ResultSet result;
		try {
			result = sqlQuery("Select ItemName, ItemDescription, ItemImage, ItemAudio, ItemVideo, LocationX, LocationY FROM MuseumItem");

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
	static void update(List<MuseumItem> list){
		try {
			sqlUpdate("DELETE FROM MuseumItem");

			for(int i = 0; i < list.size(); i++){
				
				int[] location = list.get(i).getLocation();
				sqlUpdate("INSERT INTO MuseumItem VALUES('"
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
	

//	public static void addMuseumItem(String title, String description, String image, String audio, String video){
//		try {
//			if (image != null){
//				image = image.replaceAll("/", "//");
//				image = "'" + image + "'";
//			}
//			if (audio != null){
//				audio = audio.replaceAll("/","//");
//				audio = "'" + audio + "'";
//			}
//			if (video != null){
//				video = video.replaceAll("/", "//");
//				video = "'" + video + "'";
//			}
//
//			sqlUpdate("INSERT INTO MuseumItem VALUES(default, '" + title + "', '" + description + "', " + image + ", " + audio + ", " + video + ", " + 1 + ")");
//		} catch (ClassNotFoundException | SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//	/**
//	 * Sets the name of an item
//	 * @param name representing the new name to be assigned to the item
//	 */
//	public static void  setName(String name){
//		try {
//			sqlUpdate("UPDATE MUSEUMITEM SET ItemTitle = '" + name + "'");
//		} catch (ClassNotFoundException | SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//	/**
//	 * Sets the description for an item
//	 * @param description representing the description of the item
//	 */
//	public static void  setDesciption(String description, String name){
//		try {
//			sqlUpdate("UPDATE MUSEUMITEM SET ItemDescription = '" + description + "' WHERE ItemTitle = '" + name + "'");
//		} catch (ClassNotFoundException | SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//	/**
//	 * Sets the directory path for the items image file
//	 * @param path representing the directory path
//	 */
//	public static void  setImage(String path, String name){
//		try {
//			if (path != null){
//				path = path.replaceAll("/", "//");
//				path = "'" + path + "'";
//			}
//			sqlUpdate("UPDATE MUSEUMITEM SET ItemImage = '" + path + "' WHERE ItemTitle = '" + name + "'");
//		} catch (ClassNotFoundException | SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//	/**
//	 * Sets the directory path for the item's audio file
//	 * @param path representing the directory path
//	 */
//	public static void setAdudio(String path, String name){
//		try {
//			if (path != null){
//				path = path.replaceAll("/", "//");
//				path = "'" + path + "'";
//			}
//			sqlUpdate("UPDATE MUSEUMITEM SET ItemAudio = '" + path + "' WHERE ItemTitle = '" + name + "'");
//		} catch (ClassNotFoundException | SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//	/**
//	 * Sets the directory path for the item's video file
//	 * @param path representing the directory path
//	 */
//	public static void  setVideo(String path, String name){
//		try {
//			if (path != null){
//				path = path.replaceAll("/", "//");
//				path = "'" + path + "'";
//			}
//			sqlUpdate("UPDATE MUSEUMITEM SET ItemVideo = '" + path + "' WHERE ItemTitle = '" + name + "'");
//		} catch (ClassNotFoundException | SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

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
		
		ArrayList mylist = (ArrayList) find();
		
//		ArrayList mylist = new ArrayList();
//		int[] location = {-1,-1};
//		MuseumItem item = new MuseumItem("Z3","First functional program-controlled Turing-complete computer, the Z3, which "
//				+ "became operational in May 1941. Thanks to this machine and its predecessors, Konrad Zuse is often regarded"
//				+ " as the inventor of the computer. Using 2,300 relays, the Z3 used floating point binary arithmetic and had "
//				+ "a 22-bit word length. The original Z3 was destroyed in a bombing raid of Berlin in late 1943. However, Zuse "
//				+ "later supervised a reconstruction of the Z3 in the 1960s.", null,null,null, location);
//		mylist.add(item);
//		update(mylist);
		
		System.out.println((((MuseumItem) mylist.get(0)).getName().toString()));
	}
}

