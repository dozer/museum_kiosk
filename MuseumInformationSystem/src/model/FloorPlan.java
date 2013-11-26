package model;

/**
 * The Floor plan module is intended to allow an administrator user
 * to change the blueprints for the museum itself, as well as rearrange 
 * the walls, rooms, and exhibits inside the building
 * Created November 26th 2013
 * @author Sara
 */
public class FloorPlan {

	public enum Structure{
		OUTSIDEWALL, INSIDEWALL, HALLWAY, DOOR, EXHBIT, STAIRS
	}
	
	Structure type;
	
	/**
	 * Floorplan class constructor
	 * @param type
	 */
	public FloorPlan(Structure type){
		this.type = type;
	}
	
	/**
	 * Sets the type of the building structure such as outside and inside walls, 
	 * 	hallways, doors, exhibits, etc
	 * @param type, representing a type of building structure
	 */
	void setType(Structure type){
		this.type = type;
	}
	
	/** 
	 * Returns the name of the type of building structure the item is
	 * @return a string representing a type of building structure
	 */
	String getType(){
		return type.toString();
	}
}
