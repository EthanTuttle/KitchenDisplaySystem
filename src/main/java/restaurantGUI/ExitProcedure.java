package src.main.java.restaurantGUI;

import java.io.*;
import java.util.*;

import src.main.java.Backend.Menu;
import src.main.java.Backend.MenuItem;

public class ExitProcedure 
{
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
				for (String category : itr.keySet()){
					HashMap<String, MenuItem> itr2 = itr.get(category);
					if (itr2.keySet().isEmpty()){
						line="category="+category+"&menu_item= &timeToMake= ";
						bufferedWriter.write(line);
						bufferedWriter.newLine();
					}
					else{
						for (String menuItem : itr2.keySet()){
							MenuItem item = itr2.get(menuItem);
							line="category="+item.getCategory()+"&menu_item="+item.getName()+"&timeToMake="+item.getTimeToMake();
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
