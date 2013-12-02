package view;

import model.Exhibit;

/**
 * Provides a visual interface to create and alter the museum's exhibits
 * Created November 26th 2013
 * @author Sara
 */
public class ExhibitEditView {

	/**
	 * Opens a window to allow input of a new exhibit
	 * @return an exhibit
	 */
	Exhibit createExhibit(){
		int[] location = {0,0};
		String exhibitName = "Default";
		String exhibitDescription = "Default";
		Exhibit exhibit = new Exhibit(exhibitName, exhibitDescription, location);
		//Not sure of purpose to return exhibit
		return exhibit;
	}
	
	/**
	 * Opens a window to edit an existing exhibit
	 * @return an updated exhibit
	 */
	Exhibit editExhibit(Exhibit exhibit){
		//Not sure of purpose to return exhibit
		return exhibit;
	}
}
