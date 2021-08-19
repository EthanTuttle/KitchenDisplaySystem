package src.main.java.customerGUI;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

<<<<<<< HEAD
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class CustomerGUI implements ActionListener ,ChangeListener {

    JButton button;
    JButton buttonmainCourse;
    JButton buttonSides;
    JButton buttonDrinks;
    JButton buttonAPPs;
    


    JFrame frame;

    public CustomerGUI(){

         frame = new JFrame("Demo Frame");

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
  
        JPanel panel = new JPanel();
        frame.getContentPane();
  


        JPanel buttonPanel = new JPanel();

      button = new JButton("Checkout");
      buttonAPPs = new JButton("Apps");
      buttonmainCourse = new JButton("Main Courses");

      buttonSides = new JButton("Sides");
      buttonDrinks = new JButton("Drinks");

      button.setActionCommand("Checkout");


      button.addActionListener(this);

  
  
  
        Dimension size = button.getPreferredSize();
  
        button.setBounds(1200, 730, size.width +30, size.height+30 ) ;
        button.setBackground(Color.green);
        button.setOpaque(true);

=======
import java.awt.*;
import javax.swing.*;
import java.net.Socket;
import java.io.*;
>>>>>>> ce493a2bd2171eccbdca8547f597990f9621046f


        buttonPanel.add(buttonmainCourse);
        buttonPanel.add(buttonSides);
        buttonPanel.add(buttonDrinks);
        buttonPanel.add(buttonAPPs);

        panel.setLayout(null);
        panel.add(button);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        frame.add(buttonPanel);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setSize(500, 300);
        frame.setVisible(true);
    }

<<<<<<< HEAD

    public void init(Container container){};



    @Override
    public void stateChanged(ChangeEvent e) {
        
    }
    
    
    
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {

        String command = e.getActionCommand();
        if (command.equals("Checkout")) {

            System.out.println("ORDER PLACED");
          }
        
=======
    private Socket connection;
    private PrintWriter out; //TODO: use to communicate order
    private BufferedReader in;
    private String rpiIP = "129.161.136.111"; //change last 3 digits to host ID of machine running restaurant GUI
    private src.main.java.Backend.Menu menu;
    private boolean menuLoaded = false;

    public CustomerGUI() {
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setJMenuBar(new GUIMenu(this));
        setTitle("Customer View");

        //add panels here
        
        try {
            connection = new Socket(rpiIP, 55555);
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
>>>>>>> ce493a2bd2171eccbdca8547f597990f9621046f
    }
    
    

   public static void main(String[] args) {
     
        CustomerGUI g = new CustomerGUI();
        

      
   }








   
}