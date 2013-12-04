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

	String[][] floorPlan;

	/**
	 * Floor plan class constructor
	 * @param location representing the location of the cell
	 * @param type representing the structure type of the cell
	 */
	public FloorPlan(int[] location, String type){
		this.floorPlan[location[0]][location[1]] = type;
	}

	/**
	 * Sets the type of the building structure such as outside and inside walls, 
	 * 	hallways, doors, exhibits, etc
	 * @param type, representing a type of building structure
	 * @param location , location in the floor plan
	 */
	public void setType(int[] location, String type){
		this.floorPlan[location[0]][location[1]] = type;
	}

	/** 
	 * Returns the name of the type of building structure the item is
	 * @param  location, the location in the floor plan
	 * @return a string representing a type of building structure
	 */
	public String getType(int[] location){
		return floorPlan[location[0]][location[1]];
	}
        
        /** 
	 * Returns the entire building structure in a two-dimensional array
	 * @return a string representing the whole building structure
	 */
        public String[][] getFloorPlan() {
            return floorPlan;
        }
        
        public void setFloorPlanSize(int size) {
        	floorPlan = new String[size][size];
        }
        
        public void initializeFloorPlan() {
        	for(int i = 0; i < floorPlan.length; i++)
        	{
        		for(int j = 0; j < floorPlan.length; j++)
        		{
        			floorPlan[i][j] = "";
        		}
        	}
        }
}
