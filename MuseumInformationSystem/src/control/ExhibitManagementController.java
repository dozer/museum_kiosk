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
	 * Creates a new Exhibit with the given parameters and adds it to the Exhibit list
	 * @param name
	 * @param description
	 * @param itemList
	 */
	/*
	ArrayList<Exhibit> addExhibit(ArrayList<Exhibit> list, String name, String description, ArrayList<MuseumItem> itemList){
		list.add(new Exhibit(name, description, itemList));
		
		return list;
		//changed []object itemlist to an arraylist because an array's size is not dynamic
		//Im a little confused from the DDD definition.  
		//		did you mean name and description of museum item??
		//		if so, then description is not needed to add item to exhibit collection
	}
	*/
	/** 
	 * remove Exhibit(String name)
	 * @param name
	 */
	/*
	ArrayList<Exhibit> removeExhibit(ArrayList<Exhibit> list, String name){
		for(Exhibit e: list)
		{
			if(e.getName() == name)
			{
				list.remove(e);
				
				break;
			}
		}
		
		return list;
		//again is this removing an exhibit or a museum item??
			//if it is a museum item, i recommend that in the database we create a column for enabled/disabled
			//and instead removing it, we disable it to prevent an accidental data loss
	}
	*/
	
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
	void removeFromExhibit(String name, String itemName){
		
	}
}
