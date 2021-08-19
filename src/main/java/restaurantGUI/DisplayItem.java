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
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JLabel name = new JLabel();
        if (customer.getName().length() > 15) {
            name.setText(customer.getName().substring(0, 15) + "...");
        } else {
            name.setText(customer.getName());
        }
        Font original = name.getFont();
        Font bigger = original.deriveFont(24.0f);
        name.setFont(bigger);
        time = new JLabel();
        updateTime();
        time.setFont(original.deriveFont(18.0f));
        add(name);
        add(time);
        Dimension minSize = new Dimension(Integer.MAX_VALUE, 5);
        Dimension prefSize = new Dimension(Integer.MAX_VALUE, 5);
        Dimension maxSize = new Dimension(Integer.MAX_VALUE, 5);
        //add(new Box.Filler(minSize, prefSize, maxSize));
        JSeparator bar = new JSeparator();
        bar.setMaximumSize(maxSize);
        add(bar);
        for (OrderItem order : customer.getOrders()) {
            for (MenuItem menuItem : order.getOrder()) {
                JLabel itemName = new JLabel(menuItem.getName(), SwingConstants.CENTER);
                itemName.setFont(original.deriveFont(18.0f));
                add(itemName);                
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
