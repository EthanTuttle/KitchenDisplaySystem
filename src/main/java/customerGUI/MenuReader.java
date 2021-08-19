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

import javax.swing.plaf.TreeUI;

import jdk.jfr.SettingDefinition;
import src.main.java.Backend.Menu;
import src.main.java.Backend.MenuItem;

import java.math.BigDecimal;
import java.net.Socket;

/**
 * MenuReader class to read in a restaurant Menu
 */
public class MenuReader {
    /**
     * File of orders
     */
    private File orders;
    /**
     * Scanner to read menu
     */
    private Scanner readMenu;
    /**
     * Writer that logs orders
     */
    private BufferedWriter orderLogger;
    /**
     * FileWriter to log orders
     */
    private FileWriter orderLoggerStream;
    /**
     * Count of orders
     */
    private int count;
    /**
     * Input file for orders
     */
    private String inputFile;
    /**
     * Calendar
     */
    private Calendar calendar;
    /**
     * List of menu items
     */
    private ArrayList<MenuItem> menuItems;
    /**
     * Customer name
     */
    private String TheCUstomer;
    /**
     * Write out the orders
     */
    private PrintWriter out; // TODO: use to communicate order
    /**
     * Connection to restaurant
     */
    private Socket connection;

    /**
     * Instantiates Menu Reader
     * @param inputMenu Restaurant Menu
     * @param Cust Customer name
     * @param connSocket Socket of connection
     */
    public MenuReader(Menu inputMenu, String Cust, Socket connSocket) {

        menuItems = new ArrayList<MenuItem>();
        calendar = new GregorianCalendar();
        count = 1;
        TheCUstomer = Cust;

        connection = connSocket;

    }

    /**
     * Returns the menu items
     * @return List of menu items
     */
    public ArrayList<MenuItem> getMenuItems() {
        return menuItems;
    }

    /**
     * Logs order when order is placed
     * @param itemsOrdered Items you have ordered
     * @param orderPrice Price of orders
     * @throws IOException If we cannpt create a writer
     */
    public void logOrder(ArrayList<MenuItem> itemsOrdered, BigDecimal orderPrice) throws IOException {

        orderLogger = new BufferedWriter(orderLoggerStream);

        java.util.Date orderDate = new java.util.Date();

        String sendToSocket = "";

        out = new PrintWriter(connection.getOutputStream(), true);

        sendToSocket += new Timestamp(orderDate.getTime()) + ";";

        sendToSocket += (this.TheCUstomer + ";");

        for (MenuItem item : itemsOrdered) {

            sendToSocket += (item.getName() + ";");

        }
        out.println(sendToSocket);

        orderLogger.close();
    }


}
