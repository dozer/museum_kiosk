package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.FloorPlan;


public class FloorPlanView extends JFrame {
	private static final int N = 10;				
    private static final int SIZE = 60;
    static int currentObjectX;
    static int currentObjectY;
    static FloorPlan floorplan;
    
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
            super(i / N + "," + i % N);
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
            		//floorplan.getItem(x, y);            		
            	}
            });            
        }
    }
	
	private void createGUI() {
    	JPanel coordGrid = new JPanel(new GridLayout(N,N));
    	coordGrid.setPreferredSize(new Dimension(N * SIZE, N * SIZE));
    	
    	for(int i = 0; i < N * N; i++)
    		coordGrid.add(new Button(i));
    	
    	
    	
    	JPanel mainPanel = new JPanel();
    	
    	mainPanel.add(coordGrid);
    	this.add(mainPanel);
    }
	
	public void display() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
	}
	
	public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                //new FloorPlanView().display();
            }
        });
    }
}
