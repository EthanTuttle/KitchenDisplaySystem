package src.main.java;

public class MenuItem {
    
    private String name; //name of menu item
    private int timeToMake; //measured in ms

    public MenuItem(String name, int ms) {
        this.name = name;
        this.timeToMake = ms;
    }

    public String getName() {
        return name;
    }

    public int getTimeToMake() {
        return timeToMake;
    }

}
