package model;
import java.util.ArrayList;

import enums.Structure;

/**
 * The Floor plan module is intended to allow an administrator user
 * to change the blueprints for the museum itself, as well as rearrange 
 * the walls, rooms, and exhibits inside the building
 * Created November 26th 2013
 * @author Sara & Jack
 */
public class FloorPlan {

	String[][] floorPlanType;
	String[][] floorPlanItem;

	/**
	 * 
	 */
	public FloorPlan(int size) {
		setFloorPlanSize(size);
		initializeFloorPlan();
	}
	/**
	 * Floor plan class constructor
	 * @param location representing the location of the cell
	 * @param type representing the structure type of the cell
	 */
	public FloorPlan(int[] location, String type, String itemName){
		this.floorPlanType[location[0]][location[1]] = type;
		this.floorPlanItem[location[0]][location[1]] = itemName;
	}
	
	public void setFloorPlanType(String[][] floorPlanType){
		this.floorPlanType = floorPlanType;
	}
	
	public void setFloorPlanItem(String[][] floorPlanItem){
		this.floorPlanItem = floorPlanItem;
	}
	
	

	/**
	 * Sets the type of the building structure such as outside and inside walls, 
	 * 	hallways, doors, exhibits, etc
	 * @param type, representing a type of building structure
	 * @param location , location in the floor plan
	 */
	public void setType(int[] location, String type){
		this.floorPlanType[location[0]][location[1]] = type;
	}

	/** 
	 * Returns the name of the type of building structure the item is
	 * @param  location, the location in the floor plan
	 * @return a string representing a type of building structure
	 */
	public String getType(int[] location){
		return floorPlanType[location[0]][location[1]];
	}

	/**
	 * Returns the name of the type of building structure the item is
	 * @param  location, the location in the floor plan
	 * @return a string representing a type of building structure
	 */
    public String getType(int x, int y) {
    	return floorPlanType[x][y];
    }
        /** 
	 * Returns the entire building structure in a two-dimensional array
	 * @return a string representing the whole building structure
	 */
	public ArrayList getFloorPlan() {
		ArrayList floorplan = new ArrayList();
		
		floorplan.add(floorPlanType);
		floorplan.add(floorPlanItem);
		
		return floorplan;
	}

	public void setFloorPlanSize(int size) {
		floorPlanType = new String[size][size];
		floorPlanItem = new String[size][size];
	}

	public void initializeFloorPlan() {
		for(int i = 0; i < floorPlanType.length; i++)
		{
			for(int j = 0; j < floorPlanType.length; j++)
			{
				floorPlanType[i][j] = "";
			}
		}
}
	}