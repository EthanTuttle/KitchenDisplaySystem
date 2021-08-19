package src.main.java.restaurantGUI;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import src.main.java.Backend.Menu;
import src.main.java.Backend.MenuItem;

import java.util.ArrayList;
import java.util.Iterator;

public class MenuCreationGUI extends JPanel {

    private ArrayList<JTextField> itemFields = new ArrayList<>();
    private ArrayList<JButton> itemButtons = new ArrayList<>();
    private boolean loadingMenu = false;
    private Menu menu;

    public MenuCreationGUI(Menu menu){
        setLayout(new BorderLayout());
        this.menu = menu;
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
                if (!(checkValidValue(categField.getText(),"category"))){
                    return;
                }
                JPanel singleCategPanel = new JPanel();
                JPanel enclosedSingleCategPanel = new JPanel(new BorderLayout());
                singleCategPanel.setLayout(new BoxLayout(singleCategPanel,BoxLayout.Y_AXIS));
                FlowLayout fl = new FlowLayout(FlowLayout.CENTER,0,0);
                FlowLayout fl2 = new FlowLayout(FlowLayout.CENTER,10,0);
                JPanel newMenuItemPanel = new JPanel(fl);
                JPanel labelAndExitPanel = new JPanel(fl2);
                JLabel categLabel = new JLabel(categField.getText().strip());
                categLabel.addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mousePressed(MouseEvent e)
                    {
                        handleMousePress(categLabel, "category");
                    }
                });
                JButton addMenuItemButton = new JButton(new ButtonAction("Add Menu Item", singleCategPanel));
                JButton removeComponentButton = new JButton(new RemoveComponentAction("X", enclosingCategPanel, enclosedSingleCategPanel));
                singleCategPanel.setName(categLabel.getText());
                newMenuItemPanel.setName(categLabel.getText());
                newMenuItemPanel.add(addMenuItemButton);
                labelAndExitPanel.setName("category="+"&menu_item=&"+categLabel.getText());
                labelAndExitPanel.add(categLabel);
                labelAndExitPanel.add(removeComponentButton);

                singleCategPanel.add(labelAndExitPanel);
                singleCategPanel.add(newMenuItemPanel);
                enclosedSingleCategPanel.add(singleCategPanel,BorderLayout.NORTH);
                enclosingCategPanel.add(enclosedSingleCategPanel);
                menu.addCategory(categLabel.getText());
                revalidate();
                scrollPanel.revalidate();
            }
        });

        add(categPanel,BorderLayout.NORTH);
        add(scrollPanel,BorderLayout.CENTER);

        // If a menu exists than load it instead of showing an empty menu
        loadingMenu = true;
        Iterator<String> itr1 = menu.allItems().keySet().iterator();
        while (itr1.hasNext()) {
            String category = itr1.next();
            categField.setText(category);
            addCategButton.getActionListeners()[0].actionPerformed(null);
            Iterator<String> itr2 = menu.allItems().get(category).keySet().iterator();
            while (itr2.hasNext()) {
                String name = itr2.next(); 
                MenuItem item = menu.allItems().get(category).get(name);
                itemFields.get(itemFields.size()-1).setText(item.getName());
                itemButtons.get(itemButtons.size()-1).getActionListeners()[0].actionPerformed(null);
            }
        }
        loadingMenu = false;
    }
    public boolean checkValidValue(String value, String type){
        if (type.equals("category")){
            /*check dup category or empty value*/
            if (menu.containsCategory(value) || value.equals("")){
                return false;
            }
        }
        else if (type.equals("menu_item")){
            // Check that the value is not empty before checking if any values can exist
            String[] itemTimeCombo = value.split("  ");
            // Check that the value is not empty before checking if any values can exist
            // If values exist check that at least 2 values were parsed
            if (value.equals("") || itemTimeCombo.length < 2){
                return false;
            }
            String menuItem = itemTimeCombo[0];
            String timeToMake = itemTimeCombo[1];
            /*check that the menu item is not empty and that the menu item does not already exist*/
            if (menuItem.equals("") || menu.findMenuItem(menuItem) != null){
                return false;
            }
            try {
                Integer actualTimeToMake = Integer.parseInt(timeToMake);
                if (actualTimeToMake<0){
                    throw new Exception();
                }
            }
            catch (Exception n){
                return false;
            }

        }
        return true;
    }
    class ButtonAction extends AbstractAction {
        String name;
        JPanel parentPanel;
        public ButtonAction(String name, JPanel pPanel){
            super(name);
            this.name = name;
            this.parentPanel  = pPanel;
        }

        public void actionPerformed(ActionEvent event){
            JTextField menuItem = new JTextField();
            JTextField menuItemETM = new JTextField();
            JComponent[] components = new JComponent[] {
                new JLabel("Enter the name of the item"),
                menuItem,
                new JLabel("Enter the estimated time to make the menu item"),
                menuItemETM,
            };
            int result = JOptionPane.showConfirmDialog(null, components, "Add new menu item", JOptionPane.YES_NO_OPTION);
            if(result == JOptionPane.OK_OPTION) {
                JLabel itemTimeCombo = new JLabel(menuItem.getText().strip()+"  "+menuItemETM.getText().strip());
                if (!(checkValidValue(itemTimeCombo.getText(),"menu_item"))){
                    return;
                }
                menu.addMenuItem(parentPanel.getName(), menuItem.getText().strip(), Integer.parseInt(menuItemETM.getText().strip()));
                itemTimeCombo.addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mousePressed(MouseEvent e)
                    {
                        handleMousePress(itemTimeCombo, "menu_item");
                    }
                });
                JPanel labelAndExitPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,10,0));
                JButton removeComponentButton = new JButton(new RemoveComponentAction("X", this.parentPanel, labelAndExitPanel));
                labelAndExitPanel.setName("category="+this.parentPanel.getName().strip()+"&menu_item="+menuItem.getText().strip());
                labelAndExitPanel.add(itemTimeCombo);
                labelAndExitPanel.add(removeComponentButton);

                parentPanel.add(labelAndExitPanel);
                parentPanel.revalidate();
                scrollPanel.revalidate();
            }
        }
    }
    class RemoveComponentAction extends AbstractAction{
        String name;
        JPanel parentPanel;
        JPanel childPanel;
        public RemoveComponentAction(String name, JPanel parentPanel, JPanel childPanel)
        {
            super(name);
            this.name = name;
            this.parentPanel = parentPanel;
            this.childPanel = childPanel;
        }
        public void actionPerformed(ActionEvent event)
        {
            // this.childPanel.getName() will return
            // category=?&menu_item=?
            

            // Split the query into category=? AND menu_item=?
            String[] query = this.childPanel.getName().split("&");
            String category = "";
            String menu_item = "";

            // parse, split and get the category and menu item
            String[] values = query[0].split("=");
            category = values[1];
            values = query[1].split("=");
            menu_item = values[1];
            menu.remove(category, menu_item, menu_item.equals("") ? "category" : "menu_item");
            this.parentPanel.remove(this.childPanel);
            this.parentPanel.revalidate();
            this.parentPanel.repaint();
        }
    }
    public void handleMousePress(JLabel text, String type)
    {
        String updatedString = null;
        JTextArea updatedMenuItem = new JTextArea();
        JTextArea updatedMenuItemETM = new JTextArea();
        if (type.toLowerCase().equals("category"))
        {
		    updatedString = JOptionPane.showInputDialog(this, "What would you like to rename the category to?");
            if (!checkValidValue(updatedString,"category")){
            }
        }
        else if (type.toLowerCase().equals("menu_item"))
        {
            JComponent[] components = new JComponent[] {
                new JLabel("Enter the name of the item"),
                updatedMenuItem,
                new JLabel("Enter the estimated time to make the menu item"),
                updatedMenuItemETM,
            };
            int result = JOptionPane.showConfirmDialog(null, components, "Add new menu item", JOptionPane.YES_NO_OPTION);
            if(result == JOptionPane.OK_OPTION) {
                // Check if the input is valid
                updatedString = updatedMenuItem.getText()+"  "+updatedMenuItemETM.getText();
                if (!checkValidValue(updatedString,"menu_item")){
                    return;
                }
            }
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
