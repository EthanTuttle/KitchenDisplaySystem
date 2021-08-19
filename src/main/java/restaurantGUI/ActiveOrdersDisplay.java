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

/**
 * ActiveOrdersDisplay class which displays all the 
 * <b>Orders</b> a <b>Restaurant</b> receives to that
 * <b>Restaurant</b>
 */
public class ActiveOrdersDisplay extends JPanel {

    /**
     * Order Items to display
     */
    private LinkedList<DisplayItem> displayItems = new LinkedList<>();
    /**
     * Queue for Active Orders
     */
    private ActiveOrders queue;
    /**
     * Panel to display active orders
     */
    private JPanel displayPanel;
    /**
     * Scroll Panel to hold display panel
     */
    private JScrollPane scrollPanel;
    /**
     * Overall menu from restaurant
     */
    private Menu menu;
    /**
     * Customers that we have orders from
     */
    private HashMap<String, Customer> customers = new HashMap<>();
    
    /**
     * Instantiates the Active Orders Display
     * @param queue Queue of active orders
     * @param menu Restaurant Menu
     */
    public ActiveOrdersDisplay(ActiveOrders queue, src.main.java.Backend.Menu menu) {
        this.menu = menu;
        this.queue = queue;
        setLayout(new BorderLayout());
        displayPanel = new JPanel();
        displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.X_AXIS));
        scrollPanel = new JScrollPane(displayPanel);
        scrollPanel.setBorder(null);
        scrollPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        add(scrollPanel, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        JButton refresh = new JButton("Refresh");
        refresh.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                updatePanel();
            }
        });
        buttonPanel.add(refresh);
        add(buttonPanel, BorderLayout.SOUTH);

        new Thread(new Runnable(){

            @Override
            public void run() {
                while (true) {
                    synchronized (displayItems) {
                        for (DisplayItem item : displayItems) {
                            item.updateTime();
                        }
                        scrollPanel.repaint();
                        scrollPanel.revalidate();
        int delay = 500;
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                synchronized (displayItems) {
                    for (DisplayItem item : displayItems) {
                        item.updateTime();
                    }
                    scrollPanel.repaint();
                    scrollPanel.revalidate();
                }
            }
        };
        Timer timeUpdater = new Timer(delay, taskPerformer);
        timeUpdater.start();
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
        queue.sort();
        updatePanel();
    }

    /**
     * Updates the panel
     */
    public void updatePanel() {
        synchronized (displayItems) {
            displayPanel.removeAll();
            Iterator<Customer> itr = queue.getIterator();
            while (itr.hasNext()) {
                Customer customer = itr.next();

                JButton deleteButton = new JButton("Remove");
                deleteButton.setBackground(Color.GREEN);
                deleteButton.setOpaque(true);

                DisplayItem displayItem = new DisplayItem(customer, deleteButton);
                displayItems.add(displayItem);
                displayItem.setMinimumSize(new Dimension((int)displayPanel.getSize().getWidth()/6, (int)displayPanel.getSize().getHeight()));
                displayItem.setMaximumSize(new Dimension((int)displayPanel.getSize().getWidth()/4, (int)displayPanel.getSize().getHeight()));
                JScrollPane scrollableDisplay = new JScrollPane(displayItem);
                scrollableDisplay.setMinimumSize(new Dimension((int)displayPanel.getSize().getWidth()/6, (int)displayPanel.getSize().getHeight()));
                scrollableDisplay.setPreferredSize(new Dimension((int)displayPanel.getSize().getWidth()/6, (int)displayPanel.getSize().getHeight()));
                scrollableDisplay.setMaximumSize(new Dimension((int)displayPanel.getSize().getWidth()/6, (int)displayPanel.getSize().getHeight()));
                displayPanel.add(scrollableDisplay);
                if (itr.hasNext()) {
                    Dimension minSize = new Dimension(5, 100);
                    Dimension prefSize = new Dimension(10, 100);
                    Dimension maxSize = new Dimension(10, 100);
                    displayPanel.add(new Box.Filler(minSize, prefSize, maxSize));
                }

                deleteButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        queue.removeCustomer(customer);
                        displayPanel.remove(scrollableDisplay);
                        scrollPanel.repaint();
                        scrollPanel.revalidate();
                        customers.remove(customer.getName());
                    }
                });
            }
            scrollPanel.repaint();
            scrollPanel.revalidate();
        }
    }
}
