package src.main.java.customerGUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.*;

import java.net.InetAddress;
import java.net.Socket;
import java.io.*;

public class CustomerGUI extends JFrame {

    private Socket connection;
    private PrintWriter out; //TODO: use to communicate order
    private BufferedReader in;
    private String rpiIP = "129.161.52.212"; //change last 3 digits to host ID of machine running restaurant GUI
    private src.main.java.Backend.Menu menu;
    private boolean menuLoaded = false;

    public CustomerGUI() {
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setJMenuBar(new GUIMenu(this));
        setTitle("Customer View");
        
        try {
            connection = new Socket(InetAddress.getByName(rpiIP), 55555);
            out = new PrintWriter(connection.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            new Thread(new Runnable() {
                public void run() {
                    menu = new src.main.java.Backend.Menu();
                    while (connection.isConnected()) {
                        try {
                            String line = in.readLine();
                            if (line.equals("End of Menu")) {
                                break;
                            } else { //On connection host should send all menu items as name;timeToMake;category
                                String[] list = line.split(";");
                                menu.addMenuItem(list[2], list[0],  Integer.parseInt(list[1]));
                            }
                        } catch (IOException e) {
                            //TODO
                            System.out.println(e);
                        }
                    }
                    menuLoaded = true;
                    //TODO: figure out how to transition to menu chooser
                }
            }).start();
            setVisible(true);
            setExtendedState(getExtendedState() | MAXIMIZED_BOTH);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "ERROR: No connection to Host available", "Connection Error", JOptionPane.ERROR_MESSAGE);
        }

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());
        inputPanel.add(new JLabel("Enter Name: "));
        JTextField input = new JTextField();
        inputPanel.add(input);
        mainPanel.add(inputPanel);
        JButton submit = new JButton("Submit");
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                if (input.getText().length() > 0) {
                    //new Mainframe(menu, input.getText());
                    CustomerGUI.this.setVisible(false);
                }
                

            }
        });
        mainPanel.add(submit);
        add(mainPanel);
        setVisible(true);
        setExtendedState(getExtendedState() | MAXIMIZED_BOTH);
    }
    
    

   public static void main(String[] args) {
        new CustomerGUI();
   }   
}