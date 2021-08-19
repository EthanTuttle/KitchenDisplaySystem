package src.main.java.customerGUI;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.JOptionPane;

import src.main.java.Backend.Menu;


public class RestaurantTester {


	public static void main(String[] args) throws FileNotFoundException {
		
		try{
		
			File inputFile = new File("/Users/johnfoster/Desktop/NinjaFastKitchenSystem3000/src/main/java/customerGUI/orders.txt");

			String CustomerName = JOptionPane.showInputDialog("Enter Name");
			
			Mainframe guiFrame = new Mainframe(new Menu(), CustomerName);
		}
		
		catch(FileNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Error! Menu File not found!", "Please reinput", JOptionPane.ERROR_MESSAGE);
		}
		catch(Exception e) {
		
			JOptionPane.showMessageDialog(null, "Error! Program terminated"
					 , " Error", JOptionPane.ERROR_MESSAGE);
		}
		
		
	}

}