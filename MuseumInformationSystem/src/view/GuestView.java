package view;

import java.beans.Statement;


import java.net.URL;

import access.ExhibitDAO;
import access.MuseumItemDAO;
import access.UserDAO;


import model.MuseumItem;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
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
 * @author Sara
 */
public class GuestView extends Application {
    private Scene scene;
    @Override public void start(Stage stage) {
        // create the scene
        stage.setTitle("Museum Information System");
        scene = new Scene(new Browser(),850,600, Color.web("#666970"));
        stage.setScene(scene);     
        stage.show();
    }
 
    public static void main(String[] args) {
        launch(args);
    }
    

    /**
     * Displays the home screen that users will access
     */
    void displayHome(){
    }
    
    /**
     * Displays the search page that will allow users
     * to search the museum's exhibits
     */
    void displaySearch(){
    }
    
    /**
     * Display the information about the item being queried
     * @param item, representing the museum item which holds all
     * its data (description and file paths)
     */
    void displayMuseumItem(MuseumItem item){
    }
    
}

class Browser extends Region {
 
    final WebView browser = new WebView();
    final WebEngine webEngine = browser.getEngine();
     
    public Browser() {
        //apply the styles
        getStyleClass().add("browser");
        // load the web page
        
        
        URL url = getClass().getResource("../MCH_html/index.html");
        webEngine.load(url.toExternalForm());
        
        //add the web view to the scene
        getChildren().add(browser);
 
    }
    private Node createSpacer() {
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        return spacer;
    }
 
    @Override protected void layoutChildren() {
        double w = getWidth();
        double h = getHeight();
        layoutInArea(browser,0,0,w,h,0, HPos.CENTER, VPos.CENTER);
    }
 
    @Override protected double computePrefWidth(double height) {
        return 850;
    }
 
    @Override protected double computePrefHeight(double width) {
        return 600;
    }
}
