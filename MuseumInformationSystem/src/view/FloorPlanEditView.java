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

import control.ExhibitManagementController;
import access.ExhibitDAO;
import access.FloorPlanDAO;
import access.MuseumItemDAO;
import model.CoordinateObject;
import model.Exhibit;
import model.FloorPlan;
import model.MuseumItem;

/**
 * Provides a visual interface to create and alter the floor plan of the museum
 * Created November 26th 2013
 * @author Sara, Casey
 */
public class FloorPlanEditView extends JFrame {
	private static final int N = 10;				
    private static final int SIZE = 60;
    static CoordinateObject[][] gridObject = new CoordinateObject[N][N];
    static int currentObjectX;
    static int currentObjectY;
    //static FloorPlan floorplan;
    
    static FloorPlan currentfloorplan;
    static FloorPlan newfloorplan;
    
    static String currentFloorPlanType = "Wall";
    static String currentFloorPlanItem = "Wall";
    
    static String newFloorPlanType = "Wall";
    static String newFloorPlanItem = "Wall";
    
    List<Exhibit> exhibitList;
    List<MuseumItem> museumItemList;
    JButton wall;
    JButton exhibit;
    JButton item;
    JButton space;
    JButton save;
    
    /**
     * Create the Panel, as well as all coordinates and object buttons.  The User will
     * be able to click a button to choose which type of object (Wall, Exhibit, Item, Open Space)
     * to populate coordinates within the Floor Plan.
     */
    public FloorPlanEditView(ArrayList floorplan) {
    	newfloorplan = new FloorPlan(10);
    	String[][] tempType = (String[][]) floorplan.get(0);
    	String[][] tempItem = (String[][]) floorplan.get(1);
    	
    	newfloorplan.setFloorPlanType(tempType);
    	newfloorplan.setFloorPlanItem(tempItem);
    	currentfloorplan = newfloorplan;
    	
    	currentFloorPlanType = "Wall";
    	currentFloorPlanItem = "Wall";
    	
    	createGUI();
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
            //super(i / N + "," + i % N);
        	super(currentfloorplan.getItem(i / N, i % N));
        	this.setOpaque(true);
            this.setBorderPainted(true);
            this.setBackground(Color.white);
                        
            this.x = i / N;
            this.y = i % N;
            
            switch(currentfloorplan.getType(this.x, this.y)) //currentObjectType
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
            
            this.addActionListener(new ActionListener() {
            	public void actionPerformed(ActionEvent e)
            	{
            		currentObjectX = x;
            		currentObjectY = y;
            		
            		int[] tempLocation = new int[2];
            		tempLocation[0] = x;
            		tempLocation[1] = y;
            		
            		newfloorplan.setFloorPlanType(x, y, newFloorPlanType);
            		newfloorplan.setFloorPlanItem(x, y, newFloorPlanItem);
            		//newfloorplan.setType(tempLocation, newFloorPlanType);
            		setText(newFloorPlanItem);
            		setToolTipText(newFloorPlanItem);
            		
            		switch(newFloorPlanType) //currentObjectType
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
            		if(!newFloorPlanType.equals("Wall"))
            		{
            			newFloorPlanItem = "";
            			newFloorPlanType = "Space";
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
    private void setCurrent(String type, String name){
    	if(type == "Wall") {
    		newFloorPlanType = "Wall";
    		wall.setFont(space.getFont().deriveFont(Font.BOLD | Font.ITALIC));
    	}
    	else {
    		wall.setFont(wall.getFont().deriveFont(Font.PLAIN));
    	}
    	
    	if(type == "Exhibit") {
    		
    		newFloorPlanType = "Exhibit";
    		exhibit.setFont(space.getFont().deriveFont(Font.BOLD | Font.ITALIC));
    	}
    	else {
    		exhibit.setFont(wall.getFont().deriveFont(Font.PLAIN));
    	}
    	
    	if(type == "Item") {
    		newFloorPlanType = "Item";
    		item.setFont(space.getFont().deriveFont(Font.BOLD | Font.ITALIC));
    	}
    	else {
    		item.setFont(wall.getFont().deriveFont(Font.PLAIN));
    	}
    	
    	if(type == "Space") {
    		newFloorPlanType = "Space";
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
        		newFloorPlanType = "Wall";
        		newFloorPlanItem = "";
        		//setNewFloorPlanSpot(newFloorPlanItem, newFloorPlanType);
        		setCurrent(newFloorPlanType, newFloorPlanItem);
        	}
        });
    	
    	exhibit = new JButton();
    	exhibit.setText("Exhibit");
    	exhibit.setForeground(Color.green);
    	exhibit.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e)
        	{
        		exhibitList = ExhibitDAO.find();
        		ArrayList<String> arraylist = new ArrayList<String>() ;
        		for(int i = 0; i< exhibitList.size(); i++){
        			arraylist.add(exhibitList.get(i).getExhibitName());
        		}
        		
        		newFloorPlanType = "Exhibit";
        		newFloorPlanItem = (String) JOptionPane.showInputDialog(null, "Choose an Exhibit", "Choose an Exhibit", JOptionPane.QUESTION_MESSAGE, null, arraylist.toArray(), arraylist.toArray()[0]);
        		//setNewFloorPlanSpot(newFloorPlanItem, newFloorPlanType);
        		setCurrent(newFloorPlanType, newFloorPlanItem);
        	}
        });
    	
    	item = new JButton();
    	item.setText("Museum Item");
    	item.setForeground(Color.blue);
    	item.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e)
        	{
        		museumItemList = MuseumItemDAO.find();
        		ArrayList<String> arraylist = new ArrayList<String>() ;
        		for(int i = 0; i< museumItemList.size(); i++){
        			arraylist.add(museumItemList.get(i).getName());
        		}
        		newFloorPlanType = "Item";
        		newFloorPlanItem = (String) JOptionPane.showInputDialog(null, "Choose an Item", "Choose an Exhibit", JOptionPane.QUESTION_MESSAGE, null, arraylist.toArray(), arraylist.toArray()[0]);
        		//setNewFloorPlanSpot(newFloorPlanItem, newFloorPlanType);
        		setCurrent(newFloorPlanType, newFloorPlanItem);
        	}
        });
    	
    	space = new JButton();
    	space.setText("Open Space");
    	space.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e)
        	{
        		newFloorPlanType = "Space";
        		newFloorPlanItem = "";
        		//setNewFloorPlanSpot(newFloorPlanItem, newFloorPlanType);
        		setCurrent(newFloorPlanType, newFloorPlanItem);
        	}
        });
    	
    	save = new JButton();
    	save.setText("Save Floorplan");
    	save.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e)
    		{
    			FloorPlanDAO.updateFloorPlan(newfloorplan.getFloorPlanType(), newfloorplan.getFloorPlanItem(), 0);
    			setVisible(false);
    			new LoginView();
    			
    		}
    	});
    }
    
    
    
