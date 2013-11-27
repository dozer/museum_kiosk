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

	/**
	 * Edits the Floor plan objects which could be the structure type,
	 * x, or y coordinate.
	 * @param coordinateX representing the x coordinate
	 * @param coordinateY representing the y coordinate
	 * @param type representing the structure type of the floor plan
	 */
	void editFloorPlan(int coordinateX, int coordinateY, Structure type){
		//coordinate x and y params might be easier to manage if written as a array of size 2
	}
	
	/**
	 * Move takes the object currently in the floor plan in spot coordinatesX and Y
	 * and moves it to the object in the spot coordinateZ and W
	 * @param coordinateX representing the old x coordinate on the grid
	 * @param coordinateY representing the old y coordinate on the grid
	 * @param coordinateZ representing the new x coordinate on the grid
	 * @param coordinateW representing the new y coordinate on the grid
	 */
	void move(int coordinateX, int coordinateY, int coordinateZ, int coordinateW){
		// this method may have to be rewritten.  old x, y params dont seem unnessesary
	}
	
	/**
	 * Falls back to the chosen floor plan
	 * @param fp representing the old floor plan
	 */
	void fallbackToFloorPlan(FloorPlan fp){
		//method may not need param for floor plan. instead maybe just pop the most recent from stack??
		//also floorplan object is misunderstood. floorplan from model represents objects/structures such as walls
		//	doors, outterwalls, etc.  not an actual floor plan.
	}
	
	/**
	 * Adds the new Floor Plan created by editFloorPlan to the list of FloorPlans
	 * @param fp representing a new floorplan
	 */
	void addToFloorPlanList(FloorPlan fp){
		//Floorplan object is misunderstood. Floorplan from model represents objects/structures such as walls
		//	doors, outterwalls, etc.  not an actual floor plan.
	}
	
	/**
	 * Removes last floor plan from the floor plan list.
	 */
	void removeFromFloorPlanList(){
		
	}
}
