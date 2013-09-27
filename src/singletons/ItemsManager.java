package singletons;

import java.util.ArrayList;
import java.util.Collections;
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
	
	/**
	 * Returns true if you can add this item,
	 * false otherwise.
	 * @param item check this item to see if it can be added
	 * @return true if the item can be added, false otherwise
	 */
	public boolean canAddItem(Item item){
		return true;
	}
	
	/**
	 * Adds the item to the list of all items.
	 * @param item the item to be added
	 */
	public void addItem(Item item){
		_allItemsList.add(item);
	}
	
	/**
	 * Removes the item from the list of all items.
	 * @param item the item to be removed
	 */
	public void removeItem(Item item){
		_allItemsList.remove(item);
	}
	
	/**
	 * Returns an unmodifiable all items list.
	 * @return
	 */
	public List<Item> getUnmodifiableAllItemsList(){
		return Collections.unmodifiableList(_allItemsList);
	}
}
