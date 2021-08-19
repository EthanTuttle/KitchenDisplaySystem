package src.main.java.restaurantGUI;

import java.awt.*;
import javax.swing.*;

import src.main.java.Backend.Customer;
import src.main.java.Backend.MenuItem;
import src.main.java.Backend.OrderItem;
import java.util.concurrent.TimeUnit;
import java.util.*;

public class DisplayItem extends JPanel {
    
    private JLabel time;
    private Customer customer;

    public DisplayItem(Customer customer) {
        this.customer = customer;
        setLayout(new GridLayout(0, 1));
        JLabel name = new JLabel("", SwingConstants.CENTER);
        if (customer.getName().length() > 20) {
            name.setText(customer.getName().substring(0, 20) + "...");
        } else {
            name.setText(customer.getName());
        }
        
        Font original = name.getFont();
        Font bigger = original.deriveFont(18.0f);
        name.setFont(bigger);
        time = new JLabel("", SwingConstants.CENTER);
        updateTime();
        add(name);
        add(time);
        for (OrderItem order : customer.getOrders()) {
            for (MenuItem menuItem : order.getOrder()) {
                add(new JLabel(menuItem.getName()));
                
            }
        }
        setBorder(BorderFactory.createLineBorder(Color.black));
    }

    public void updateTime() {
        long diff = new Date().getTime() - customer.getTime().getTime();
        String timeString = String.format("%d:%d", 
        TimeUnit.MILLISECONDS.toMinutes(diff),
        TimeUnit.MILLISECONDS.toSeconds(diff) - 
        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(diff))
        );
        time.setText("Time waiting: " + timeString);
    }

}
