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
    public void handleMousePress(JLabel text, String type)
    {
        String updatedString = null;
        if (type.toLowerCase().equals("category"))
        {
		    updatedString = JOptionPane.showInputDialog(this, "What name would you like to use? (You must choose a name).");
        }
        else if (type.toLowerCase().equals("menu_item"))
        {
		    updatedString = JOptionPane.showInputDialog(this, "What name would you like to use? (You must choose a name).");
        }
        else{
            // The type provided is unexpected.
            System.err.println("Received type: "+type+" which wwas not expected.");
            return;
        }

        if (updatedString != null)
        {
            text.setText(updatedString);
        }
    }
}
