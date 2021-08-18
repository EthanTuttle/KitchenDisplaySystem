package src.main.java.restaurantGUI;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuCreationGUI extends JFrame {


    public MenuCreationGUI(){
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Menu Configuration");

        setLayout(new FlowLayout());
        JTextField categField = new JTextField(10);
        JButton addCategButton = new JButton("Add Category");

        addCategButton.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent event){
                
            }
        });
    }
}
