package model;

import java.util.ArrayList;
import java.util.List;

public class Exhibit {

	private int[] location = new int[2];
	private List<MuseumItem> exhibitList = new ArrayList<MuseumItem>();
	
	/**
	 * Sets the grid location of the collection of items
	 * @param location, representing the location inside the museum
	 */
	void setExhibitLocation(int[] location){
		this.location[0] = location[0];
		this.location[1] = location [1];
	}
	
	/**
	 * Returns an array of size 2 with the (x,y) 
	 * 	Coordinates of an exhibit's location
	 * @return a string representing the location of the exhibit
	 * 	inside the museum
	 */
	int[] getExhibitLocation(){
		return this.location;
	}
	
	/**
	 * Adds a museuem item to the collection/exhibit
	 * @param item, representing a museum item to be added to the collection
	 */
	void addItem(MuseumItem item){
		exhibitList.add(item);
	}
	
	/**
	 * Removes a given item from a collection
	 * @param item, representing a museum item to be removed from the collection
	 */
	void removeItem(MuseumItem item){
		exhibitList.remove(item);
	}
}
