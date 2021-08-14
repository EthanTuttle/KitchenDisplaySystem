package src.main.java.restaurantGUI;

import java.awt.*;
import javax.swing.*;
import javax.swing.JFrame;

public class RestuarantGUI extends JFrame{
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new RestuarantGUI();
            }
        });
    }

    public RestuarantGUI() {
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setJMenuBar(new GUIMenu(this));
        setTitle("Restaurant View");

        setVisible(true);
        setExtendedState(getExtendedState() | MAXIMIZED_BOTH);
    }

}
