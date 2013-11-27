package access;

import java.util.ArrayList;
import java.util.List;

import enums.Structure;
import model.FloorPlan;

/**
 * The purpose of this module is to populate FloorPlan objects with their corresponding data 
 * from the database
 * Created November 27th 2013
 * @author Sara
 *
 */
public class FloorPlanDAO {

	/**
	 * Queries the database to populate a collection of Floor plan objects
	 * @return a list of Floor Plan objects populated with data
	 */
	List<FloorPlan> find(){
		List<FloorPlan> list = new ArrayList<FloorPlan>();
		return list;
	}
	
	/**
	 * Sets the type of building structure (such as outside wall, inside wall, hallway, 
	 * door, divider, exhibit, etc)
	 * @param type representing a type of building structure
	 */
	void setType(Structure type){
		//I changed the parameter from string to structure. feel free to reverse my change
	}
	
	
}
