
package tutorial;

import java.util.*;
import com.google.gson.*;
import java.io.*;
import classes.*;

public class ConvertJSON {
	
	public ConvertJSON() { }
	
	public boolean convert(ArrayList<Doc> articles) 
	{
		
		Gson gson = new Gson();
		
		try {
			FileWriter writer = new FileWriter("example.json");
			for (Doc d : articles) 
			{
				String temp = gson.toJson(d);
				writer.write(temp);
			}
			writer.close();			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}
}
