package control;

import model.CoordinateObject;
import view.FloorPlanEditView;

public class ApplicationController {

	public static void main(String[] args) {
    	FloorPlanEditView fp = new FloorPlanEditView();
    	fp.display();
    	
    	//while(fp.isShowing())
    	//{
    		
    	//}
    	
    	CoordinateObject[][] floorplan = fp.getGridObject();
    	int start[] = new int[2];
    	int end[] = new int[2];
    	start[0] = 1;
    	start[1] = 1;
    	end[0] = 4;
    	end[1] = 4;
    	DirectionController dc = new DirectionController(floorplan,start, end);
		dc.find();
		dc.outputDirections();
    	//DirectionController dc = new DirectionController();
        //boolean solved = dc.find();
        //System.out.println(dc.toString());
    }
}
