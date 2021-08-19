package src.main.java.restaurantGUI;

import java.awt.*;
import javax.swing.*;

import src.main.java.Backend.Customer;
import src.main.java.Backend.OrderItem;

import java.util.*;

public class DisplayItem extends JPanel {
    

    public DisplayItem() {
        setLayout(new GridLayout(0, 1));
    }

    public void update(Customer customer) {
        LinkedList<OrderItem> list = customer.getOrders();
        
    }

}
