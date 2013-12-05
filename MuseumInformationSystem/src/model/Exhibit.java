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
	private ArrayList<MuseumItem> museumItemList = new ArrayList<MuseumItem>();
	private String exhibitName;
	private String exhibitDescription;
	
	/**
	 * Exhibit class constructor
	 * @param location
	 */
	public Exhibit(String exhibitName, String exhibitDescription, int[] location){
		setExhibitLocation(location);
		this.exhibitName = exhibitName;
		this.exhibitDescription = exhibitDescription;
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
	 * Sets the exhibit name
	 * @param exhibitName, representing the exhibit name
	 */
	public void setExhibitName(String exhibitName){
		this.exhibitName = exhibitName;
	}
	
	/**
	 * Returns the exhibit name
	 * @return a string representing the exhibit name
	 */
	public String getExhibitName(){
		return this.exhibitName;
	}
	
	/**
	 * Sets the exhibit description
	 * @param exhibitDescription, representing the exhibit description
	 */
	public void setExhibitDescription(String exhibitDescription){
		this.exhibitDescription = exhibitDescription;
	}
	
	/**
	 * Returns the exhibit description
	 * @return a string representing the exhibit description
	 */
	public String getExhibitDescription(){
		return this.exhibitDescription;
	}
	/**
	 * Adds a museuem item to the collection/exhibit
	 * @param item, representing a museum item to be added to the collection
	 */
	public void addItem(MuseumItem item){
		museumItemList.add(item);
	}
	
	/**
	 * Removes a given item from a collection
	 * @param item, representing a museum item to be removed from the collection
	 */
	public void removeItem(MuseumItem item){
		museumItemList.remove(item);
	}
        
        /**
         * Sets the museum item list
         * @param museumItemList the new museum item list to be set
         */
        public void setMuseumItemList(List<MuseumItem> museumItemList) {
            this.museumItemList = new ArrayList(museumItemList);
        }
	
	/**
	 * Returns the Exhibit's list of museum items
	 * @return the Exhibit's list of museum items
	 */
	public ArrayList<MuseumItem> getMuseumItemList(){
		return this.museumItemList;
	}
}
