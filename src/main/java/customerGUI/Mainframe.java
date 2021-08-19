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

public class Mainframe extends JFrame {

	private BigDecimal totalCost;
	private MenuReader menuRead;
	private JPanel receipt;
	private JPanel centerPanel;
    private Menu givMenu;
	private int orderPrice;
	private ArrayList <MenuItem> itemsOrdered;

	private String itemInformation;
	
	public Mainframe(Menu gMenu,String customerString) throws FileNotFoundException {
		
		totalCost = new BigDecimal(0);
		itemInformation = "";
		
		String Customer = customerString;

		itemsOrdered = new ArrayList<MenuItem>();

        givMenu = gMenu;
		// menuRead = new MenuReader(givenMenu,Customer);
		// menuRead.readInputFile();
		create();
		create();
		
		setSize(1500,1500);
		setTitle("Restaurant Menu System");
		setBackground(Color.WHITE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

	}
	
	
	public void create() {
		JPanel mainPanel = (JPanel) getContentPane();
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, getItemButtons(), getReceipt());
		
		splitPane.setDividerLocation(780);
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(splitPane, BorderLayout.CENTER);
		
		
	}

	private JScrollPane getItemButtons() {
		JPanel pan = new JPanel();
		pan.setLayout(new GridLayout(0,2));
		







    // map category:::(name : menuitem(name, timeTomake))



        ArrayList<MenuItem> itemButtons  = new ArrayList<MenuItem>();

        for(String category : givMenu.allItems().keySet()){

                for( String itemName : givMenu.getMenuItems(category).keySet()){

                   itemButtons.add(givMenu.getMenuItems(category).get(itemName));

                }

        }



		

		for (final MenuItem itemButton: itemButtons) {

			final JButton createButton = new JButton(itemButton.getName());
			createButton.setToolTipText(itemButton.getName());
			
			createButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					refreshPanel(itemButton);
				}
			});
			pan.add(createButton);
			createButton.setPreferredSize(new Dimension(30,60));
			
		}
		
		JScrollPane scroller = new JScrollPane(pan);
		Border etchedBorder = BorderFactory.createEtchedBorder();
		Border border = BorderFactory.createTitledBorder(etchedBorder, "Items",TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Lucida", Font.BOLD, 20) , Color.BLACK);
		pan.setBorder(border);
		return scroller;

		}

	private JPanel getReceipt() {
		
		receipt = new JPanel();
		JLabel label = new JLabel("Customer Order:");
		receipt.setLayout(new BorderLayout());
		
		JPanel lowerPanel = new JPanel();
		lowerPanel.setLayout(new BorderLayout());
		
		receipt.add(lowerPanel,BorderLayout.SOUTH);
		receipt.add(label, BorderLayout.NORTH);
		
		centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(0,2));
		




	


		


		
		JScrollPane centerPanelScroller = new JScrollPane(centerPanel);
		receipt.add(centerPanelScroller, BorderLayout.CENTER);
		
	
		
		JButton placeOrder = new JButton("Place Order");
		JButton clearOrder = new JButton("Clear Order");
		
		placeOrder.setPreferredSize(new Dimension(30,50));
		clearOrder.setPreferredSize(new Dimension(30,50));
		
		centerPanel.setBackground(Color.gray);
		placeOrder.setForeground(Color.GREEN);
		clearOrder.setForeground(Color.RED);
		
		placeOrder.setFont(new Font ("Times New Roman", Font.BOLD,40));
		clearOrder.setFont(new Font ("Times New Roman", Font.BOLD,40));
		
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
						JOptionPane.showMessageDialog(getContentPane(), "Order has been sent to kitchen", "Order has been logged", JOptionPane.INFORMATION_MESSAGE);
						delete();
					}
					else {
						JOptionPane.showMessageDialog(null,"No items ordered", "Place order", JOptionPane.ERROR_MESSAGE);
					}
					
				}
				 catch (IOException g) {
					
					 JOptionPane.showMessageDialog(null, "Error! Program terminated"
							 , " Error", JOptionPane.ERROR_MESSAGE);
				}
				
			}
			
		});
		
		lowerPanel.add(placeOrder, BorderLayout.CENTER);
		lowerPanel.add(clearOrder, BorderLayout.SOUTH);
		lowerPanel.setBackground(Color.LIGHT_GRAY);
		receipt.setBackground(Color.WHITE);
		return receipt;
		
	}
	
	private void delete() {
		
		
		itemsOrdered.clear();
		itemInformation = "";
	
		centerPanel.removeAll();
		centerPanel.repaint();
		centerPanel.revalidate();
		
	}
	
	private void refreshPanel(final MenuItem itemButton) {
		String item = itemButton.getName();

		int itemPrice = itemButton.getTimeToMake();
		 
		 itemsOrdered.add(itemButton);
	
		 JLabel orderItems = new JLabel(item);


		centerPanel.add(orderItems);


		JButton deleteButton = new JButton("REMOVE ITEM");

		deleteButton.setBackground(Color.RED);

		deleteButton.setOpaque(true);


		deleteButton.addActionListener(new ActionListener(){
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