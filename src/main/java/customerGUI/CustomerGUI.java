package src.main.java.customerGUI;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.Semaphore;
import java.io.*;

/**
 * CustomerGUI class that instantiates the
 * <b>Customer</b> Interface
 */
public class CustomerGUI {

    /**
     * Semaphore to provide synchronization
     */
    private Semaphore loadSemaphore = new Semaphore(1);
    /**
     * Connect to the Customer GUI
     */
    private Socket connection;
    /**
     * Reader to read in input
     */
    private BufferedReader in;
    /**
     * RPI IP Address
     */
    private String rpiIP = "129.161.52.212"; //change last 3 digits to host ID of machine running restaurant GUI
    /**
     * Restaurant Menu
     */
    private src.main.java.Backend.Menu menu;

    /**
     * Instantiate CustomerGUI
     */
    public CustomerGUI() {

        JFrame options = new JFrame();
        ImageIcon ninjaIcon = null;
        java.net.URL imgURL = CustomerGUI.class.getResource("ninja.png");
        if (imgURL != null) {
            ninjaIcon = new ImageIcon(imgURL);
            options.setIconImage(ninjaIcon.getImage());
        } else {
            JOptionPane.showMessageDialog(null, "Icon image not found.");
        }
        options.setTitle("Customer Information");
        options.setSize(350, 125);
        options.setLocationRelativeTo(null);
        JPanel optionsPanelWrapper = new JPanel();
        optionsPanelWrapper.setLayout(new FlowLayout());
        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new GridLayout(0, 2));
        optionsPanel.add(new JLabel("Enter Name: ", SwingConstants.CENTER));
        JTextField nameInput = new JTextField();
        nameInput.setColumns(18);
        optionsPanel.add(nameInput);
        optionsPanel.add(new JLabel("Enter IP: ", SwingConstants.CENTER));
        JTextField ipInput = new JTextField();
        ipInput.setColumns(18);
        optionsPanel.add(ipInput);
        optionsPanel.add(new JLabel());
        JButton submit = new JButton("Submit");
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                if (nameInput.getText().length() == 0 || ipInput.getText().length() == 0 ) {
                    JOptionPane.showMessageDialog(null, "Must enter all inputs", "Input Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    rpiIP = ipInput.getText().strip();
                    try {
                        loadSemaphore.acquire();
                        connection = new Socket(InetAddress.getByName(rpiIP), 55555);
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
                                            if (!menu.containsCategory(list[2])) {
                                                menu.addCategory(list[2]);
                                            }
                                            menu.addMenuItem(list[2], list[0],  Integer.parseInt(list[1]));
                                        }
                                    } catch (IOException e) {
                                        //TODO: 
                                        System.out.println(e);
                                    }
                                    
                                }
                                loadSemaphore.release();
                            }
                        }).start();
                        loadSemaphore.acquire();
                        options.setVisible(false);
                        new Mainframe(menu, nameInput.getText(), connection);
                        loadSemaphore.release();
                    } catch (IOException e) {
                        JOptionPane.showMessageDialog(null, e, "Connection Error", JOptionPane.ERROR_MESSAGE);
                    } catch (InterruptedException e) {
                        JOptionPane.showMessageDialog(null, e, "Thread Error", JOptionPane.ERROR_MESSAGE);
                    }
                    
                }
            }
        });
        optionsPanel.add(submit);
        options.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        optionsPanelWrapper.add(optionsPanel);
        options.add(optionsPanelWrapper);
        options.setVisible(true);

    }
    /**
     * Main function creates a new Customer GUI
     * @param args Command Line Arguments
     */
   public static void main(String[] args) {
        new CustomerGUI();
   }   
}