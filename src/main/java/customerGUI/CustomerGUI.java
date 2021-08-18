package src.main.java.customerGUI;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        
    }
    
    

   public static void main(String[] args) {
     
        CustomerGUI g = new CustomerGUI();
        

      
   }








   
}