package src.main.java.restaurantGUI;

import java.awt.*;
import javax.swing.*;

import src.main.java.Backend.Customer;
import src.main.java.Backend.MenuItem;
import src.main.java.Backend.OrderItem;

import java.util.*;

public class DisplayItem extends JPanel {
    

    public DisplayItem(Customer customer) {
        setLayout(new GridLayout(0, 1));
        JLabel name = new JLabel("", SwingConstants.CENTER);
        name.setText(customer.getName());
        Font original = name.getFont();
        Font bigger = original.deriveFont(18.0f);
        name.setFont(bigger);
        JLabel time = new JLabel("", SwingConstants.CENTER);
        time.setText("Time waiting: " + customer.getTick());
        add(name);
        add(time);
        for (OrderItem order : customer.getOrders()) {
            for (MenuItem menuItem : order.getOrder()) {
                add(new JLabel(menuItem.getName()));
            }
        }
        setBorder(BorderFactory.createLineBorder(Color.black));
    }

}
