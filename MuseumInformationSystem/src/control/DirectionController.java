package control;

import view.FloorPlanEditView;
import java.util.Arrays;

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
    
/*
    public static void main(String[] args) {
    	DirectionController dc = new DirectionController(FLOORPLAN);
        boolean solved = dc.find();
        System.out.println(dc.toString());
    }
*/
    private int[][] floorplan;
    private int height;
    private int width;
    private int startX;
    private int startY;
    private int findX;
    private int findY;

    private int[][] directions;

    public DirectionController(int[][] floorplan) {
        this.floorplan = floorplan;
        this.height = floorplan.length;
        this.width = floorplan[0].length;
        findY = this.width - 1;
        findX = this.height - 1;

        this.directions = new int[height][width];
    }

    public boolean find() {
        return traverse(0,0);
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
            return true;
        }
        // East
        if (traverse(i, j + 1)) {
            directions[i][j + 1] = PATH;
            return true;
        }
        // South
        if (traverse(i + 1, j)) {
            directions[i + 1][j] = PATH;
            return true;
        }
        // West
        if (traverse(i, j - 1)) {
            directions[i][j - 1] = PATH;
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
        return floorplan[i][j] == 1 || floorplan[i][j] == 8 || floorplan[i][j] == 9;
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
    /*
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
    */
    /**
	 * Output directions from the user terminal to the chosen exhibit, by using other 
	 * exhibits for landmarks.  Uses Dikstra's algorithm
	 * @param currentPosition representing the position of the user
	 * @param exhibitPosition representing the position of the chosen exhibit
	 */
	void outputDirections(int currentPosition, int exhibitPosition){
		
	}

}
