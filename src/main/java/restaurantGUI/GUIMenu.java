package src.main.java.restaurantGUI;

import javax.swing.JMenuBar;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIMenu  extends JMenuBar {
    
    public GUIMenu(RestaurantGUI gui) {
        JMenu view = new JMenu("View");
        JMenuItem active = new JMenuItem("Active Order Display");
        JMenuItem menu = new JMenuItem("Menu Creation Display");
        active.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                gui.displayActiveOrdersDisplay();
            }
        });
        menu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                gui.displayMenuCreationGUI();
            }
        });
        view.add(active);
        view.add(menu);
        add(view);
    }

}
