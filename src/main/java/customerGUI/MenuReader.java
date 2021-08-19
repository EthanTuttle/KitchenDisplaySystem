package src.main.java.customerGUI;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

import src.main.java.Backend.Menu;
import src.main.java.Backend.MenuItem;

import java.math.BigDecimal;
import java.net.Socket;

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
    private PrintWriter out; //TODO: use to communicate order
    private Socket connection;

    


	public MenuReader(Menu inputMenu, String Cust,Socket connSocket) {
		
		menuItems = new ArrayList<MenuItem>();
		calendar = new GregorianCalendar();
		count = 1;
		TheCUstomer = Cust;

        connection = connSocket;


        
        
    }



	public ArrayList<MenuItem> getMenuItems() {
		return menuItems;
	}
	

	public void logOrder(ArrayList<MenuItem> itemsOrdered, BigDecimal orderPrice) throws IOException {
	
		orderLoggerStream = new FileWriter(getFileInstance(),true);
		orderLogger = new BufferedWriter(orderLoggerStream);
		
		java.util.Date orderDate = new java.util.Date();
	
        out = new PrintWriter(connection.getOutputStream(), true);

		out.println(" " + new Timestamp(orderDate.getTime()) + ";");

		out.println(this.TheCUstomer + ";");



		for (MenuItem item: itemsOrdered) {

			out.println(item.getName());


			
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
