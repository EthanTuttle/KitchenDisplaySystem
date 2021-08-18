package src.main.java.restaurantGUI;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import src.main.java.Backend.Menu;
import java.util.Iterator;

public class MenuCreationGUI extends JPanel {

    public MenuCreationGUI(Menu menu){
        setLayout(new BorderLayout());
        JTextField categField = new JTextField(10);
        JButton addCategButton = new JButton("Add Category");
        categPanel = new JPanel();
        enclosingCategPanel = new JPanel();
        scrollPanel = new JScrollPane(enclosingCategPanel);
        
        enclosingCategPanel.setLayout(new BoxLayout(enclosingCategPanel, BoxLayout.X_AXIS));
        categPanel.add(categField);
        categPanel.add(addCategButton);

        addCategButton.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent event){
                if (categField.getText().equals("")){
                    return;
                }
                JPanel singleCategPanel = new JPanel();
                JPanel enclosedSingleCategPanel = new JPanel(new BorderLayout());
                singleCategPanel.setLayout(new BoxLayout(singleCategPanel,BoxLayout.Y_AXIS));
                FlowLayout fl = new FlowLayout(FlowLayout.CENTER,0,0);
                JPanel newMenuItemPanel = new JPanel(fl);
                System.out.println(fl.getVgap());
                
                JLabel newLabel = new JLabel(categField.getText());
                newLabel.addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mousePressed(MouseEvent e)
                    {
                        handleMousePress(newLabel, "category");
                    }
                });
                JTextField newMenuItemField = new JTextField(10);
                JButton addMenuItemButton = new JButton(new ButtonAction("Add Menu Item",
                newMenuItemField,singleCategPanel));


                newMenuItemPanel.add(newMenuItemField);
                newMenuItemPanel.add(addMenuItemButton);
                
                
                singleCategPanel.add(newLabel);
                singleCategPanel.add(newMenuItemPanel);
                enclosedSingleCategPanel.add(singleCategPanel,BorderLayout.NORTH);
                enclosingCategPanel.add(enclosedSingleCategPanel);
            

                revalidate();
                scrollPanel.revalidate();
            }
        });

        add(categPanel,BorderLayout.NORTH);
        add(scrollPanel,BorderLayout.CENTER);
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

            JLabel newMenuItemLabel = new JLabel(parentField.getText());
            newMenuItemLabel.addMouseListener(new MouseAdapter()
            {
                @Override
                public void mousePressed(MouseEvent e)
                {
                    handleMousePress(newMenuItemLabel, "menu_item");
                }
            });
            parentPanel.add(newMenuItemLabel);
            parentPanel.revalidate();
            scrollPanel.revalidate();
            
        }
    }
    public void handleMousePress(JLabel text, String type)
    {
        String updatedString = null;
        if (type.toLowerCase().equals("category"))
        {
		    updatedString = JOptionPane.showInputDialog(this, "What would you like to rename the category to?");
        }
        else if (type.toLowerCase().equals("menu_item"))
        {
		    updatedString = JOptionPane.showInputDialog(this, "What would you like to rename the menu item to?");
        }
        else{
            // The type provided is unexpected.
            System.err.println("Received type: "+type+" which was not expected.");
            return;
        }

        if (updatedString != null)
        {
            text.setText(updatedString);
        }
    }
    private JScrollPane scrollPanel;
    private JPanel categPanel;
    private JPanel enclosingCategPanel;
}
