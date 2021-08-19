package src.main.java.customerGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import src.main.java.Backend.Menu;
import src.main.java.Backend.MenuItem;

import java.io.FileNotFoundException;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.Socket;

/**
 * Mainframe that the Customer GUI interacts with
 */
public class Mainframe extends JFrame {

    /**
     * Total cost of an order
     */
    private BigDecimal totalCost;
    /**
     * Reader to read the menu for display
     */
    private MenuReader menuRead;
    /**
     * Panel to display receipt
     */
    private JPanel receipt;
    /**
     * Center Panel
     */
    private JPanel centerPanel;
    JPanel pan;

    JPanel panCopy;
    private Menu givMenu;
    /**
     * Order Price
     */
    private int orderPrice;
    /**
     * List of items ordered
     */
    private ArrayList<MenuItem> itemsOrdered;
    /**
     * Information of item
     */
    private String itemInformation;

    // test for shawn uodatate129.161.52.212\\
    /**
     * Instantiates Main Frame
     * 
     * @param gMenu           Restaurant Menu
     * @param customerString  Customer name
     * @param fromEathnSocket Socket from Restaurant
     */
    public Mainframe(Menu gMenu, String customerString, Socket fromEathnSocket) {

        totalCost = new BigDecimal(0);
        itemInformation = "";

        String Customer = customerString;

        itemsOrdered = new ArrayList<MenuItem>();

        givMenu = gMenu;

        menuRead = new MenuReader(gMenu, Customer, fromEathnSocket);
        // menuRead.readInputFile();
        create();
        create();

        setSize(1500, 1500);
        setTitle("Customer Order");
        setBackground(Color.WHITE);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setExtendedState(getExtendedState() | MAXIMIZED_BOTH);
    }

    /**
     * Creates the pane display
     */
    public void create() {
        JPanel mainPanel = (JPanel) getContentPane();
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, getItemButtons(), getReceipt());

