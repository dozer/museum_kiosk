package model;
import enums.Structure;

/**
 * The Floor plan module is intended to allow an administrator user
 * to change the blueprints for the museum itself, as well as rearrange 
 * the walls, rooms, and exhibits inside the building
 * Created November 26th 2013
 * @author Sara & Jack
 */
public class FloorPlan {

	Structure[][] floorPlan;
	
	/**
	 * Floorplan class constructor
	 * @param type
	 */
	public FloorPlan(){
		
	}
	
	/**
	 * Sets the type of the building structure such as outside and inside walls, 
	 * 	hallways, doors, exhibits, etc
	 * @param type, representing a type of building structure
         * @param location , location in the floor plan
	 */
	public void setType(int[] location, Structure type){
		this.floorPlan[location[0]][location[1]] = type;
	}
	
	/** 
	 * Returns the name of the type of building structure the item is
         * @param  location, the location in the floor plan
	 * @return a string representing a type of building structure
	 */
	public Structure getType(int[] location){
		return floorPlan[location[0]][location[1]];
	}
}
