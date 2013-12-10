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
	static String[][] floorPlanType = new String[18][18];
	static String[][] floorPlanItem = new String[18][18];
	/**
	 * Queries the database to populate a collection of Floor plan objects
	 * @num representing the stack (version of floorplan)
	 * @return a list of Floor Plan objects populated with data
	 */
	public static ArrayList findFloorPlan(int num){
		ArrayList floorplan = new ArrayList();

		ResultSet result;
		try {
			result = sqlQuery("Select LocationX, LocationY, StructureType, ItemName FROM FloorPlan" + num);

			while(result.next()){
				floorPlanType[result.getInt(1)][result.getInt(2)] = result.getString(3);
					// x location		y location			structure type
				floorPlanItem[result.getInt(1)][result.getInt(2)] = result.getString(4);
			}	

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		floorplan.add(floorPlanType);
		floorplan.add(floorPlanItem);
		return floorplan;
	}

	/**
	 * Updates entire database by dropping the previous table and inserting values back into the table based on
	 * any changes that were made to the list of floorplan items
	 * @param num representing the stack (version of floorplan)
	 * @param floorplan representing the floorplan of structure types
	 */
	public static void updateFloorPlan(String[][] floorPlanType, String[][] floorPlanItem, int num){
		try {
			sqlQuery("set global max_connections = 500");
			sqlUpdate("DELETE FROM FloorPlan" + num );

			for(int i = 0; i < floorPlanType[0].length; i++){
				for(int j = 0; j < floorPlanType[1].length; j++){
					if (floorPlanType[i][j] == null){
						floorPlanType[i][j] = "";
						floorPlanItem[i][j] = "";
					}
					if (floorPlanType[i][j] == "Wall" || floorPlanType[i][j] == "Space"){
						floorPlanItem[i][j] = "";
					}
					
					
					sqlUpdate("INSERT INTO FloorPlan" + num + " VALUES("
						 + i + ", " + j + ", '" + floorPlanType[i][j] + "', '" + floorPlanItem[i][j] + "')");
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
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/MuseumInfoSystem", "root", "sschlosser3");
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
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/MuseumInfoSystem", "root", "sschlosser3");
		PreparedStatement statement = con.prepareStatement(sqlStatement);
		statement.executeUpdate();
	}

	/**
	 * Method is used as a test case, called from guestView in view folder
	 */
	public static void floorplantest(){        
		String[][] mylist1 = new String[18][18];
		String[][] mylist2 = new String[18][18];
		mylist1[0][0] = "Wall";
		mylist1[0][1] = "Wall";
		
		mylist1[0][2] = "Item";
		mylist2[0][2] = "ExhibitItem";
		
		mylist1[1][0] = "Item";
		mylist2[1][0] = "ExhibitITem2";
		
		mylist1[1][1] = "Space";
		
		updateFloorPlan(mylist1, mylist2, 0);
		
		
//		System.out.println(mylist2[0][2]);
		ArrayList floorplan = findFloorPlan(0);
		String[][] testlist = (String[][]) floorplan.get(0);
		String[][] testlist2 = (String[][]) floorplan.get(1);

		for(int i = 0; i < testlist[0].length; i++){
			for(int j = 0; j < testlist[1].length; j++){
				System.out.println( testlist2[i][j]);
			}
		}	
	}
}