package src.main.java.customerGUI;

import java.awt.*;
import javax.swing.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.Semaphore;
import java.io.*;

public class CustomerGUI {

    private Semaphore loadSemaphore = new Semaphore(1);
    private Socket connection;
    private BufferedReader in;
    private String rpiIP = "129.161.52.212"; //change last 3 digits to host ID of machine running restaurant GUI
    private src.main.java.Backend.Menu menu;

    public CustomerGUI() {
        
        //create jdialog for input
        JPanel input = new JPanel();
        input.setLayout(new GridLayout(0, 2));
        input.add(new JLabel("Name: "));
        JTextField nameInput = new JTextField();
        input.add(nameInput);
        input.add(new JLabel("IP: "));
        JTextField ipInput = new JTextField();
        input.add(ipInput);
        int result = JOptionPane.showConfirmDialog(null, input, "Enter the information to run the program", JOptionPane.OK_CANCEL_OPTION);

        //pull results
        if (result == JOptionPane.OK_OPTION) {
            if (nameInput.getText().length() == 0 || ipInput.getText().length() == 0 ) {
                JOptionPane.showMessageDialog(null, "Must enter all inputs", "Input Error", JOptionPane.ERROR_MESSAGE);
            } else {
                rpiIP = ipInput.getText();
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
                    new Mainframe(menu, nameInput.getText(), connection);
                    loadSemaphore.release();
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, e, "Connection Error", JOptionPane.ERROR_MESSAGE);
                } catch (InterruptedException e) {
                    JOptionPane.showMessageDialog(null, e, "Thread Error", JOptionPane.ERROR_MESSAGE);
                }
                
            }
        }

        
    }
    
   public static void main(String[] args) {
        new CustomerGUI();
   }   
}