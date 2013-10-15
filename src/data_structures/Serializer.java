package data_structures;

import static org.junit.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Serializer {
	
	/**
	 * Serializes a HomeInventory
	 * @param homeInventory
	 */
	public static void serializeHIT(HomeInventory homeInventory){
		//System.out.println(hi.getStorageUnitsCount());
		String filename = "hit.ser";
		
		// Save the HomeInventory to file
		FileOutputStream output = null;
		ObjectOutputStream out = null;
		
		try{
			output = new FileOutputStream(filename);
			out = new ObjectOutputStream(output);
			out.writeObject(homeInventory);
			out.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Returns the last serialized HomeInventory
	 * @return HomeInventory
	 */
	public static HomeInventory deserializeHIT(){
		String filename = "hit.ser";
		// Read the HomeInventory from file
		HomeInventory homeInventory = null;
		FileInputStream input = null;
		ObjectInputStream in = null;
		try {
			input = new FileInputStream(filename);
			in = new ObjectInputStream(input);
			homeInventory = (HomeInventory) in.readObject();
			in.close();
			//System.out.println(hi.getStorageUnitsCount());
			assertTrue(homeInventory.getStorageUnitsCount() == 1);
		}catch (Exception e){
			return new HomeInventory();
		}
		return homeInventory;
	}
}