//    public static void main(String[] args) {
//        EventQueue.invokeLater(new Runnable() {
//
//            @Override
//            public void run() {
//                //new FloorPlanEditView().display();
//            }
//        });
//    }
    
    public CoordinateObject[][] getGridObject() {
    	return this.gridObject;
    }
    
    public FloorPlan getFloorPlan() {
    	return newfloorplan;
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
    
    private void setNewFloorPlanSpot(String floorPlanItem, String floorPlanType) {
    	
    	newfloorplan.setFloorPlanItem(currentObjectX, currentObjectY, floorPlanItem);
    	newfloorplan.setFloorPlanType(currentObjectX, currentObjectY, floorPlanType);
    }
    
    private void createGUI() {
    	JPanel coordGrid = new JPanel(new GridLayout(N,N));
    	coordGrid.setPreferredSize(new Dimension(N * SIZE, N * SIZE));
    	
    	for(int i = 0; i < N * N; i++)
    		coordGrid.add(new Button(i));
    	
    	JPanel bottomGrid = new JPanel(new GridLayout(5,1));
    	
    	createButtons();
    	
    	bottomGrid.add(wall);
    	bottomGrid.add(exhibit);
    	bottomGrid.add(item);
    	bottomGrid.add(space);
    	bottomGrid.add(save);
    	
    	GridBagLayout gridBag = new GridBagLayout();
    	
    	JPanel mainPanel = new JPanel(gridBag);
    	
    	mainPanel.add(coordGrid);
    	mainPanel.add(bottomGrid);
    	this.add(mainPanel);
    }
}