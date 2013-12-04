package control;

import view.FloorPlanEditView;

import java.util.Arrays;

import model.CoordinateObject;

/**
 * The purpose of the Direction Controller is to guide the User from the
 * terminal to the Exhibit of choice.  The Directon Controller will be using 
 * Dijkstra's algorithm
 * Created November 26th 2013
 * @author Sara
 *
 */
public class DirectionController {

    final static int TRIED = 2;
    final static int PATH = 3;
    
    private static int[][] FLOORPLAN = { 
        { 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1 },
        { 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0, 0, 1 },
        { 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0 },
        { 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1 },
        { 1, 0, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 1 },
        { 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1 },
        { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
        { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 } 
    };
    
    private static int[][] FLOORPLAN2 = { 
        { 1, 1, 1, 0, 1 },
        { 1, 0, 1, 1, 1 },
        { 0, 0, 0, 0, 1 },
        { 1, 1, 1, 0, 1 },
        { 1, 0, 1, 0, 1 },
        { 1, 0, 1, 1, 1 },
        { 1, 0, 0, 0, 0 },
        { 1, 1, 1, 1, 1 } 
    };

    public static void main(String[] args) {
    	
    	int[] a = new int[2];
    	int[] b = new int[2];
    	a[0] = 0;
    	a[1] = 1;
    	b[0] = 5;
    	b[1] = 5;
    	CoordinateObject[][] coordFloorplanTest = new CoordinateObject[1][1];
    	DirectionController dc = new DirectionController(coordFloorplanTest, a,b);
        boolean solved = dc.find();
        System.out.println(dc.toString());
    }

    private CoordinateObject[][] coordFloorplan;
    private int[][] intFloorplan;
    private int height;
    private int width;
    private int startX;
    private int startY;
    private int findX;
    private int findY;
    private String directionString;

    private int[][] directions;

    public DirectionController(CoordinateObject[][] floorplan, int[] terminalPoint, int[] destinationPoint) {
        this.coordFloorplan = floorplan;
        this.intFloorplan = convertToIntArray(floorplan);
        this.height = intFloorplan.length;
        this.width = intFloorplan[0].length;
        
        this.startX = terminalPoint[1];
        this.startY = terminalPoint[0];
        
        this.findY = destinationPoint[0];		//item coordinate
        this.findX = destinationPoint[1];    //item coordinate

        this.directions = new int[height][width];
    }

    public boolean find() {
        return traverse(this.startX,this.startY);
    }

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
            directionString += checkForExhibitEastWest(i-1, j);
            return true;
        }
        // East
        if (traverse(i, j + 1)) {
            directions[i][j + 1] = PATH;
            directionString += checkForExhibitNorthSouth(i, j+1);
            return true;
        }
        // South
        if (traverse(i + 1, j)) {
            directions[i + 1][j] = PATH;
            directionString += checkForExhibitEastWest(i+1, j);
            return true;
        }
        // West
        if (traverse(i, j - 1)) {
            directions[i][j - 1] = PATH;
            directionString += checkForExhibitNorthSouth(i, j-1);
            return true;
        }

        return false;
    }

    private boolean isEnd(int i, int j) {
        return i == findX  && j == findY;
    }

    private boolean isValid(int i, int j) {
        if (inRange(i, j) && isOpen(i, j) && !isTried(i, j)) {
            return true;
        }

        return false;
    }

    private boolean isOpen(int i, int j) {
        return intFloorplan[i][j] == 1 || intFloorplan[i][j] == 8 || intFloorplan[i][j] == 9;
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
    
    public int[][] convertToIntArray(CoordinateObject[][] objectArray) {
    	int height = objectArray.length; 
    	int width = objectArray[0].length;
    	
    	int[][] intArray = new int[height][width];
    	
    	for(int i = 0; i < height; i++)
    	{
    		for(int j = 0; j < width; j++)
    		{
    			if(objectArray[i][j].getType() == "Space")
    				intArray[i][j] = 1;
    			else if(objectArray[i][j].getType() == "Exhibit")
    				intArray[i][j] = 8;
    			else if(objectArray[i][j].getType() == "Item")
    				intArray[i][j] = 9;
    			else if(objectArray[i][j].getType() == "Wall")
    				intArray[i][j] = 0;
    		}
    	}
    	
    	return intArray;
    }
    
    /**
	 * Output directions from the user terminal to the chosen exhibit, by using other 
	 * exhibits for landmarks.  Uses Dikstra's algorithm
	 * @param currentPosition representing the position of the user
	 * @param exhibitPosition representing the position of the chosen exhibit
	 */
	
    	void outputDirections(){
		
	}
	
	/**
	 * 
	 */
	
    	String checkForExhibitEastWest(int i, int j){
		int eastX = i;
		int eastY = j;
		int westX = i;
		int westY = j;
		
		while(eastX != 0 && westX != this.intFloorplan[0].length)
		{
			//check east
			if(this.intFloorplan[eastX][eastY] == 8 || this.intFloorplan[eastX][eastY] == 9)
				return "pass " + this.coordFloorplan[eastX][eastY].getType() + " to the East";
			
			//check west
			if(this.intFloorplan[westX][westY] == 8 || this.intFloorplan[westX][westY] == 9)
				return "pass " + this.coordFloorplan[westX][westY].getType() + " to the West"; 
			
			eastX--;
			westX++;
		}
		return "";
	}
	
	/**
	 * 
	 */
	String checkForExhibitNorthSouth(int i, int j){
		int northX = i;
		int northY = j;
		int southX = i;
		int southY = j;
		
		while(southX != this.intFloorplan.length && northY != 0)
		{
			//check north
			if(this.intFloorplan[northX][northY] == 8 || this.intFloorplan[northX][northY] == 9)
				return "pass " + this.coordFloorplan[northX][northY].getType() + " to the North";
			
			//check south
			if(this.intFloorplan[southX][southY] == 8 || this.intFloorplan[southX][southY] == 9)
				return "pass " + this.coordFloorplan[southX][southY].getType() + " to the South"; 
			
			northY--;
			southY++;
		}
		return "";
	}
	
}
