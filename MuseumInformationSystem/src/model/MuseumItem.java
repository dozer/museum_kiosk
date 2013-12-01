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
 * @author Sara
 *
 */
public class MuseumItem {

	private String name;
	private String description;
	private String image;
	private String audio;
	private String video;
	
	/**
	 * MuseumItem class constructor
	 * @param name
	 * @param description
	 * @param image
	 * @param audio
	 * @param video
	 */
	public MuseumItem(String name, String description, String image, 
			String audio, String video){
		this.name = name;
		this.description = description;
		this.image = image;
		this.audio = audio;
		this.video = video;
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
		return description;	//must retrieve description from database
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
	
	
}
