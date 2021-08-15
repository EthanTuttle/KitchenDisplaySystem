package src.main.java.restaurantGUI;

import java.util.LinkedList;

import javax.swing.*;
import java.awt.*;

import src.main.java.Backend.ActiveOrders;

public class ActiveOrdersDisplay extends JPanel {

    LinkedList<DisplayItem> actives = new LinkedList<>();
    
    public ActiveOrdersDisplay(ActiveOrders queue) {
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(500, 500));
        GridBagConstraints c = new GridBagConstraints();
        JPanel innerPanel = new JPanel();
        innerPanel.setLayout(new BoxLayout(innerPanel, BoxLayout.Y_AXIS));
        innerPanel.add(new JLabel("test1"));
        innerPanel.add(new JLabel("test2"));
        innerPanel.add(new JLabel("test1"));
        innerPanel.add(new JLabel("test2"));
        innerPanel.add(new JLabel("test1"));
        innerPanel.add(new JLabel("test2"));
        innerPanel.add(new JLabel("test1"));
        innerPanel.add(new JLabel("test2"));
        innerPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 1;
        c.anchor = GridBagConstraints.NORTH;
        add(innerPanel, c);
    }

    public void addOrder() {

    }

    private void updatePanel() {
        
    }

}
