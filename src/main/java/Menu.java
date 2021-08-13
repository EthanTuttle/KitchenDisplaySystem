package src.main.java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Menu {
    private static Map<String,MenuItem> menuItems = new HashMap<String,MenuItem>(); //items on menu
    private static int idNumber = 0;
    public Menu(String [] itemNames,int [] timeToMake){
        for (int i=0;i<itemNames.length;i++){
            MenuItem m = new MenuItem(itemNames[i],timeToMake[i],idNumber);
            menuItems.put(itemNames[i],m);
            idNumber++;
        }
    }

    public static ArrayList<MenuItem> getMenuItems(){
        return (ArrayList<MenuItem>) menuItems.values();
    }

    public static void addMenuItem(String name, int timeToMake){
        MenuItem m = new MenuItem(name,timeToMake,idNumber);
        menuItems.put(name,m);
        idNumber++;
    }
}
