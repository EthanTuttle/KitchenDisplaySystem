package src.main.java.restaurantGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * JMenu Bar that switches between Restaurant Display and Menu Creation
 */
public class GUIJMenuBar  extends JMenuBar {
    
    /**
     * Instantiates JMenuBar for the RestaurantGUI
     * @param gui RestauranGUI
     */
    public GUIJMenuBar(RestaurantGUI gui) {
        JMenu view = new JMenu("View");
        JMenuItem active = new JMenuItem("Active Order Display");
        JMenuItem menu = new JMenuItem("Menu Creation Display");
        JMenuItem title = new JMenuItem("Title Screen");
        active.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                gui.displayActiveOrdersDisplay();
            }
        });
        menu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                gui.displayMenuCreationGUI();
            }
        });
        title.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                gui.displayTitle();
            }
        });
        view.add(title);
        view.add(active);
        view.add(menu);
        add(view);
    }

}
