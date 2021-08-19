package src.main.java.restaurantGUI;

import java.io.*;
import java.util.*;

import src.main.java.Backend.Menu;
import src.main.java.Backend.MenuItem;

/**
 * Exit Procedure Class stores a menu for a future run of the application
 */
public class ExitProcedure 
{
	/**
	 * Stores the menu for future runs of the application
	 * @param menu Menu to Store
	 */
	public static void exitProcedure(Menu menu)
	{
			// create a new menu file and populate it with default settings
			FileWriter fileWriter = null;
			BufferedWriter bufferedWriter = null;
			try{
				fileWriter = new FileWriter(".menu");
				bufferedWriter = new BufferedWriter(fileWriter);
				String line = "";
				Map<String, HashMap<String, MenuItem>> itr = menu.getMenu();
				// Loop through LinkedHashMap<String, HashMap<String, MenuItem>> in the menu 
				for (String category : itr.keySet()){
					HashMap<String, MenuItem> itr2 = itr.get(category);
					if (itr2.keySet().isEmpty()){
						line="category="+category+";menu_item= ;timeToMake= ";
						bufferedWriter.write(line);
						bufferedWriter.newLine();
					}
					else{
						// Loop through HashMap<String, MenuItem> in the menu 
						for (String menuItem : itr2.keySet()){
							MenuItem item = itr2.get(menuItem);
							String mapCategory = " ";
							String mapItemName = " ";
							if (item != null){
								// No mapping for a menuItem to 
								mapCategory = category;
								mapItemName = menuItem;
							}
							line="category="+mapCategory+";menu_item="+mapItemName+";timeToMake="+item.getTimeToMake();
							bufferedWriter.write(line);
							bufferedWriter.newLine();
						}
					}
				}
			}
			catch(IOException e){
				e.printStackTrace();
			}
			finally{
				try{
					if (bufferedWriter != null) bufferedWriter.close();
					if (fileWriter != null) fileWriter.close();
				}
				catch (IOException e){
					/* Error caught while trying to close the Writer streams */
					e.printStackTrace();
				}
			}
	}
}
