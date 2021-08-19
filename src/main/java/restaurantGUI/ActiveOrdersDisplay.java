package src.main.java.restaurantGUI;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;
import java.util.Date;
import src.main.java.Backend.ActiveOrders;
import src.main.java.Backend.Customer;
import src.main.java.Backend.Menu;
import src.main.java.Backend.MenuItem;
import java.sql.Timestamp;

public class ActiveOrdersDisplay extends JPanel {

    private LinkedList<DisplayItem> displayItems = new LinkedList<>();
    private ActiveOrders queue;
    private JPanel displayPanel;
    private JScrollPane scrollPanel;
    private Menu menu;
    private HashMap<String, Customer> customers = new HashMap<>();
    
    public ActiveOrdersDisplay(ActiveOrders queue, src.main.java.Backend.Menu menu) {
        this.menu = menu;
        this.queue = queue;
        setLayout(new BorderLayout());
        displayPanel = new JPanel();
        displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.X_AXIS));
        scrollPanel = new JScrollPane(displayPanel);
        scrollPanel.setBorder(null);
        add(scrollPanel, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        JButton button = new JButton("button");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                
            }
        });
        buttonPanel.add(button);
        add(buttonPanel, BorderLayout.SOUTH);

        new Thread(new Runnable(){
            public void run() {
                while (true) {
                    for (DisplayItem item : displayItems) {
                        item.updateTime();
                    }
                    scrollPanel.repaint();
                    scrollPanel.revalidate();
                }
            }
        }).start();
    }

    /**
     * Read in an order in the form of timePlace, customer name, then followed by the names of all menu items delimited by ";"
     * @param orderString - the string of time placed, name, and menu items. Should have at least one menu item
     */
    public void addOrder(String orderString) {
        String[] items = orderString.split(";");
        String customerName = items[1];
        Customer customer;
        if (customers.containsKey(customerName)) { //if exists, get customer
            customer = customers.get(customerName);
        } else { //else create and add to hash map and active order queue
            customer = new Customer(customerName); 
            customers.put(customerName, customer);
            queue.addCustomer(customer);
        }
        for (int i = 2; i < items.length; i++) {
            MenuItem item = menu.findMenuItem(items[i]);
            if (item != null) {
                customer.addItemToOrder(item);
            }
        }
        String timeString = items[0];
        Date timePlaced = Timestamp.valueOf(timeString);
        customer.placeOrder(timePlaced);
        updatePanel();
    }

    private void updatePanel() {
        displayPanel.removeAll();
        Iterator<Customer> itr = queue.getIterator();
        while (itr.hasNext()) {
            Customer customer = itr.next();
            DisplayItem displayItem = new DisplayItem(customer);
            JScrollPane scrollableDisplay = new JScrollPane(displayItem);
            scrollableDisplay.setMinimumSize(new Dimension((int)displayPanel.getSize().getWidth()/6, (int)displayPanel.getSize().getHeight()));
            scrollableDisplay.setMaximumSize(new Dimension((int)displayPanel.getSize().getWidth()/6, (int)displayPanel.getSize().getHeight()));
            scrollableDisplay.setBorder(null);
            displayPanel.add(scrollableDisplay);
            if (itr.hasNext()) {
                Dimension minSize = new Dimension(5, 100);
                Dimension prefSize = new Dimension(10, 100);
                Dimension maxSize = new Dimension(10, 100);
                displayPanel.add(new Box.Filler(minSize, prefSize, maxSize));
            }
        }
        scrollPanel.repaint();
        scrollPanel.revalidate();
    }

}
