package src.main.java.restaurantGUI;

import java.awt.*;
import javax.swing.*;
import java.util.*;
import src.main.java.Customer;
import src.main.java.OrderItem;

public class DisplayItem extends JPanel {
    

    public DisplayItem() {
        setLayout(new GridLayout(0, 1));
    }

    public void update(Customer customer) {
        LinkedList<OrderItem> list = customer.getOrders();
        
    }

}
