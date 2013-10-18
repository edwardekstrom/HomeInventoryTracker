/**
 * 
 */
package ui_interaction;

import java.util.List;

import singletons.Configuration;
import data_structures.Item;
import data_structures.ProductContainer;

import gui.batches.AddItemBatchController;

/**
 * @author Capchu
 *
 */
public class ItemFacade {

	private static ItemFacade _instance = null;
	private Configuration config;

	private AddItemBatchController _addItemBatchController;
	
	private ItemFacade(){
		config = Configuration.getInstance();
	}
	
	public static ItemFacade getInstance(){
		if (_instance == null){
			_instance = new ItemFacade();
		}
		return _instance;
	}
	
	/**
	 * Add the given item to the tree
	 * @param toAdd
	 */
	public void addItemToTree(Item toAdd){
		
	}
	/**
	 * add the Item to the manager
	 * @param toAdd
	 */
	public void addItemToManager(Item toAdd){
		
	}
	/**
	 * removes the item from the tree
	 * @param toRemove
	 */
	public void removeItemFromTree(Item toRemove){
		
	}
	/**
	 * removes the item from the manager
	 * @param toRemove
	 */
	public void removeItemFromManager(Item toRemove){
		
	}
	/**
	 * Moves the Item from start container to finish
	 * @param start
	 * @param finish
	 */
	public void moveItemInTree(ProductContainer start, ProductContainer finish){
		
	}
	
	/**
	 * Prints the barcodes for the given items
	 * @param toPrint
	 */
	public void printBatchBarcodes(List<Item> toPrint){
		
	}


	public void registerAddItemBatchController(AddItemBatchController aibc){
		_addItemBatchController =aibc;
	}

	public AddItemBatchController getAddItemBatchController(){
		return _addItemBatchController;
	}
	
}
