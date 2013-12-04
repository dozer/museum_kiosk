package model;

/**
 * An Object that will be contained within an array.  Will hold all information about what is
 * held within coordinate
 * @author caseytcaprice
 *
 */
public class CoordinateObject {
	private String type;
	private int x;
	private int y;
	private String name;
	private String description;
	private String media;
	
	public CoordinateObject(String type, int x, int y, String name, String description, String media) {
		this.type = type;
		this.x = x;
		this.y = y;
		this.name = name;
		this.description = description;
		this.media = media;    		
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public String getType()
	{
		return this.type;
	}
}
