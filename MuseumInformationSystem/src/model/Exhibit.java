package model;

import java.util.ArrayList;
import java.util.List;

/** 
 * The exhibit model essentially represents a collection of Museum Items
 * and their grouped location.  This module consists of creating and deleting
 * groups of exhibits, and adding and removing individual items into that collection
 * Created November 26th 2013
 * @author Sara
 *
 */
public class Exhibit {

	private int[] location = new int[2];
	private List<MuseumItem> exhibitList = new ArrayList<MuseumItem>();
	
	/**
	 * Exhibit class constructor
	 * @param location
	 */
	public Exhibit(int[] location){
		setExhibitLocation(location);
	}
	
	/**
	 * Sets the grid location of the collection of items
	 * @param location, representing the location inside the museum
	 */
	public void setExhibitLocation(int[] location){
		this.location[0] = location[0];
		this.location[1] = location [1];
	}
	
	/**
	 * Returns an array of size 2 with the (x,y) 
	 * 	Coordinates of an exhibit's location
	 * @return a string representing the location of the exhibit
	 * 	inside the museum
	 */
	public int[] getExhibitLocation(){
		return this.location;
	}
	
	/**
	 * Adds a museuem item to the collection/exhibit
	 * @param item, representing a museum item to be added to the collection
	 */
	public void addItem(MuseumItem item){
		exhibitList.add(item);
	}
	
	/**
	 * Removes a given item from a collection
	 * @param item, representing a museum item to be removed from the collection
	 */
	public void removeItem(MuseumItem item){
		exhibitList.remove(item);
	}
}
