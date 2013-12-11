package model;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This module represents an item inside the museum.
 * A single item will have a name, written description,
 * an image, and a location.  If available, it is also
 * possible for the item to have audio or video capabilities
 * Created November 25th 2013
 * @author Sara, Casey
 *
 */
public class MuseumItem {

	private String name;
	private String description;
	private String image;
	private String audio;
	private String video;
	private int[] location;
	
	/**
	 * MuseumItem class constructor
	 * @param name
	 * @param description
	 * @param image
	 * @param audio
	 * @param video
	 */
	public MuseumItem(String name, String description, String image, String audio, String video,
			int location[]){
		this.name = name;
		this.description = description;
		this.image = image;
		this.audio = audio;
		this.video = video;
		this.location = location;
		
	}
	
	/**
	 * Sets the name of the item
	 * @param name, a string representing the new name to be 
	 * 		assigned to the item
	 */
	public void setName(String name){
		this.name = name;
	}
	
	/**
	 * Sets the description for the item
	 * @param description, a string representing the description of the item
	 */
	public void setDescription(String description){
		this.description = description;
	}
	
	/**
	 * Sets the directory path for the item's image file
	 * @param path, a string representing the directory path
	 */
	public void setImage(String path){
		this.image = path;
	}
	
	/**
	 * Sets the directory path for the item's audio file
	 * @param path, a string representing the directory path
	 */
	public void setAudio(String path){
		this.audio = path;
	}
	
	/**
	 * Sets the directory path for the item's video file
	 * @param path, a string representing the directory path
	 */
	public void setVideo(String path){
		this.video = path;
	}
	
	/**
	 * Sets the location for the item on the museum's map
	 * @param location, an array of size 2 representing the items location
	 */
	public void setlocation(int[] location){
		this.location = location;
	}
	
	/**
	 * Returns the name of the museum item
	 * @return a string representing the name of the item
	 */
	public String getName(){
		return name;
	}
	/**
	 * Returns the description of the museum item
	 * @return a string representing the description of an item
	 */
	public String getDescription(){
		return description;
	}
	
	/**
	 * Returns the directory path for the item's audio file
	 * @return a string representing the directory path
	 */
	public String getAudio(){
		return audio;
	}
	
	/**
	 * Returns a directory path for the item's image file
	 * @return a string representing the directory path
	 */
	public String getImage(){
		return image;
	}
	
	/**
	 * Returns a directory path for the item's video file
	 * @return a string representing the directory path
	 */
	public String getVideo(){
		return video;
	}
	
	/**
	 * Returns an array of size 2 representing the x and y axis on the museums grid/map
	 * @return an array representing the location on the museum map
	 */
	public int[] getLocation(){
		return location;
	}
	
}
