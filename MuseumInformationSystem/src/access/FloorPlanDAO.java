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
	 * @return a list of Floor Plan objects populated with data
	 */
	FloorPlan find(){
		FloorPlan floor;
  			ResultSet result;
		int[] index = {0,0};
                try {
                        result = sqlQuery("Select * FROM FloorPlan WHERE Enabled = 1");
                        
                        while(result.next()){
                                floor.setType(index, Structure.valueOf(result.getString(2)));
                                //Not sure what the size of the grid is or if there's a better way to do it.
                                //Feel free to change.
                                if(index[0] == 10) {
                                	index[0] = 0;
                                	index[1]++;
                                }
                                else
                                	index[0]++;
                        }        
                        
                } catch (ClassNotFoundException | SQLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }
                return floor;
	}
	
	/**
	 * Sets the type of building structure (such as outside wall, inside wall, hallway, 
	 * door, divider, exhibit, etc)
	 * @param type representing a type of building structure
	 */
	void setType(Structure type){
		//I changed the parameter from string to structure. feel free to reverse my change
		try {
                        sqlUpdate("UPDATE FLOORPLAN SET Structure = '" + type + "'");
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
                FloorPlan floor = (FloorPlan) find();
                while(index[1] != 11) {
                	System.out.println((floor.getType(index[0], index[1]).toString()));
                	//Not sure what the size of the grid is or if there's a better way to do it.
                        //Feel free to change.
                        if(index[0] == 10) {
                        	index[0] = 0;
                                index[1]++;
                        }
                        else
                                index[0]++;
                }
        }
        
	
	
}
