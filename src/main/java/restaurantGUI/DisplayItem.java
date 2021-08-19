package src.main.java.restaurantGUI;

import java.awt.*;
import javax.swing.*;

import src.main.java.Backend.Customer;
import src.main.java.Backend.MenuItem;
import src.main.java.Backend.OrderItem;
import java.util.concurrent.TimeUnit;
import java.util.*;

/**
 * Display for a specific item in the order display
 */
public class DisplayItem extends JPanel {
    
    /**
     * Time to display
     */
    private JLabel time;
    /**
     * Customer and therefore order to display
     */
    private Customer customer;

    /**
     * Instantiate the display item
     * @param customer Customer and therefor order to display
     */
    public DisplayItem(Customer customer, JButton deleteButton) {
        this.customer = customer;
        setLayout(new BorderLayout());

        JPanel boxPanel = new JPanel();
        boxPanel.setLayout(new BoxLayout(boxPanel, BoxLayout.Y_AXIS));
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
        boxPanel.add(name);
        boxPanel.add(time);
        //add(new Box.Filler(minSize, prefSize, maxSize));
        JSeparator bar = new JSeparator();
        bar.setMaximumSize(new Dimension(Integer.MAX_VALUE, 5));
        boxPanel.add(bar);
        for (OrderItem order : customer.getOrders()) {
            for (MenuItem menuItem : order.getOrder()) {
                JLabel itemName = new JLabel(menuItem.getName(), SwingConstants.CENTER);
                itemName.setFont(original.deriveFont(18.0f));
                boxPanel.add(itemName);                
            }
        }
        boxPanel.setBorder(BorderFactory.createLineBorder(Color.black));

        add(boxPanel, BorderLayout.CENTER);
        add(deleteButton, BorderLayout.SOUTH);
    }

    /**
     * Updates the time for each order and displays it
     */
    public void updateTime() {
        long diff = new Date().getTime() - customer.getTime().getTime();
        String timeString = String.format("%d:%02d", 
        TimeUnit.MILLISECONDS.toMinutes(diff),
        TimeUnit.MILLISECONDS.toSeconds(diff) - 
        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(diff))
        );
        time.setText("Time waiting: " + timeString);
    }

}
