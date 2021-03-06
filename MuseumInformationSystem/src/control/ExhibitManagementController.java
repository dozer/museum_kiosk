package control;

import java.util.List;
import java.util.ArrayList;

import model.Exhibit;
import model.MuseumItem;
import access.ExhibitDAO;
import access.MuseumItemDAO;

/**
 * The purpose of the Exhibit Management Controller is to add, remove or edit
 * Exhibits in the ExhibitList Created November 26th 2013
 *
 * @author Sara, Joe, Casey
 *
 */
public class ExhibitManagementController {
    
    /**
     * Gets all Exhibits currently in the database
     * 
     * @return list, representing all Exhibits
     */
    public List<Exhibit> getExhibits() {
        List<Exhibit> list = ExhibitDAO.find();
        for(Exhibit e: list)
            e.setMuseumItemList(ExhibitDAO.findMuseumItemInExhibit(e.getExhibitName()));
        return list;
    }
    
    /**
     * Gets all MuseumItems currently in the database
     * 
     * @return list, representing all MuseumItems
     */
    public List<MuseumItem> getMuseumItems() {
        List<MuseumItem> list = MuseumItemDAO.find();
        return list;
    }

    /**
     * Creates a new Exhibit with the location, name, and description of the
     * Exhibit
     *
     * @param location, representing the coordinates of the Exhibit on the floor
     * plan
     * @param name, representing the name of the Exhibit
     * @param description, representing the description of the Exhibit
     */
    public void addExhibit(int[] location, String name, String description) {
        List<Exhibit> list = ExhibitDAO.find();
        ExhibitDAO.CreateExhibit(name);
        list.add(new Exhibit(name, description, location));
        ExhibitDAO.updateExhibitTable(list);
    }

    /**
     * Removes an Exhibit from the current ArrayList of Exhibits
     *
     * @param name, representing the name of the Exhibit to remove
     * @return ArrayList of Exhibits minus the Exhibit to remove
     */
    public void removeExhibit(String name) {
        List<Exhibit> list = ExhibitDAO.find();
        for (Exhibit e : list) {
            if (e.getExhibitName() == name) {
                list.remove(e);

                break;
            }
        }

        ExhibitDAO.updateExhibitTable(list);
    }

    /**
     * Creates a new Item with the given paramters to the exhibit matching the
     * given name's ItemList
     *
     * @param name representing the exhibit name
     * @param newName representing the item name
     * @param description representing the item description
     * @param location representing the location of the exhibit
     * @param museumItemList representing the museum items of this Exhibit
     */
    public void editExhibit(String name, String newName, String description, int[] location, ArrayList<MuseumItem> museumItemList) {
        ArrayList<String> updatedlist = new ArrayList<String>();
        List<Exhibit> list = ExhibitDAO.find();
        for(Exhibit e: list) {
            if(e.getExhibitName().equals(name)) {
                if(newName != null)
                    e.setExhibitName(newName);
                if(description != null)
                    e.setExhibitDescription(description);
                if(location != null)
                    e.setExhibitLocation(location);
                if(museumItemList != null) {
                    e.setMuseumItemList(museumItemList);
                    
                    updatedlist.clear();
                    for(MuseumItem item : e.getMuseumItemList()){
                    	updatedlist.add(item.getName());
                    }
                    
                    ExhibitDAO.updateItemsInExhibit(name, updatedlist);
                }
            }
        }
        ExhibitDAO.updateExhibitTable(list);
    }

    /**
     * Removes the provided item from the given exhibits itemlist
     *
     * @param name representing the exhibit name
     * @param itemName representing the museum item name
     */
    public void removeMuseumItemFromExhibit(String exhibitName, String itemName) {
        List<Exhibit> list = ExhibitDAO.find();
        for (Exhibit e : list) {
            if (e.getExhibitName().equals(exhibitName)) {
                for (MuseumItem m : e.getMuseumItemList()) {
                    if (m.getName().equals(itemName)) {
                        e.removeItem(m);
                        break;
                    }
                }
            }
        
        ArrayList<String> updatedlist = new ArrayList<String>();
        for(MuseumItem item : e.getMuseumItemList()){
        	updatedlist.add(item.getName());
        }
        ExhibitDAO.updateItemsInExhibit(exhibitName, updatedlist);
        }
    }
}
