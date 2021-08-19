package src.main.java.Backend;

import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;


/**
 * <b>Menu<b/> class that holds categories of <b>Menu Items<b/> which the restaurant adds <b>menu items<b/> to and
 * the <b>Customer<b/> interacts with to add <b>menu items<b/> to their <b>Order<b/>
 */
public class Menu {

    /**
     * Menu object that holds menu items
     */
    private Map<String,HashMap<String,MenuItem>> menu; //items on menu
    
    /**
     * Constructs empty menu object
     */
    public Menu(){
        menu = new HashMap<String,HashMap<String,MenuItem>>();
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
    public Map<String,HashMap<String,MenuItem>> allItems() {
        return menu;
    }
    public Boolean containsCategory(String category){
        if (menu.get(category) != null){
            return true;
        }
        return false;
    }

    public static Menu loadMenu() {
        Menu menu = new Menu();

        return menu;
    }

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
}
