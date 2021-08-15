package src.main.java.restaurantGUI;

import java.util.LinkedList;

import javax.swing.JPanel;

import src.main.java.Backend.ActiveOrders;

public class ActiveOrdersDisplay extends JPanel {

    LinkedList<DisplayItem> actives = new LinkedList<>();
    
    public ActiveOrdersDisplay(ActiveOrders queue) {
        
    }

}
