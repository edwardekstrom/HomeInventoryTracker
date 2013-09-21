package singletons;

import java.util.ArrayList;
import java.util.List;

import data_structures.Item;

/**
 * 
 * @author edwardekstrom
 * Singleton:  This class puts all items into one place for 
 * easy access throughout the whole program.
 */
public class ItemsManager {
	
	private static ItemsManager _instance = null;
	private List<Item> _allItemsList;
	
	/**
	 * This method instantiates the instance of ItemsManager.
	 * It will only ever be called one time.
	 */
	private ItemsManager(){
		_allItemsList = new ArrayList<Item>();
	}
	
	/**
	 * This method returns the ItemsManger singleton
	 * @return ItemsManager, the only instance of it
	 */
	public static ItemsManager getInstance(){
		if (_instance == null){
			_instance = new ItemsManager();
		}
		return _instance;
	}
}
