package control;

import model.FloorPlan;	//might need to be removed
import enums.Structure;

/**
 * The purpose of the Floor Plan Manager Controller is take the coordinate and object 
 * chosen in Floor Plan Edit View, and to input that object in to that coordinate in the
 * Floor Plan Model.  The Floor Plan Management Controller also manages a list of the current
 * and previous two floor plans.  Floor Plan Management handles falling back to the previous 
 * floor plan designs, as well as adding new FloorPlans to the list, and remove old Floor Plans 
 * from the list
 * Created November 26th 2013
 * @author Sara
 *
 */
public class FloorPlanManagementController {

	private FloorPlan[] stack = new FloorPlan[3];
	//undo is added to allocate space for user error in floor plan editing
	private FloorPlan[] undo = new FloorPlan[3];

	/**
	 * Edits the Floor plan objects which could be the structure type,
	 * x, or y coordinate.
	 * @param location representing the x and y coordinate
	 * @param type representing the structure type of the floor plan
	 */
	void editFloorPlan(int[] location, String type){
		stack[0].setType(location, type);
	}

	/**
	 * Move takes the object currently in the floor plan in location1
	 * and moves it to the object in location2.  
	 * @param location1 representing the old x and y coordinate on the grid
	 * @param location2 representing the new x and y coordinate on the grid
	 */
	void move(int[] location1, int[] location2){
		String str = stack[0].getType(location2);
		stack[0].setType(location2, stack[0].getType(location1));
		stack[0].setType(location1, str);
	}

	/**
	 * Falls back to the chosen floor plan
	 * @param fp representing the old floor plan
	 */
	void fallbackToFloorPlan(){
		undo[2] = undo[1];
		undo[1] = undo[0];
		undo[0] = stack[0];
		stack[0] = stack[1];
		stack[1] = stack[2];
		stack[2] = null;

	}

	/**
	 * Adds the new Floor Plan created by editFloorPlan to the list of FloorPlans
	 * @param fp representing a new floorplan
	 */
	void addToFloorPlanList(FloorPlan fp){
		stack[2] = stack[1];
		stack[1] = stack[0];
		stack[0] = fp;
	}

	/**
	 * Removes last floor plan from the floor plan list.
	 */
	void removeFromFloorPlanList(){
		//not sure if neccessary

	}
}
