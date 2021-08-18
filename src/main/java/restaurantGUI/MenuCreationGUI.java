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
        JPanel enclosingCategPanel = new JPanel();
        categPanel.add(categField);
        categPanel.add(addCategButton);

        addCategButton.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent event){
                if (categField.getText().equals("")){
                    return;
                }
                JPanel singleCategPanel = new JPanel();
                JPanel newMenuItemPanel = new JPanel();
                JLabel newLabel = new JLabel(categField.getText());
                JTextField newMenuItemField = new JTextField(10);
                JButton addMenuItemButton = new JButton(new ButtonAction("Add Menu Item",newMenuItemField,singleCategPanel));
                singleCategPanel.setLayout(new BoxLayout(singleCategPanel,BoxLayout.Y_AXIS));
                
                singleCategPanel.add(newLabel);
                newMenuItemPanel.add(newMenuItemField);
                newMenuItemPanel.add(addMenuItemButton);
                singleCategPanel.add(newMenuItemPanel);
                enclosingCategPanel.add(singleCategPanel);
                revalidate();
            }
        });

        add(categPanel,BorderLayout.NORTH);
        add(enclosingCategPanel,BorderLayout.CENTER);
    }
    class ButtonAction extends AbstractAction {
        String name;
        JPanel parentPanel;
        JTextField parentField;
        public ButtonAction(String name, JTextField pField, JPanel pPanel){
            super(name);
            this.name = name;
            this.parentPanel  = pPanel;
            this.parentField = pField;
        }

        public void actionPerformed(ActionEvent event){
            if (parentField.getText().equals("")){
                return;
            }
            
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
