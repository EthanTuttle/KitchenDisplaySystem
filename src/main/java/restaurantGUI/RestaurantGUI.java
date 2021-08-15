package src.main.java.restaurantGUI;

import java.awt.*;
import javax.swing.*;
import javax.swing.JFrame;

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

    public RestaurantGUI() {
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setJMenuBar(new GUIMenu(this));
        setTitle("Restaurant View");

        setVisible(true);
        setExtendedState(getExtendedState() | MAXIMIZED_BOTH);

        OrderPanel = new ActiveOrdersDisplay();
        add(OrderPanel);
    }

}
