package src.main.java.customerGUI;

import java.awt.*;
import javax.swing.*;

import src.main.java.Backend.Connection;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerGUI extends JFrame {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new CustomerGUI();
            }
        });
    }

    private Connection connection;

    public void CustomerGUI() {
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setJMenuBar(new GUIMenu(this));
        setTitle("Customer View");

        setVisible(true);
        setExtendedState(getExtendedState() | MAXIMIZED_BOTH);
    }

}
