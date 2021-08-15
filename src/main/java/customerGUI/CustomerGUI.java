package src.main.java.customerGUI;

import java.awt.*;
import javax.swing.*;

import src.main.java.Backend.Connection;
import src.main.java.Backend.MenuItem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

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
    private String rpiIP = "129.161.136.111"; //change last 3 digits to host ID of machine running restaurant GUI

    public void CustomerGUI() {
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setJMenuBar(new GUIMenu(this));
        setTitle("Customer View");

        connection = new Connection(1, rpiIP, 55555);


        try {
            connection.connect();
            new Thread(new Runnable() {
                public void run() {
                    while (connection.connected()) {
                        try {
                            String line = connection.read();
                            if (line.equals("End of Menu")) {
                                break;
                            } else { //On connection host should send all menu items as name;timeToMake;category
                                String[] list = line.split(";");
                                MenuItem item = new MenuItem(list[0], Integer.parseInt(list[1]), list[2]);
                            }
                        } catch (IOException e) {
                            //TODO
                            System.out.println(e);
                        }
                    }
                }
            }).start();
            setVisible(true);
            setExtendedState(getExtendedState() | MAXIMIZED_BOTH);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "ERROR: No connection to Host available", "Connection Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
