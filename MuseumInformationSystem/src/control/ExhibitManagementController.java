package control;

import java.util.ArrayList;

import model.Exhibit;
import model.MuseumItem;

/**
 * The purpose of the Exhibit Management Controller is to add,  remove or edit Exhibits in the ExhibitList
 * Created November 26th 2013
 * @author Sara
 *
 */
public class ExhibitManagementController {

	/**
	 * Creates a new Exhibit with the location, name, and description of the Exhibit
	 * @param list, representing current list of Exhibits
	 * @param location, representing the coordinates of the Exhibit on the floor plan
	 * @param name, representing the name of the Exhibit
	 * @param description, representing the description of the Exhibit
	 */
	ArrayList<Exhibit> addExhibit(ArrayList<Exhibit> list, int[] location, String name, String description){
		list.add(new Exhibit(name, description, location));
		
		return list;
	}
	
	/** 
	 * Removes an Exhibit from the current ArrayList of Exhibits
	 * @param list, representing the current list of Exhibits
	 * @param name, representing the name of the Exhibit to remove
	 * @return ArrayList of Exhibits minus the Exhibit to remove
	 */
	ArrayList<Exhibit> removeExhibit(ArrayList<Exhibit> list, String name){
		for(Exhibit e: list)
		{
			if(e.getExhibitName() == name)
			{
				list.remove(e);
				
				break;
			}
		}
		
		return list;
	}
	
	/**
	 * Creates a new Item with the given paramters to the exhibit matching the given name's ItemList
	 * @param name representing the exhibit name
	 * @param newName representing the item name
	 * @param description representing the item description
	 * @param media 
	 */
	void editExhibit(String name, String newName, String description, String media){
		//this method may need to be altered;
		//what is considered media?? museum item model can have image, audio, and video
		// also is the name representing the exhibit name supposed to be the museum item?
	}
	
	/**
	 * Removes the provided item from the given exhibits itemlist
	 * @param name representing the exhibit name
	 * @param itemName representing the museum item name
	 */
	ArrayList<Exhibit> removeMuseumItemFromExhibit(ArrayList<Exhibit> list, String name, String itemName){
		for(Exhibit e: list)
		{
			if(e.getExhibitName() == name)
			{
				for(MuseumItem m: e.getMuseumItemList())
				{
					if(m.getName() == itemName)
					{
						e.removeItem(m);
						break;
					}
				}
			}
		}
		
		return list;		
	}
	
}
