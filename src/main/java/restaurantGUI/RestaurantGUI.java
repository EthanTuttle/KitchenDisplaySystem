package src.main.java.restaurantGUI;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.ServerSocket;
import src.main.java.Backend.*;
import java.net.Socket;
import javax.swing.ImageIcon;

/**
 * RestaurantGUI class that creates the Restaurant Interface
 */
public class RestaurantGUI extends JFrame{
    /**
     * Main function that invokes the Restaurant GUI
     * @param args Command Line Arguments
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new RestaurantGUI();
            }
        });
    }
    /**
     * Display of active orders display
     */
    private ActiveOrdersDisplay activeOrdersDisplay;
    /**
     * Menu Creation GUI
     */
    private MenuCreationGUI menuCreationGUI;
    /**
     * Restaurant Menu
     */
    private src.main.java.Backend.Menu menu;
    /**
     * Server for customers to connect to the restaurant
     */
    private ServerSocket server;

    /**
     * Instantiates the RestaurantGUI
     */
    public RestaurantGUI() {

        ImageIcon ninjaIcon = null;

        java.net.URL imgURL = RestaurantGUI.class.getResource("ninja.png");
        if (imgURL != null) {
            ninjaIcon = new ImageIcon(imgURL);
            setIconImage(ninjaIcon.getImage());
        } else {
            JOptionPane.showMessageDialog(RestaurantGUI.this, "Icon image not found.");
        }

        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setJMenuBar(new GUIMenu(this));
        setTitle("Restaurant View");
        try {
            server = new ServerSocket(55555);
        } catch (IOException e) {
            System.out.println(e);
            return;
        }
        
        menu = src.main.java.Backend.Menu.loadMenu();

        addWindowListener(new java.awt.event.WindowAdapter(){
            /**
             * stores data before frame closed
            * @param java.awt.event.WindowEvent e This is the frame closing
            */
            public void windowClosing(java.awt.event.WindowEvent e){
                    ExitProcedure.exitProcedure(menu);
                    System.exit(0);
            }
        });


        activeOrdersDisplay = new ActiveOrdersDisplay(new ActiveOrders(), menu);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println("Waiting");
                    try {
                        Socket client = server.accept();
                        System.out.println("Connection found");
                        PrintWriter out = new PrintWriter(client.getOutputStream(), true);
                        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                        new Thread(new Runnable() {
                            public void run() {
                                for (String category : menu.allItems().keySet()) {
                                    for (String name : menu.allItems().get(category).keySet()) {
                                        out.println(menu.allItems().get(category).get(name).toString());
                                    }
                                }
                                out.println("End of Menu");
                                while(client.isConnected()) { //while connected, look for communication
                                    try {
                                        String line = in.readLine();
                                        if (line == null) {
                                            break;
                                        }
                                        else if (line.length() > 0) {
                                            activeOrdersDisplay.addOrder(line);
                                        }
                                    } catch (IOException e) {
                                        break;
                                    }
                                    
                                }
                            }
                        }).start(); //run thread to send all menu items
                    } catch (IOException e) {
                        //TODO
                        System.out.println(e);
                    }
                }
            }
        }).start();

        menuCreationGUI = new MenuCreationGUI(menu);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(0, 1));
        mainPanel.add(new JLabel("Choose where to go to start", SwingConstants.CENTER));
        JPanel buttonPanel = new JPanel();
        JButton displayButton = new JButton("To Order Display");
        JButton menuCreationButton = new JButton("To Menu Creation");
        menuCreationButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                setContentPane(menuCreationGUI);
                revalidate();
            }
        });
        displayButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                setContentPane(activeOrdersDisplay);
                revalidate();
            }
        });
        buttonPanel.add(displayButton);
        buttonPanel.add(menuCreationButton);
        mainPanel.add(buttonPanel);
        setContentPane(mainPanel);

        setVisible(true);
        setExtendedState(getExtendedState() | MAXIMIZED_BOTH);
    }

    /**
     * Gets menu from the back end
     * @return Restaurant Menu
     */
    public src.main.java.Backend.Menu menu() {
        return menu;
    }

    /**
     * Displays the active order display
     */
    public void displayActiveOrdersDisplay() {
        setContentPane(activeOrdersDisplay);
        repaint();
        revalidate();
    }

    /**
     * Displays the Menu Creation GUI
     */
    public void displayMenuCreationGUI() {
        setContentPane(menuCreationGUI);
        repaint();
        revalidate();
    }

}
