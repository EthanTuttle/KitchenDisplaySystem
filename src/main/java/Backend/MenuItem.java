package src.main.java.Backend;

/**
 * <b>MenuItem</b> class which represents a menu item that a restaurant puts on a 
 * <b>Menu</b> and a <b>Customer</b> could add to an <b>Order</b>
 */
public class MenuItem {
    
    /**
     * Name of Menu Item
     */
    private String name; //name of menu item

    /**
     * Time to make menu item
     */
    private int timeToMake; //measured in ms

    /**
     * Category Menu Item falls into
     */
    private String category; //category the menu item falls into on the menu

    /**
     * Creates a menu item object
     * @param name Name of Menu Item
     * @param ms Time to make the menu item
     * @param cat Category the menu item falls into
     */
    public MenuItem(String name, int ms, String cat) {
        this.name = name;
        this.timeToMake = ms;
        this.category = cat;
    }

    /**
     * 
     * @return Menu Item name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @return Time to make menu item
     */
    public int getTimeToMake() {
        return timeToMake;
    }

    /**
     * 
     * @return Category the menu item falls into
     */
    public String getCategory(){
        return category;
    }

    @Override
    public String toString() {
        return name + ";" + timeToMake + ";" + category;
    }

}
