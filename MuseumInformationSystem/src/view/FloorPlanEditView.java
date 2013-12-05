package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import access.ExhibitDAO;
import model.CoordinateObject;
import model.Exhibit;
import model.FloorPlan;

/**
 * Provides a visual interface to create and alter the floor plan of the museum
 * Created November 26th 2013
 * @author Sara
 */
public class FloorPlanEditView extends JFrame {
	private static final int N = 18;				
    private static final int SIZE = 60;
    static CoordinateObject[][] gridObject = new CoordinateObject[N][N];
    static String currentSelectedObject = "Wall";
    //static CoordinateObject currentObject;
    static String currentObjectString = "Wall";
    static int currentObjectX;
    static int currentObjectY;
    static FloorPlan floorplan;
    List<Exhibit> exhibitList;
    JButton wall;
    JButton exhibit;
    JButton item;
    JButton space;    
    
    /**
     * Create the Panel, as well as all coordinates and object buttons.  The User will
     * be able to click a button to choose which type of object (Wall, Exhibit, Item, Open Space)
     * to populate coordinates within the Floor Plan.
     */
    public FloorPlanEditView() {
    	initializeArray();
    	exhibitList = ExhibitDAO.find();
    	//currentObject = new CoordinateObject("Wall", -1, -1, "", "", "");
    	currentObjectString = "Wall";
    	floorplan.setFloorPlanSize(N);    	
    	
    	JPanel coordGrid = new JPanel(new GridLayout(N,N));
    	coordGrid.setPreferredSize(new Dimension(N * SIZE, N * SIZE));
    	for(int i = 0; i < N * N; i++)
    		coordGrid.add(new Button(i));
    	
    	JPanel bottomGrid = new JPanel(new GridLayout(4,1));
    	
    	createButtons();
    	
    	bottomGrid.add(wall);
    	bottomGrid.add(exhibit);
    	bottomGrid.add(item);
    	bottomGrid.add(space);
    	
    	GridBagLayout gridBag = new GridBagLayout();
    	
    	JPanel mainPanel = new JPanel(gridBag);
    	
    	mainPanel.add(coordGrid);
    	mainPanel.add(bottomGrid);
    	this.add(mainPanel);
    }
    
    /**
     * Generic class to create buttons that will be used as coordinates.  When a
     * button is clicked, it will take on the properties of the selected object
     * @author caseytcaprice
     *
     */
    private static class Button extends JButton {
    	int x;
    	int y;
    	
    	/**
    	 * Turns button green
    	 */
    	void makeGreen()
    	{
    		this.setBackground(Color.green);
    	}
    	
    	/**
    	 * Turns button red
    	 */
    	void makeRed()
    	{
    		this.setBackground(Color.red);
    	}
    	
    	/**
    	 * Turns button blue
    	 */
    	void makeBlue()
    	{
    		this.setBackground(Color.blue);
    	}
    	
    	/**
    	 * Turns button into open space
    	 */
    	void makeSpace()
    	{
    		this.setBackground(Color.white);
    	}
    	
    	/**
    	 * Button Constructor, creates a generic white button with a coordinate.
    	 * @param i, representing the number of Button created.  Used for coordinates
    	 */
        public Button(int i) {
            super(i / N + "," + i % N);
            this.setOpaque(true);
            this.setBorderPainted(true);
            this.setBackground(Color.white);
                        
            this.x = i / N;
            this.y = i % N;
            
            this.addActionListener(new ActionListener() {
            	public void actionPerformed(ActionEvent e)
            	{
            		currentObjectX = x;
            		currentObjectY = y;
            		
            		int[] tempLocation = new int[2];
            		tempLocation[0] = x;
            		tempLocation[1] = y;
            		
            		floorplan.setType(tempLocation, currentObjectString);
            		//gridObject[x][y] = currentObject;
            		
            		//switch(currentSelectedObject)
            		switch(currentObjectString) //currentObjectType
            		{
            			case("Wall"):
            				makeRed();
            				break;
            			case("Exhibit"):
            				makeGreen();
            				break;
            			case("Item"):
            				makeBlue();
            				break;
            			case("Space"):
            				makeSpace();
            			default:
            				break;            			
            		}
            	}
            });            
        }
    }

