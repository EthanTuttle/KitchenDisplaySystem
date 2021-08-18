package src.main.java.restaurantGUI;

import java.util.LinkedList;

import javax.swing.*;
import java.awt.*;

import src.main.java.Backend.ActiveOrders;
import src.main.java.Backend.Customer;

public class ActiveOrdersDisplay extends JPanel {

    //LinkedList<DisplayItem> actives = new LinkedList<DisplayItem>();
    private JPanel innerPanel;
    private GridBagConstraints constraints;
    
    public ActiveOrdersDisplay() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setAlignmentY(0);
        setAlignmentX(0);
        innerPanel = new JPanel(new GridBagLayout());
        innerPanel.setAlignmentY(0);
        innerPanel.setAlignmentX(0);
        constraints = new GridBagConstraints();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) (screenSize.getHeight() / 1.25);
        innerPanel.setMaximumSize(new Dimension(width, height));
        innerPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        add(innerPanel);
    }

    public void addCustomer(Customer aCustomer) {
        ActiveOrders.addCustomer(aCustomer);
        updatePanel();
    }

    private void updatePanel() {
        constraints.anchor = GridBagConstraints.NORTH;
        constraints.weighty = 1;
        for (int orderIndex = 0; orderIndex < ActiveOrders.orders.size(); orderIndex++)
        {
            // this was supposed to be where we build each "order" string
            // Would have been like
            // Customer A                   |   Customer B
            //  Item 1, Item 2, Item 3,...  |    Item 1, Item 2, Item 3...
            //
            JLabel customerName = new JLabel(ActiveOrders.orders.get(orderIndex).getName());
            innerPanel.add(customerName, constraints);
        }
        /*
        innerPanel.add(test, constraints);
        test.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        constraints.gridx = 1;
        constraints.gridy = 0;
        JLabel test1 = new JLabel("TEST1");
        innerPanel.add(test1, constraints);
        constraints.gridx = 2;
        constraints.gridy = 0;
        JLabel test2 = new JLabel("TEST2");
        innerPanel.add(test2, constraints);
        constraints.gridx = 3;
        constraints.gridy = 0;
        JLabel test3 = new JLabel("TEST3");
        innerPanel.add(test3, constraints); 
        */  
    }

}
