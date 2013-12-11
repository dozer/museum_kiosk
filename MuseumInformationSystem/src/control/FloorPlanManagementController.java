package control;

import java.util.ArrayList;

import model.FloorPlan;
import enums.Structure;
import access.FloorPlanDAO;

/**
 * The purpose of the Floor Plan Manager Controller is take the coordinate and
 * object chosen in Floor Plan Edit View, and to input that object in to that
 * coordinate in the Floor Plan Model. The Floor Plan Management Controller also
 * manages a list of the current and previous two floor plans. Floor Plan
 * Management handles falling back to the previous floor plan designs, as well
 * as adding new FloorPlans to the list, and remove old Floor Plans from the
 * list Created November 26th 2013
 *
 * @author Sara, Joe, Casey
 *
 */
public class FloorPlanManagementController {

    private FloorPlan[] stack = new FloorPlan[3];
    private FloorPlan[] undo = new FloorPlan[3];


    /**
     * Edits the Floor plan objects which could be the structure type, x, or y
     * coordinate.
     *
     * @param location representing the x and y coordinate
     * @param type representing the structure type of the floor plan
     */
    void editFloorPlan(int[] location, String type) {
        FloorPlan fp = stack[0];
        stack[0].setType(location, type);
        
        ArrayList floor = stack[0].getFloorPlan();
        String[][] floortype = (String[][]) floor.get(0);
        String[][] flooritem = (String[][]) floor.get(1);
        
        FloorPlanDAO.updateFloorPlan(floortype, flooritem, 0);
        addToFloorPlanList(fp);
    }

    /**
     * Move takes the object currently in the floor plan in location1 and moves
     * it to the object in location2.
     *
     * @param location1 representing the old x and y coordinate on the grid
     * @param location2 representing the new x and y coordinate on the grid
     */
    void move(int[] location1, int[] location2) {
        FloorPlan fp = stack[0];
        String str = stack[0].getType(location2);
        stack[0].setType(location2, stack[0].getType(location1));
        stack[0].setType(location1, str);
        
        ArrayList floor = stack[0].getFloorPlan();
        String[][] floortype = (String[][]) floor.get(0);
        String[][] flooritem = (String[][]) floor.get(1);
        
        FloorPlanDAO.updateFloorPlan(floortype, flooritem, 0);
        addToFloorPlanList(fp);
    }

    /**
     * Falls back to the previous floor plan
     *
     */
    void fallbackToFloorPlan() {
        
        stack[0] = stack[1];
        
        ArrayList floor = stack[1].getFloorPlan();
        String[][] floortype = (String[][]) floor.get(0);
        String[][] flooritem = (String[][]) floor.get(1);
        
        FloorPlanDAO.updateFloorPlan(floortype, flooritem, 0);
        stack[1] = stack[2];
        
        ArrayList floor2 = stack[2].getFloorPlan();
        String[][] floortype2 = (String[][]) floor.get(0);
        String[][] flooritem2 = (String[][]) floor.get(1);
        
        FloorPlanDAO.updateFloorPlan(floortype, flooritem, 1);
        stack[2] = null;

    }

    /**
     * Moves the queue back for the old floor plans
     *
     * @param fp representing the previous floor plan
     */
    void addToFloorPlanList(FloorPlan fp) {
        stack[2] = stack[1];
        
        ArrayList floor = stack[1].getFloorPlan();
        String[][] floortype = (String[][]) floor.get(0);
        String[][] flooritem = (String[][]) floor.get(1);
        
        FloorPlanDAO.updateFloorPlan(floortype, flooritem, 2);
        
        stack[1] = fp;
        
        ArrayList floor2 = fp.getFloorPlan();
        String[][] floortype2 = (String[][]) floor.get(0);
        String[][] flooritem2 = (String[][]) floor.get(1);
        
        FloorPlanDAO.updateFloorPlan(floortype, flooritem, 1);
    }
}