    /**
     * Displays Panel
     */
    public void display() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    /**
     * Set current coordinate to an object type, Wall, Exhibit, Item, or Open Space.
     * @param type Object type, Wall, Exhibit, Item, Open Space
     */
    private void setCurrent(String type){
    	if(type == "Wall") {
    		currentSelectedObject = "Wall";
    		wall.setFont(space.getFont().deriveFont(Font.BOLD | Font.ITALIC));
    	}
    	else {
    		wall.setFont(wall.getFont().deriveFont(Font.PLAIN));
    	}
    	
    	if(type == "Exhibit") {
    		currentSelectedObject = "Exhibit";
    		exhibit.setFont(space.getFont().deriveFont(Font.BOLD | Font.ITALIC));
    	}
    	else {
    		exhibit.setFont(wall.getFont().deriveFont(Font.PLAIN));
    	}
    	
    	if(type == "Item") {
    		currentSelectedObject = "Item";
    		item.setFont(space.getFont().deriveFont(Font.BOLD | Font.ITALIC));
    	}
    	else {
    		item.setFont(wall.getFont().deriveFont(Font.PLAIN));
    	}
    	
    	if(type == "Space") {
    		currentSelectedObject = "Space";
    		space.setFont(space.getFont().deriveFont(Font.BOLD | Font.ITALIC));
    	}
    	else {
    		space.setFont(wall.getFont().deriveFont(Font.PLAIN));
    	}    	
    }
    
    /**
     * Create Wall, Exhibit, Item, and Open Space buttons
     */
    public void createButtons() {
    	wall = new JButton();
    	wall.setText("Wall");
    	wall.setForeground(Color.red);
    	wall.setFont(wall.getFont().deriveFont(Font.BOLD | Font.ITALIC));
    	wall.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e)
        	{
        		currentObjectString = "Wall";
        		//= new CoordinateObject("Wall", -1, -1, "", "", "");
        		setCurrent("Wall");
        	}
        });
    	
    	exhibit = new JButton();
    	exhibit.setText("Exhibit");
    	exhibit.setForeground(Color.green);
    	exhibit.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e)
        	{
        		//JList exhibitPopup = new JList(exhibitList.toArray());
        		
        		//String s = (String) JOptionPane.showInputDialog(null, "Choose an Exhibit", "Choose an Exhibit", JOptionPane.QUESTION_MESSAGE, null, exhibitList.toArray(), exhibitList.toArray()[0]);
        		//popUp window populated with Exhibits
        		//e = Exhibit chosen by user
        		//currentObject = new Object("Exhibit", -1, -1, e.getName(), e.getDescription(), e.getMedia());
        		currentObjectString = "Exhibit"; //change to Exhibit.name when hooked up****
        		//currentObjectType = "Exhibit";
        		//currentObject = new CoordinateObject("Exhibit", -1, -1, "Exhibit Name", "Exhibit Description", "Exhibit Media");
        		setCurrent("Exhibit"); 
        	}
        });
    	
    	item = new JButton();
    	item.setText("Museum Item");
    	item.setForeground(Color.blue);
    	item.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e)
        	{
        		//popUp window populated with Exhibits
        		//e = Exhibit chosen by user
        		//popUp window populated with e.getMuseumItems
        		//m = MuseumItem chosen by user
        		//currentObject = new Object("Item", -1, -1, m.getName(), m.getDescription, m.getMedia());
        		currentObjectString = "Item"; //change to Item.name when hooked up****
        		//currentObjectType = "Item";
        		//currentObject = new CoordinateObject("Item", -1, -1, "Item Name", "Item Description", "Item Media");
        		setCurrent("Item");
        	}
        });
    	
    	space = new JButton();
    	space.setText("Open Space");
    	space.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e)
        	{
        		currentObjectString = "Space";
        		//currentObject = new CoordinateObject("Space", -1, -1, "", "", "");
        		setCurrent("Space");
        	}
        });
    }
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new FloorPlanEditView().display();
            }
        });
    }
    
    public CoordinateObject[][] getGridObject() {
    	return this.gridObject;
    }
    
    public FloorPlan getFloorPlan() {
    	return floorplan;
    }
    
    public void initializeArray(){
    	CoordinateObject temp = new CoordinateObject("Space", -1, -1, "", "", "");
    	for(int i = 0; i < N; i++)
    	{
    		for(int j = 0; j < N; j++)
    		{
    			gridObject[i][j] = temp;
    		}
    	}
    }
    /*
    /**
     * An Object that will be contained within an array.  Will hold all information about what is
     * held within coordinate
     * @author caseytcaprice
     *
     */
    /*
    private class CoordinateObject {
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
    }*/
}
