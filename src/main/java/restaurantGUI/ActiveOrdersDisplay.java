package src.main.java.restaurantGUI;

import java.util.LinkedList;

import javax.swing.JPanel;
import java.awt.*;

import src.main.java.Backend.ActiveOrders;

public class ActiveOrdersDisplay extends JPanel {

    LinkedList<DisplayItem> actives = new LinkedList<>();
    
    public ActiveOrdersDisplay(ActiveOrders queue) {
        
    }

    public void addOrder() {

    }
    public void paint(Graphics g)
    {
        g.setColor(Color.BLACK);
        g.drawRect(10,10,100,100);
    }

    private void updatePanel() {
        
    }

}
