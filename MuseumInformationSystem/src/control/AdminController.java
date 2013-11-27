package control;

import view.AdminView;
import javafx.event.ActionEvent;
//import java.awt.event.ActionEvent;

/**
 * The purpose of the Admin Controller was to handle which window
 * the Admin View GUI would display, User Management, Floor Plan Management, 
 * or Exhibit Management
 * Created November 26th 2013
 * @author Sara
 *
 */
public class AdminController {

	/**
	 * Determines what type of View should be displayed depending on the ActionEvent's
	 * name.  Verify that user has access to view clicked.  Returns name of view(User Edit,
	 * Exhibit Edit, FloorPlanEdit) to AdminView to display the correct view in the GUI
	 * @param ae represents the action event that holds the name of the button clicked
	 * @return the view to be displayed (User edit, Exhibit Edit, or Floor plan edit)
	 */
	AdminView determineAdminView(ActionEvent ae){
		AdminView adminview = new AdminView();
		return adminview;	
	}
}
