package model;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.sun.org.apache.bcel.internal.generic.StoreInstruction;

import singletons.BarcodeManager;
import singletons.ItemsManager;
import singletons.ProductsManager;

public class Serializer {
	
	/**
	 * Serializes a HomeInventory
	 * @param homeInventory
	 */
	public static void serializeHIT(HomeInventory homeInventory){
		String filename = "hit.ser";
		
		storeManagers();
		
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
	
	private static void storeManagers(){
		BarcodeManager.getInstance().storeBarcodeList();
		ItemsManager.getInstance().storeBarcodeList();
		ProductsManager.getInstance().storeBarcodeList();
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

		}catch (Exception e){
			return new HomeInventory();
		}
		return homeInventory;
	}
	
	public static void de_storeManagers(){
		BarcodeManager.getInstance().de_storeBarcodeList();
		ItemsManager.getInstance().de_storeBarcodeList();
		ProductsManager.getInstance().de_storeBarcodeList();
	}
}
