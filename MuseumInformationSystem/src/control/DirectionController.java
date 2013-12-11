package control;

import view.FloorPlanEditView;

import java.util.ArrayList;
import java.util.Arrays;

import model.FloorPlan;

/**
 * The purpose of the Direction Controller is to guide the User from the
 * terminal to the Exhibit of choice.  The Directon Controller will be using 
 * Dijkstra's algorithm
 * Created November 26th 2013
 * @author Sara, Casey
 *
 */
public class DirectionController {

    final static int TRIED = 2;
    final static int PATH = 3;
    
    private FloorPlan floorplan;
    private int height;
    private int width;
    private int startX;
    private int startY;
    private int findX;
    private int findY;
    private String directionString = "";
    String[][] floorPlanItem;
    String[][] floorPlanType;
    private int[][] directions;

    /**
     * DirectionController takes a floorplan, and runs Dijkstra's algorithm on it.
     * This will fill intFloorPlan with the shortest rou;te from terminal to destination
     * @param floorplan, representing the floorplan of the museum filled with Walls, Exhibits, Items, Open Space
     * @param terminalPoint, representing the beginning point of the user
     * @param destinationPoint, representing the destination point of the user
     */
    public DirectionController(String[][] floorPlanItem, String[][] floorPlanType, int[] terminalPoint, int[] destinationPoint) {
    	this.floorPlanItem = floorPlanItem;
        this.floorPlanType = floorPlanType;
    	this.height = floorPlanItem.length;
        this.width = floorPlanItem.length;
        
        this.startX = terminalPoint[1];
        this.startY = terminalPoint[0];
        
        this.findY = destinationPoint[0];		//item coordinate
        this.findX = destinationPoint[1];    //item coordinate

        this.directions = new int[height][width];
    }

    /**
     * finds the shortest route to the destination, starting
     * at the terminal
     * @return true or false depending on if the program could find
     * the destination, and it's not hidden behind walls
     */
    public boolean find() {
        return traverse(this.startX,this.startY);
    }
    
    /**
     * traverses to the next point on the map, depending if
     * the coordinate is suitable(not a wall)
     * @param i, representing the x coordinate
     * @param j, representing the y coordinate
     * @return true or false, depending if the program could
     * move onto an adjacent coordinate
     */
    private boolean traverse(int i, int j) {
        if (!isValid(i,j)) {
            return false;
        }

        if ( isEnd(i, j) ) {
            directions[i][j] = PATH;
            return true;
        } else {
            directions[i][j] = TRIED;
        }

        // North
        if (traverse(i - 1, j)) {
            directions[i-1][j] = PATH;
            directionString = checkForExhibitEastWest(i-1, j) + directionString;
            return true;
        }
        // East
        if (traverse(i, j + 1)) {
            directions[i][j + 1] = PATH;
            directionString = checkForExhibitNorthSouth(i, j+1) + directionString;
            return true;
        }
        // South
        if (traverse(i + 1, j)) {
            directions[i + 1][j] = PATH;
            directionString = checkForExhibitEastWest(i+1, j) + directionString;
            return true;
        }
        // West
        if (traverse(i, j - 1)) {
            directions[i][j - 1] = PATH;
            directionString = checkForExhibitNorthSouth(i, j-1)  + directionString;
            return true;
        }

        return false;
    }

    /**
     * isEnd returns true if this coordinate is the destination
     * @param i, representing the x coordinate
     * @param j, representing the y coordinate
     * @return true or false depending on if the coordinates are the destination
     */
    private boolean isEnd(int i, int j) {
        return i == findX  && j == findY;
    }
    
    /**
     * isValid determines if the coordinate is a valid one(in array range, not a wall
     * or if not already tried
     * @param i, representing x coordinate
     * @param j, repres
     * @return
     */
    private boolean isValid(int i, int j) {
        if (inRange(i, j) && isOpen(i, j) && !isTried(i, j)) {
            return true;
        }

        return false;
    }

    private boolean isOpen(int i, int j) {
        return floorPlanType[i][j].equals("Space") || floorPlanType[i][j].equals("Item") || floorPlanType[i][j].equals("Exhibit") || floorPlanType[i][j].equals("");
    }

    private boolean isTried(int i, int j) {
        return directions[i][j] == TRIED;
    }

    private boolean inRange(int i, int j) {
        return inHeight(i) && inWidth(j);
    }

    private boolean inHeight(int i) {
        return i >= 0 && i < height;
    }

    private boolean inWidth(int j) {
        return j >= 0 && j < width;
    }

    public String toString() {
        String s = "";
        for (int[] row : directions) {
            s += Arrays.toString(row) + "\n";
        }

        return s;
    }
    
    /**
	 * Output directions from the user terminal to the chosen exhibit, by using other 
	 * exhibits for landmarks.  Uses Dikstra's algorithm
	 * @param currentPosition representing the position of the user
	 * @param exhibitPosition representing the position of the chosen exhibit
	 */
	
	public String getDirections(){
		return directionString;		
	}
	
	
	/**
	 * check for any exhibits to the east or west of current
	 * coordinate.  Return as string to be output
	 * @param i, coordinate i
	 * @param j, coordinate j
	 * @return string output direction
	 */
	String checkForExhibitEastWest(int i, int j){
		int eastX = i;
		int eastY = j;
		int westX = i;
		int westY = j;
		
		while(eastY != 0 && westY != floorPlanType[0].length)
		{
			//check east
			if(floorPlanType[eastX][eastY].equals("Item") || floorPlanType[eastX][eastY].equals("Exhibit"))
				return "pass " + floorPlanItem[eastX][eastY] + " to the East ";
			
			//check west
			if(floorPlanType[westX][westY].equals("Item") || floorPlanType[westX][westY].equals("Exhibit"))
				return "pass " + floorPlanItem[westX][westY] + " to the West "; 
			
			eastY--;
			westY++;
		} 
		return "";
	}
	
	/**
	 * Check for any exhibits or items to north or south, return 
	 * as string for directions
	 * @param i, coordinate i
	 * @param j, coordinate j
	 * @return string, directions
	 */
	String checkForExhibitNorthSouth(int i, int j){
		int northX = i;
		int northY = j;
		int southX = i;
		int southY = j;
		
		while(southX != floorPlanItem.length && northX != 0)
		{
			//check north
			if(floorPlanType[northX][northY].equals("Item") || floorPlanType[northX][northY].equals("Exhibit"))
				return "pass " + floorPlanItem[northX][northY] + " to the North ";
			
			//check south
			if(floorPlanType[southX][southY].equals("Item") || floorPlanType[southX][southY].equals("Exhibit"))
				return "pass " + floorPlanItem[southX][southX] + " to the South "; 
			
			northX--;
			southX++;
		}
		return "";
	}
	
}
