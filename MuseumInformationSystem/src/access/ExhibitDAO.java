package access;

import java.util.ArrayList;
import java.util.List;

import model.Exhibit;
import model.MuseumItem;

/**
 * The purpose of this module is to populate Exhibit objects with their 
 * corresponding data from the database
 * Created November 27th 2013
 * @author Sara
 *
 */
public class ExhibitDAO {

	/**
	 * Queries the database to populate a collection of Exhibit objects
	 * @return a list of Exhibit objects populated with data
	 */
	List<Exhibit> find(){
		List<Exhibit> list = new ArrayList<Exhibit>();
		return list;
	}
	
	/**
	 * Sets the location of collection of items
	 * @param location representing the location inside the museum
	 */
	void setExhibitLocation(int[] location){
		//I changed the location parameter from a string into an array of size 2 to stay 
		//	formalized with other objects representing location
	}
	
	/**
	 * Adds a museum item to the collection
	 * @param item representing an item inside the museum
	 */
	void addItem(MuseumItem item){
		
	}
}
