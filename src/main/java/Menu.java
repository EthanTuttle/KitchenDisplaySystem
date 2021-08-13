package src.main.java;

import java.util.HashMap;
import java.util.Map;


public class Menu {
    private Map<String,HashMap<String,MenuItem>> menu = new HashMap<String,HashMap<String,MenuItem>>(); //items on menu
    public Menu(){

    }

    public void addCategory(String category){ //adds category and empty map of menu items
        menu.put(category,new HashMap<String,MenuItem>());
    }

    public void addMenuItem(String category, String menuName, int timeToMake){ //adds a menu item to a certain category
        menu.get(category).put(menuName,new MenuItem(menuName,timeToMake));
    }

    public Map<String,MenuItem> getMenuItems(String category){ ///gets the menu items from the same category
        return menu.get(category);
    }


}
