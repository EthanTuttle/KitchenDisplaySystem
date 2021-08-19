package src.main.java.restaurantGUI;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import src.main.java.Backend.*;

import src.main.java.Backend.ActiveOrders;

public class RestaurantGUI extends JFrame{

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
        mainPanel.add(new JLabel("Choose where to go to start", SwingConstants.CENTER));
        JPanel buttonPanel = new JPanel();
        JButton menuButton = new JButton("To Menu Configuration");
        //TODO: create menubutton action listener
        JButton displayButton = new JButton("To Order Display");
        displayButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                remove(mainPanel);
                JScrollPane scroll = new JScrollPane(activeOrdersDisplay);
                scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
                add(scroll);
                revalidate();
            }
        });
        buttonPanel.add(menuButton);
        buttonPanel.add(displayButton);
        mainPanel.add(buttonPanel);
        add(mainPanel);

        setVisible(true);
        setExtendedState(getExtendedState() | MAXIMIZED_BOTH);
    }

}
