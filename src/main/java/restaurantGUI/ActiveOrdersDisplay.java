package src.main.java.restaurantGUI;

import java.util.LinkedList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import src.main.java.Backend.ActiveOrders;

public class ActiveOrdersDisplay extends JPanel {

    LinkedList<DisplayItem> actives = new LinkedList<>();
    
    public ActiveOrdersDisplay(ActiveOrders queue) {
        add(new JLabel("test1"));
        add(new JLabel("test2"));
        add(new JLabel("test1"));
        add(new JLabel("test2"));
        add(new JLabel("test1"));
        add(new JLabel("test2"));
        add(new JLabel("test1"));
        add(new JLabel("test2"));
    }

    public void addOrder() {

    }

    private void updatePanel() {
        
    }

}
