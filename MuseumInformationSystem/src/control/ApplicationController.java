package control;

import access.ExhibitDAO;
import access.FloorPlanDAO;
import access.MuseumItemDAO;
import model.CoordinateObject;
import model.FloorPlan;
import view.FloorPlanEditView;

public class ApplicationController {

	public static void main(String[] args) {
    	//MuseumItemDAO.museumtest();
		FloorPlanEditView fpev = new FloorPlanEditView(FloorPlanDAO.findFloorPlan(0));
    	fpev.display();
    	
    	//while(fp.isShowing())
    	//{
    		
    	//}
    	FloorPlan fp = fpev.getFloorPlan();
    	//CoordinateObject[][] floorplan = fp.getGridObject();
    	int start[] = new int[2];
    	int end[] = new int[2];
    	start[0] = 1;
    	start[1] = 1;
    	end[0] = 4;
    	end[1] = 4;
    	//DirectionController dc = new DirectionController(fp,start, end);
		//dc.find();
		//dc.outputDirections();
    	//DirectionController dc = new DirectionController();
        //boolean solved = dc.find();
        //System.out.println(dc.toString());
        
		
		//ExhibitDAO.exhibittest();
    }
}
