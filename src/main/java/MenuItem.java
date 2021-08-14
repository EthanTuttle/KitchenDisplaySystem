package src.main.java;

public class MenuItem {
    
    private String name; //name of menu item
    private int timeToMake; //measured in ms
    private String category; //category the menu item falls into on the menu

    public MenuItem(String name, int ms, String cat) {
        this.name = name;
        this.timeToMake = ms;
        this.category = cat;
    }

    public String getName() {
        return name;
    }

    public int getTimeToMake() {
        return timeToMake;
    }
    public String getCategory(){
        return category;
    }

}
