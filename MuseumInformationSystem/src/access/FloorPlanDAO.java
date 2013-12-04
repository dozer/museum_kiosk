package access;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import enums.Structure;
import model.FloorPlan;

/**
 * The purpose of this module is to populate FloorPlan objects with their corresponding data 
 * from the database
 * Created November 27th 2013
 * @author Sara, Joe
 *
 */
public class FloorPlanDAO {

	/**
	 * Queries the database to populate a collection of Floor plan objects
	 * @num representing the stack (version of floorplan)
	 * @return a list of Floor Plan objects populated with data
	 */
//	public static List<FloorPlan>findCurrentFloorPlan2(int num){
//		List<FloorPlan> list= new ArrayList<FloorPlan>();
//		ResultSet result;
//		try {
//			result = sqlQuery("Select StructureType LocationX, LocationY FROM FloorPlan" + num);
//
//			while(result.next()){
//				int[] location = {result.getInt(2), result.getInt(3)};
//				FloorPlan item = new FloorPlan(location, result.getString(1));
//				//Structure type		x, y locations
//				list.add(item);
//			}	
//
//		} catch (ClassNotFoundException | SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return (List<FloorPlan>) list;
//	}

	public static String[][] findFloorPlan(int num){
		String[][] floorplan = new String[18][18];
		ResultSet result;
		try {
			result = sqlQuery("Select StructureType, LocationX, LocationY FROM FloorPlan" + num);

			while(result.next()){
				floorplan[result.getInt(2)][result.getInt(3)] = result.getString(1);
				// x location		y location			structure type
			}	

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return floorplan;
	}

	/**
	 * Updates entire database by dropping the previous table and inserting values back into the table based on
	 * any changes that were made to the list of floorplan items
	 * @param num representing the stack (version of floorplan)
	 * @param floorplan representing the floorplan of structure types
	 */
	public static void updateFloorPlan(String[][] floorPlan, int num){
		try {
			sqlUpdate("DELETE FROM FloorPlan" + num);

			for(int i = 0; i < floorPlan[0].length; i++){
				for(int j = 0; j < floorPlan[1].length; j++){
					if (floorPlan[i][j] == null)
						floorPlan[i][j] = "Space";
					
					sqlUpdate("INSERT INTO FloorPlan" + num + " VALUES('"
							+ floorPlan[i][j] + "', " + i + ", " + j + ")");
				};
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
	public static void floorplantest(){        
		String[][] mylist = new String[18][18];
		mylist[0][0] = "Wall";
		mylist[0][1] = "Wall";
		mylist[0][2] = "Item";
		mylist[1][0] = "Item";
		mylist[1][1] = "Space";
		
		updateFloorPlan(mylist, 0);
		
		String[][] testlist = findFloorPlan(0);
		for(int i = 0; i < testlist[0].length; i++){
			for(int j = 0; j < testlist[1].length; j++){
				System.out.println(testlist[i][j]);
			}
		}	
	}



}
