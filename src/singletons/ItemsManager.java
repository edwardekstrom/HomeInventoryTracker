package singletons;

import hit_exceptions.NullExitDateException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;

import visitor.RemovedItemsVisitor;
import visitor.ReportVisitor;
import model.DateTime;
import model.HomeInventory;
import model.Item;

/**
 * 
 * @author edwardekstrom
 * Singleton:  This class puts all items into one place for 
 * easy access throughout the whole program.
 */
public class ItemsManager {
	
	private static ItemsManager _instance = null;
	private List<Item> _allItemsList;
	private List<Item> _deletedItemsList;
	
	/**
	 * This method instantiates the instance of ItemsManager.
	 * It will only ever be called one time.
	 * @precondition none
	 * @postcondition there is a new ItemsManager
	 */
	private ItemsManager(){
		assert true;
		_allItemsList = new ArrayList<Item>();
		_deletedItemsList = new ArrayList<Item>();
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
	
	public List<Item> getAllItems(){
		return _allItemsList;
	}

	public Item getItem(String barcode){
		for(Item i : _allItemsList){
			String code = i.getBarcode().getBarcode();
			if(code.equals(barcode))
				return i;
		}
		return null;
	}	
	
	public void storeBarcodeList(){
		HomeInventory hi = Configuration.getInstance().getHomeInventory();
		hi.setStoreItemManagerList(_allItemsList);
		hi.setStoreDeleted(_deletedItemsList);
	}
	
	public void de_storeBarcodeList(){
		HomeInventory hi = Configuration.getInstance().getHomeInventory();
		_allItemsList = hi.getStoreItemManagerList();
		_deletedItemsList = hi.getStoreDeleted();
	}
	
	public void accept(ReportVisitor visitor){
		for (Item i : _allItemsList){
			i.accept(visitor);
		}
	}
	public void acceptRemovedItemsVisitor(RemovedItemsVisitor visitor){
		for (Item i : _deletedItemsList){
			i.accept(visitor);
		}
	}
	
	public void addToDeletedItems(Item deletedItem){
		_deletedItemsList.add(deletedItem);
	}
	
	
}
