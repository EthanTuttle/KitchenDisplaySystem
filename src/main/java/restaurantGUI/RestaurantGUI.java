package src.main.java.restaurantGUI;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import src.main.java.Backend.*;

import src.main.java.Backend.ActiveOrders;

public class RestaurantGUI extends JFrame{

    /** Show all the Orders on the OrderPanel */
    private ActiveOrdersDisplay orderPanel;
    private ActiveOrders queue;
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new RestaurantGUI();
            }
        });
    }

    private ActiveOrdersDisplay activeOrdersDisplay;

    public RestaurantGUI() {
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setJMenuBar(new GUIMenu(this));
        setTitle("Restaurant View");

        activeOrdersDisplay = new ActiveOrdersDisplay(new ActiveOrders());
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(0, 1));
        mainPanel.add(new JLabel("Choose where to go to start"));
        JPanel buttonPanel = new JPanel();
        JButton menuButton = new JButton("To Menu Configuration");
        //TODO: create menubutton action listener
        JButton displayButton = new JButton("To Order Display");
        displayButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                removeAll();
                add(activeOrdersDisplay);
            }
        });
        buttonPanel.add(menuButton);
        buttonPanel.add(displayButton);
        mainPanel.add(buttonPanel);

        setVisible(true);
        setExtendedState(getExtendedState() | MAXIMIZED_BOTH);

        OrderPanel = new ActiveOrdersDisplay();
        add(OrderPanel);
    }

}
