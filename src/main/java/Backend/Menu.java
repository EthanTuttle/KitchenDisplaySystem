package src.main.java.Backend;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.LinkedHashMap;


/**
 * <b>Menu</b> class that holds categories of <b>Menu Items</b> which the restaurant adds <b>menu items</b> to and
 * the <b>Customer</b> interacts with to add <b>menu items</b> to their <b>Order</b>
 */
public class Menu {

    /**
     * Menu object that holds menu items
     */
    private LinkedHashMap<String,HashMap<String,MenuItem>> menu; //items on menu
    
    /**
     * Constructs empty menu object
     */
    public Menu(){
        menu = new LinkedHashMap<String,HashMap<String,MenuItem>>();
    }

    /**
     * Adds a category to the menu
     * @param category Category to add to the menu
     */
    public void addCategory(String category){ //adds category and empty map of menu items
        menu.put(category,new HashMap<String,MenuItem>());
    }

    /**
     * Adds a menu item to a category in the menu
     * @param category Category where we should add menu item to
     * @param menuName Menu Item Name 
     * @param timeToMake Time to make menu item
     */
    public void addMenuItem(String category, String menuItemName, int timeToMake){ //adds a menu item to a certain category
        menu.get(category).put(menuItemName,new MenuItem(menuItemName,timeToMake,category));
    }

    /**
     * 
     * @param category Category of menu items to return
     * @return Menu Items of category passed in
     */
    public Map<String,MenuItem> getMenuItems(String category){ ///gets the menu items from the same category
        return menu.get(category);
    }

    /**
     * Gets the menu 
     * @return Menu
     */
    public LinkedHashMap<String,HashMap<String,MenuItem>> allItems() {
        return menu;
    }
    
    /**
     * Returns if a category is in the menu
     * @param category The category to test
     * @return Boolean if category is in the menu
     */
    public Boolean containsCategory(String category){
        if (menu.get(category) != null){
            return true;
        }
        return false;
    }

    /**
     * Loads menu from previous run of application
     * @return Menu that was previously loaded
     */
    public static Menu loadMenu() {
        Menu menu = new Menu();
        File menuFile = new File(".menu");
        if (menuFile.exists())
        {
            FileReader fileReader = null;
            BufferedReader bufferedReader = null;
            try{
                fileReader = new FileReader(menuFile);
                bufferedReader = new BufferedReader(fileReader);
                String line = bufferedReader.readLine();
                while (line != null){
                    // Each line is being read as "customer=?&menu_item=?&timeToMake=?"
                    // fields contains customer=?,
                    //                 menu_item=?,
                    //                 timeToMake=?
                    String[] fields = line.split(";");
                    String category = "";
                    String menuItem = "";
                    Integer timeToMake = null;
                    for (int i = 0; i < fields.length; i++){
                        String[] values = fields[i].split("=");
                        String fieldValue = values[1];
                        switch (i)
                        {
                            case 0:
                                category = fieldValue.strip();
                                if (!menu.containsCategory(category)){
                                    menu.addCategory(category);
                                }
                                break;
                            case 1:
                                menuItem = fieldValue.strip();
                                break;
                            case 2:
                                timeToMake = fieldValue.strip().equals("") ? null : Integer.parseInt(fieldValue.strip());
                                if (timeToMake != null){
                                    menu.addMenuItem(category, menuItem, timeToMake);
                                }
                        }
                    }
                    line = bufferedReader.readLine();
                }
		    }
            catch(IOException e){
                e.printStackTrace();
            }
            finally{
                try{
                    if (bufferedReader != null) bufferedReader.close();
                    if (fileReader != null) fileReader.close();
                }
                catch (IOException e){
                    /* Error caught while trying to close the Writer streams */
                    e.printStackTrace();
                    System.exit(1);
                }
            }
        }
        // Return the menu whether populated or not
        return menu;

    }

    /**
     * Replaces an old category in the menu with a new category
     * @param oldCategoryName The Old Category Name 
     * @param newCategoryName The New Category Name
     */
    public void replace(String oldCategoryName, String newCategoryName){
        HashMap<String,MenuItem> sampleMap = menu.remove(oldCategoryName);
        menu.put(newCategoryName,sampleMap);
    }

    /**
     * Removes a category or menu item from the menu based of the type passed in
     * @param categ The Category to Remove
     * @param menuItem The Menu Item to Remove
     * @param type Type specifying whether we remove a category or menu item
     */
    public void remove(String categ, String menuItem, String type){
        if (type.equals("category")){
            menu.remove(categ);
        }
        else if (type.equals("menu_item")){
            menu.get(categ).remove(menuItem);
        }
    }
    
    /**
     * Finds a menu item based off its name in the menu
     * @param name Name of the Menu Item
     * @return The Menu Item found, null otherwise
     */
    public MenuItem findMenuItem(String name) {
        Iterator<String> itr = menu.keySet().iterator();
        while (itr.hasNext()) {
            String category = itr.next();
            if (menu.get(category).containsKey(name)) {
                return menu.get(category).get(name);
            }
        }
        return null;
    }

    public Map<String, HashMap<String, MenuItem>> getMenu()
    {
        return this.menu;
    }

    public boolean isEmpty(){
        return menu.isEmpty();
    }
}
