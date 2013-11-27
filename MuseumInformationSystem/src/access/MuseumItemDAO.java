package access;

import java.util.ArrayList;
import java.util.List;

import model.MuseumItem;

/**
 * The purpose of this module is to populate MuseumItem objects with
 * their corresponding data from the database
 * Created November 27th 2103
 * @author Sara
 *
 */
public class MuseumItemDAO {

	/**
	 * Queries the database to populate a collection of MuseumItem objects
	 * @return a list of Museum item objects populated with data
	 */
	List<MuseumItem> find(){
		List<MuseumItem> list= new ArrayList<MuseumItem>();
		return list;
	}
	
	/**
	 * Sets the name of an item
	 * @param name representing the new name to be assigned to the item
	 */
	void setName(String name){
		
	}
	
	/**
	 * Sets the description for an item
	 * @param description representing the description of the item
	 */
	void setDesciption(String description){
		
	}
	
	/**
	 * Sets the directory path for the items image file
	 * @param path representing the directory path
	 */
	void setImage(String path){
		
	}
	
	/**
	 * Sets the directory path for the item's audio file
	 * @param path representing the directory path
	 */
	void setAdudio(String path){
		
	}
	
	/**
	 * Sets the directory path for the item's video file
	 * @param path representing the directory path
	 */
	void setVideo(String path){
		
	}
}

