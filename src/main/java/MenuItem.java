package src.main.java;

public class MenuItem {
    
    private String name; //name of menu item
    private int timeToMake; //measured in ms
    private int menu_id; //id for menu item

    public MenuItem(String name, int ms, int id) {
        this.name = name;
        this.timeToMake = ms;
        this.menu_id = id;
    }

    public String getName() {
        return name;
    }

    public int getTimeToMake() {
        return timeToMake;
    }
    public int getId(){
        return menu_id;
    }

}
