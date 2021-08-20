package src.main.java.customerGUI;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import src.main.java.Backend.Menu;
import src.main.java.Backend.MenuItem;
import java.math.BigDecimal;
import java.net.Socket;

public class MenuReader {

    private ArrayList<MenuItem> menuItems;
    private String TheCUstomer;
    private PrintWriter out; // TODO: use to communicate order
    private Socket connection;

    public MenuReader(Menu inputMenu, String Cust, Socket connSocket) {

        menuItems = new ArrayList<MenuItem>();
        TheCUstomer = Cust;

        connection = connSocket;

    }

    public ArrayList<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void logOrder(ArrayList<MenuItem> itemsOrdered, BigDecimal orderPrice) throws IOException {

      
        java.util.Date orderDate = new java.util.Date();

        String sendToSocket = "";

        out = new PrintWriter(connection.getOutputStream(), true);

        sendToSocket += new Timestamp(orderDate.getTime()) + ";";

        sendToSocket += (this.TheCUstomer + ";");

        for (MenuItem item : itemsOrdered) {

            sendToSocket += (item.getName() + ";");

        }
        out.println(sendToSocket);
    }

}
