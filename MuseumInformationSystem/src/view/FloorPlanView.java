package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import control.DirectionController;
import access.ExhibitDAO;
import access.MuseumItemDAO;
import model.Exhibit;
import model.FloorPlan;
import model.MuseumItem;


public class FloorPlanView extends JFrame {
	private static final int N = 10;				
    private static final int SIZE = 60;
    static int currentObjectX;
    static int currentObjectY;
    static FloorPlan floorplan;
    static boolean canGetDirections = false;
    
    
    static JTextArea title;// = new JTextField();
    static JTextArea description;// = new JTextArea();
    static BufferedImage image;
    static JLabel imageLabel;
    static ImageIcon imageIcon;
    static JButton directions;
//    JButton image;
//    JButton audio;
//    JButton video;
    
	public FloorPlanView(ArrayList floorplan) {
		this.floorplan = new FloorPlan(10);
    	String[][] tempType = (String[][]) floorplan.get(0);
    	String[][] tempItem = (String[][]) floorplan.get(1);
    	
    	this.floorplan.setFloorPlanType(tempType);
    	this.floorplan.setFloorPlanItem(tempItem);
		createGUI();
		
	}
	
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
    	
    	/*
    	void color()
    	{
    		switch(floorplan.getType(x,y))
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
    	*/
    	
    	/**
    	 * Button Constructor, creates a generic white button with a coordinate.
    	 * @param i, representing the number of Button created.  Used for coordinates
    	 */
        public Button(int i) {
            //super(i / N + "," + i % N);
        	super(floorplan.getItem(i / N, i % N));
        	setToolTipText(floorplan.getItem(i / N, i % N));
            this.setOpaque(true);
            this.setBorderPainted(true);
            this.setBackground(Color.white);
                        
            this.x = i / N;
            this.y = i % N;
            
            switch(floorplan.getType(this.x, this.y)) //currentObjectType
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
            
            //color();
            
            this.addActionListener(new ActionListener() {
            	public void actionPerformed(ActionEvent e)
            	{
            		currentObjectX = x;
            		currentObjectY = y;
            		int element = 0;
            		String elementName = "";
            		String elementDescription = "";
            		String elementPicture = "";
            		String elementVideo = "";
            		String elementAudio = "";
            		
            		switch(floorplan.getType(x, y))
            		{
            			case "Exhibit":
            				canGetDirections = true;
            				List<Exhibit> exhibitList = ExhibitDAO.find();
            				for(int i = 0; i < exhibitList.size(); i++)
            				{
            					if(exhibitList.get(i).getExhibitName().equals(getText()))
            					{
            						element = i;
            						break;
            					}
            				}
            				
            				elementName = exhibitList.get(element).getExhibitName();
            				elementDescription = exhibitList.get(element).getExhibitDescription();
            				break;
            			case "Item":
            				canGetDirections = true;
            				List<MuseumItem> itemList = MuseumItemDAO.find();
            				for(int i = 0; i < itemList.size(); i++)
            				{
            					if(itemList.get(i).getName().equals(getText()))
            					{
            						element = i;
            						break;
            					}
            				}
            				
            				elementName = itemList.get(element).getName();
            				elementDescription = itemList.get(element).getDescription();
            				elementPicture = itemList.get(element).getImage();
            				elementVideo = itemList.get(element).getVideo();
            				elementAudio = itemList.get(element).getAudio();
            				break;
            			default:
            				canGetDirections = false;
            				break;
            		}
            		
            		String tempName = getText();
            		
            		if(tempName.equals(""))
            		{
            			title.setText("");
            			description.setText("");
            		}
            		else
            		{
            			title.setText(elementName);
            			description.setText(elementDescription);
            			if(elementPicture != null)
            			{
            				try {
								String s = "images\\" + elementPicture;
            					image = ImageIO.read(new File("C:\\Development\\git\\museum_kiosk\\MuseumInformationSystem\\src\\view\\images\\" + elementPicture));
            					imageIcon = new ImageIcon(image);
            					Image img = imageIcon.getImage();
            					Image nwimg = img.getScaledInstance(130, 130,  java.awt.Image.SCALE_SMOOTH);
            					imageLabel.setIcon(new ImageIcon(nwimg));//.getImage().getScaledInstance(imageLabel.getHeight(), imageLabel.getWidth(),Image.SCALE_SMOOTH));
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
            				
            			}
            		}            		
            	}
            });            
        }
    }
	
	private void createGUI() {
    	JPanel coordGrid = new JPanel(new GridLayout(N,N));
    	coordGrid.setPreferredSize(new Dimension(N * SIZE, N * SIZE));
    	
    	for(int i = 0; i < N * N; i++)
    		coordGrid.add(new Button(i));
    	
    	title = new JTextArea();
    	description = new JTextArea();
    	imageLabel = new JLabel();
    	description.setLineWrap(true);
    	title.setPreferredSize(new Dimension(25, 50));
    	description.setPreferredSize(new Dimension(250,200));
    	
    	directions = new JButton();
    	directions.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e)
        	{
        		if(canGetDirections)
        		{
	        		int[] start = new int[2];
	        		int[] end = new int[2];
	        		start[0] = 5;
	            	start[1] = 9;
	            	end[0] = currentObjectX;
	            	end[1] = currentObjectY;
	        		DirectionController dc = new DirectionController(floorplan.getFloorPlanItem(), floorplan.getFloorPlanType(),start,end);
	        		dc.find();
	        		JOptionPane.showMessageDialog(null, dc.getDirections());
        		}
        		else
        			JOptionPane.showMessageDialog(null, "Please choose an Item or Exhibit");
        	}
        });
    	directions.setText("Get Directions");
    	directions.setPreferredSize(new Dimension(150,150));
    	//imageLabel.setPreferredSize(new Dimension(250,200));
    
    	//title.setText("TeestTestTest");
    	//description.setText("TestTestTestTEst");
    	
    	JPanel bottomGrid = new JPanel();
    	bottomGrid.setLayout(new BoxLayout(bottomGrid,BoxLayout.Y_AXIS));
    	
    	createBoxes();
    	
    	bottomGrid.add(title);
    	bottomGrid.add(Box.createVerticalGlue());
    	bottomGrid.add(description);
    	bottomGrid.add(imageLabel);
    	bottomGrid.add(directions);
//    	bottomGrid.add(image);
//    	bottomGrid.add(audio);
//    	bottomGrid.add(video);
    	
    	JPanel mainPanel = new JPanel();
    	
    	mainPanel.add(coordGrid);
    	mainPanel.add(bottomGrid);
    	
    	this.add(mainPanel);
    }
	
	
	public void createBoxes(){
		
	}
	
	public void display() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
	}
	
//	public static void main(String[] args) {
//        EventQueue.invokeLater(new Runnable() {
//
//            @Override
//            public void run() {
//                //new FloorPlanView().display();
//            }
//        });
//    }
}
