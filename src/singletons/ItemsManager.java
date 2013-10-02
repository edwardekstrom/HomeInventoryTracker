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
	 * @precondition none
	 * @postcondition there is a new ItemsManager
	 */
	private ItemsManager(){
		assert true;
		_allItemsList = new ArrayList<Item>();
	}
	
	/**
	 * This method returns the ItemsManger singleton
	 * @precondition none
	 * @postcondition there is one and only one instance of the ItemsManager
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
	 * @precondition none
	 * @postcondition none
	 * @param item check this item to see if it can be added
	 * @return true if the item can be added, false otherwise
	 */
	public boolean canAddItem(Item item){
		if (_allItemsList.contains(item)){
			return false;
		}else{
		return true;
		}
	}
	
	/**
	 * Adds the item to the list of all items.
	 * @precondition none
	 * @postcondition the list contains the passed item.
	 * @param item the item to be added
	 */
	public void addItem(Item item){
		if(!_allItemsList.contains(item)){
		_allItemsList.add(item);
		}
	}
	
	/**
	 * Removes the item from the list of all items.
	 * @precondition none
	 * @postcondition the list doesn't contain the item
	 * @param item the item to be removed
	 */
	public void removeItem(Item item){
		_allItemsList.remove(item);
	}
	
	/**
	 * True if the manager contains the item,
	 * false otherwise.
	 * @precondition none
	 * @postcondition none
	 * @param item An item to check for.
	 * @return true if the manager contains the item,
	 * false otherwise.
	 */
	public boolean containsItem(Item item){
		return _allItemsList.contains(item);
	}
	
	/**
	 * Returns an unmodifiable all items list.
	 * @precondition none
	 * @postcondition there is an unmodifiable version of the _allItemsList
	 * @return
	 */
	public List<Item> getUnmodifiableAllItemsList(){
		return Collections.unmodifiableList(_allItemsList);
	}
}
