package src.main.java.customerGUI;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

import src.main.java.Backend.MenuItem;

import java.math.BigDecimal;

public class MenuReader {

	private File orders;
	private Scanner readMenu;
	private BufferedWriter orderLogger;
	private FileWriter orderLoggerStream;
	private int count;
	private String inputFile;
	private Calendar calendar;
	private ArrayList<MenuItem> menuItems;
	private String TheCUstomer;
	public MenuReader(File inputMenu, String Cust) throws FileNotFoundException {
		
		menuItems = new ArrayList<MenuItem>();
		readMenu = new Scanner(inputMenu);
		calendar = new GregorianCalendar();
		count = 1;
		TheCUstomer = Cust;
		inputFile = inputMenu.getName();
	}
	
	public void readInputFile() {

		while (readMenu.hasNextLine()) {
			
			String item = readMenu.nextLine();
			int time2make = 1;
			
			MenuItem createItem = new MenuItem("blank", time2make,item);
			menuItems.add(createItem);
		}
		readMenu.close();
	}

	public ArrayList<MenuItem> getMenuItems() {
		return menuItems;
	}
	

	public void logOrder(ArrayList<MenuItem> itemsOrdered, BigDecimal orderPrice) throws IOException {
	
		orderLoggerStream = new FileWriter(getFileInstance(),true);
		orderLogger = new BufferedWriter(orderLoggerStream);
		
		java.util.Date orderDate = new java.util.Date();
	
		
		orderLogger.write(" " + new Timestamp(orderDate.getTime()) + ";");

		orderLogger.write(this.TheCUstomer + ";");

		for (MenuItem item: itemsOrdered) {

			orderLogger.write(item.getName() + ";");


			
		}

	
		
		orderLogger.close();
	}

	
	private File getFileInstance() {
		
		if (orders == null) {
			String fileName = inputFile + " " + "ItemOrders.txt";
			orders = new File(fileName);
		}
		return orders;
	}
}
