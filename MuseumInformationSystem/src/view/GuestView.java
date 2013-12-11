package view;

import static javafx.concurrent.Worker.State.FAILED;

import java.awt.Dimension;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import access.FloorPlanDAO;
import netscape.javascript.JSObject;
import model.MuseumItem;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.JFXPanel;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.TouchPoint.State;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebEvent;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * The purpose of the guest view module is to provide a visual
 * representation of the information on the museum's exhibits for 
 * the patrons of the museum to see.  This is the highest level
 * of the primary function, so it receives data from all the lower 
 * levels.  It is also where the user inputs are received, and used 
 * to call functions from the lower levels.
 * Created November 26th 2013
 * @author Sara, Amanda
 */
public class GuestView extends JFrame {
    private Scene scene;
    private final JFXPanel jfxpanel = new JFXPanel();
    private WebEngine engine;
    private JPanel panel = new JPanel();
    
    private int height = 700;
    private int width = 1200;
    
    public GuestView() {
    	super();
    	init();
    }
    
    private void init() {
    	createScene();
   // 	jfxpanel.setSize(height, width);
    	getContentPane().add(jfxpanel);
        setPreferredSize(new Dimension(1200, 700));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
    }
    
    private void createScene() {
            Platform.runLater(new Runnable() {
                @Override 
                public void run() {
                    WebView view = new WebView();
                	engine = view.getEngine();
                	engine.load(getURL("../MCH_html/index.html"));
                	jfxpanel.setScene(new Scene(view));
                }});	
    }
    
    public String getURL(String str) {
    	URL newUrl = getClass().getResource(str);
    	return newUrl.toExternalForm();
    }
    
//    public static void main(String[] args) {
    public static void begin(){
    	GuestView browser = new GuestView();
    	browser.setVisible(true);
    }
    
    public void transitionToSwing() {
    	FloorPlanEditView fpev = new FloorPlanEditView(FloorPlanDAO.findFloorPlan(0));
    	fpev.setVisible(true);
    	setVisible(false);
    }
}


////    
////    
////    @Override public void start(Stage stage) {
////        // create the scene
////        stage.setTitle("Museum Information System");
////        scene = new Scene(new Browser(),1200,900, Color.web("#666970"));
////        stage.setScene(scene);     
////        stage.show();
////    }
// 
//    public static void main(String[] args) {
//   //     launch(args);
//    }
//    
//
//    /**
//     * Displays the home screen that users will access
//     */
//    void displayHome(){
//    }
//    
//    /**
//     * Displays the search page that will allow users
//     * to search the museum's exhibits
//     */
//    void displaySearch(){
//    }
//    
//    /**
//     * Display the information about the item being queried
//     * @param item, representing the museum item which holds all
//     * its data (description and file paths)
//     */
//    void displayMuseumItem(MuseumItem item){
//    }
//    
//}
//
//class Browser extends Region {
// 
//    final WebView browser = new WebView();
//    final WebEngine webEngine = browser.getEngine();
//     
//    public Browser() {
//        //apply the styles
//
//        // load the web page
//        
//        
//        URL url = getClass().getResource("../MCH_html/index.html");
//        webEngine.load(url.toExternalForm());
//        
//        //add the web view to the scene
//        getChildren().add(browser);
//        
//        JSObject win = 
//                (JSObject) webEngine.executeScript("window");
//            win.setMember("app", new JavaApp());
//    }
//    
//    private Node createSpacer() {
//        Region spacer = new Region();
//        HBox.setHgrow(spacer, Priority.ALWAYS);
//        return spacer;
//    }
// 
//    @Override protected void layoutChildren() {
//        double w = getWidth();
//        double h = getHeight();
//        layoutInArea(browser,0,0,w,h,0, HPos.CENTER, VPos.CENTER);
//    }
// 
//    @Override protected double computePrefWidth(double height) {
//        return 850;
//    }
// 
//    @Override protected double computePrefHeight(double width) {
//        return 600;
//    }
//}
//
//
//class JavaApp {
//	public void transitionToSwing() {
//		System.out.println("Javascript upcall worked");
//		/*
//		String[] arg = null;
//		FloorPlanEditView swing = new FloorPlanEditView();
//		swing.main(arg); */
//	}
//}
