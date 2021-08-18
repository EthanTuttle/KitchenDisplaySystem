package src.main.java.restaurantGUI;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuCreationGUI extends JPanel {


    public MenuCreationGUI(){
        setLayout(new BorderLayout());
        JTextField categField = new JTextField(10);
        JButton addCategButton = new JButton("Add Category");
        JPanel categPanel = new JPanel();
        categPanel.add(categField);
        categPanel.add(addCategButton);

        addCategButton.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent event){
                if (categField.getText().equals("")){
                    return;
                }
                JLabel newLabel = new JLabel(categField.getText());
                add(newLabel);
            }
        });
    }
    class ButtonAction extends AbstractAction {
        String name;

        public ButtonAction(String name){
            super(name);
            this.name = name;
        }

        public void actionPerformed(ActionEvent event){

        }
    }
}