        splitPane.setDividerLocation(780);
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(splitPane, BorderLayout.CENTER);

    }

    /**
     * Set Up the buttons
     * 
     * @return JScrollPane
     */
    private JScrollPane getItemButtons() {

        pan = new JPanel();

        pan.setLayout(new GridLayout(0, 2));

        JPanel borderupper = new JPanel();
        borderupper.setLayout(new BorderLayout());

        JButton backbutton = new JButton("<-- back");
        borderupper.add(backbutton, BorderLayout.NORTH);

        backbutton.setBackground(Color.blue);
        backbutton.setOpaque(true);
        backbutton.setVisible(false);


        backbutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                pan.removeAll();
                repaint();
                revalidate();

                backbutton.setVisible(false);

                ArrayList<String> itemButtons = new ArrayList<String>();

                ArrayList<MenuItem> itemButtons2 = new ArrayList<MenuItem>();

                for (String category : givMenu.allItems().keySet()) {

                    itemButtons.add(category);

                }
                Border etchedBorder = BorderFactory.createEtchedBorder();
                Border border = BorderFactory.createTitledBorder(etchedBorder, "Categories", TitledBorder.DEFAULT_JUSTIFICATION,
                        TitledBorder.DEFAULT_POSITION, new Font("Lucida", Font.BOLD, 20), Color.BLACK);
                pan.setBorder(border);
        
                for (final String itemButton : itemButtons) {

                    final JButton createButton = new JButton(itemButton);
                    createButton.setToolTipText(itemButton);

                    createButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent arg0) {
                            refresh(itemButton);

                            pan.removeAll();
                            repaint();
                            revalidate();

                            for (String itemName : givMenu.getMenuItems(itemButton).keySet()) {

                                itemButtons2.add(givMenu.getMenuItems(itemButton).get(itemName));

                            }

                            backbutton.setVisible(true);

                            Border etchedBorder = BorderFactory.createEtchedBorder();
                            Border border = BorderFactory.createTitledBorder(etchedBorder, itemButton, TitledBorder.DEFAULT_JUSTIFICATION,
                                    TitledBorder.DEFAULT_POSITION, new Font("Lucida", Font.BOLD, 20), Color.BLACK);
                            pan.setBorder(border);
                    
                            

                            for (final MenuItem menuitemButton : itemButtons2) {

                                final JButton createButton2 = new JButton(menuitemButton.getName());
                                createButton2.setToolTipText(menuitemButton.getName());

                                createButton2.addActionListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent arg0) {
                                        refreshPanel(menuitemButton);

                                    }

                                });
                                pan.add(createButton2);
                                createButton2.setPreferredSize(new Dimension(30, 60));
                                repaint();
                                revalidate();

                            }

                        }

                        private void refresh(String itemButton) {

                        }
                    });
                    pan.add(createButton);
                    createButton.setPreferredSize(new Dimension(30, 60));

                }

            }
        });

        // map category:::(name : menuitem(name, timeTomake))

        ArrayList<String> itemButtons = new ArrayList<String>();

        ArrayList<MenuItem> itemButtons2 = new ArrayList<MenuItem>();


        Border etchedBorder = BorderFactory.createEtchedBorder();
        Border border = BorderFactory.createTitledBorder(etchedBorder, "Categories", TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.DEFAULT_POSITION, new Font("Lucida", Font.BOLD, 20), Color.BLACK);
        pan.setBorder(border);


        for (String category : givMenu.allItems().keySet()) {

            itemButtons.add(category);

        }



        for (final String itemButton : itemButtons) {

            final JButton createButton = new JButton(itemButton);
            createButton.setToolTipText(itemButton);

            createButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    Border etchedBorder = BorderFactory.createEtchedBorder();

                    Border border = BorderFactory.createTitledBorder(etchedBorder, itemButton, TitledBorder.DEFAULT_JUSTIFICATION,
                    TitledBorder.DEFAULT_POSITION, new Font("Lucida", Font.BOLD, 20), Color.BLACK);
            pan.setBorder(border);

                    refresh(itemButton);

                    pan.removeAll();
                    repaint();
                    revalidate();


                    
                    for (String itemName : givMenu.getMenuItems(itemButton).keySet()) {

                        itemButtons2.add(givMenu.getMenuItems(itemButton).get(itemName));


                    }

                    backbutton.setVisible(true);

                    for (final MenuItem menuitemButton : itemButtons2) {

                        final JButton createButton2 = new JButton(menuitemButton.getName());
                        createButton2.setToolTipText(menuitemButton.getName());


                        
                        createButton2.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent arg0) {
                                refreshPanel(menuitemButton);

                            }

                        });
                        pan.add(createButton2);
                        createButton2.setPreferredSize(new Dimension(30, 60));
                        repaint();
                        revalidate();

                    }
                    

                }

        
        

                private void refresh(String itemButton) {

                }
            });
            pan.add(createButton);

            createButton.setPreferredSize(new Dimension(30, 60));

        }

        borderupper.add(pan, BorderLayout.CENTER);

        JScrollPane scroller = new JScrollPane(borderupper);

     
        return scroller;

    }

    /**
     * Returns the receipt
     * 
     * @return JPanel of receipt
     */
    private JPanel getReceipt() {

        receipt = new JPanel();
        JLabel label = new JLabel("Customer Order:");
        receipt.setLayout(new BorderLayout());

        JPanel lowerPanel = new JPanel();
        lowerPanel.setLayout(new BorderLayout());

        receipt.add(lowerPanel, BorderLayout.SOUTH);
        receipt.add(label, BorderLayout.NORTH);

        centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(0, 2));

        JScrollPane centerPanelScroller = new JScrollPane(centerPanel);
        receipt.add(centerPanelScroller, BorderLayout.CENTER);

        JButton placeOrder = new JButton("Place Order");
        JButton clearOrder = new JButton("Clear Order");

        placeOrder.setPreferredSize(new Dimension(30, 50));
        clearOrder.setPreferredSize(new Dimension(30, 50));

        centerPanel.setBackground(Color.gray);
        placeOrder.setForeground(Color.GREEN);
        clearOrder.setForeground(Color.RED);

        placeOrder.setFont(new Font("Times New Roman", Font.BOLD, 40));
        clearOrder.setFont(new Font("Times New Roman", Font.BOLD, 40));

        clearOrder.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {

                delete();

            }

        });

        placeOrder.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (itemsOrdered.size() != 0) {
                        menuRead.logOrder(itemsOrdered, totalCost);
                        JOptionPane.showMessageDialog(getContentPane(), "Order has been sent to kitchen",
                                "Order has been logged", JOptionPane.INFORMATION_MESSAGE);
                        delete();
                    } else {
                        JOptionPane.showMessageDialog(null, "No items ordered", "Place order",
                                JOptionPane.ERROR_MESSAGE);
                    }

                } catch (IOException g) {

                    JOptionPane.showMessageDialog(null, "Error! Program terminated", " Error",
                            JOptionPane.ERROR_MESSAGE);
                }

            }

        });

        lowerPanel.add(placeOrder, BorderLayout.CENTER);
        lowerPanel.add(clearOrder, BorderLayout.SOUTH);
        lowerPanel.setBackground(Color.LIGHT_GRAY);
        receipt.setBackground(Color.WHITE);
        return receipt;

    }

    /**
     * Deletes items ordered
     */
    private void delete() {

        itemsOrdered.clear();
        itemInformation = "";

        centerPanel.removeAll();
        centerPanel.repaint();
        centerPanel.revalidate();

    }

    /**
     * Refreshes the panel
     * 
     * @param itemButton Button used to refresh panel
     */
    private void refreshPanel(final MenuItem itemButton) {
        String item = itemButton.getName();

        int itemPrice = itemButton.getTimeToMake();

        itemsOrdered.add(itemButton);

        JLabel orderItems = new JLabel(item);

        centerPanel.add(orderItems);

        JButton deleteButton = new JButton("REMOVE ITEM");

        deleteButton.setBackground(Color.RED);

        deleteButton.setOpaque(true);

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                centerPanel.remove(orderItems);
                itemsOrdered.remove(itemButton);
                centerPanel.remove(deleteButton);
                centerPanel.repaint();
                centerPanel.revalidate();
            }
        });

        centerPanel.add(deleteButton);
        centerPanel.revalidate();

        orderPrice += totalCost.intValue();
    }

}