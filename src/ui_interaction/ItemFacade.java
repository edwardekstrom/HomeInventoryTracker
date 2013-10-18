/**
 * 
 */
package ui_interaction;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import singletons.Configuration;
import singletons.ItemsManager;
import data_structures.Barcode;
import data_structures.Date;
import data_structures.Item;
import data_structures.Product;
import data_structures.ProductContainer;
import gui.batches.AddItemBatchController;

/**
 * @author Capchu
 *
 */
public class ItemFacade extends Observable{

	

	private static ItemFacade _instance = null;
	private Configuration _config;
	private ItemsManager _itemsManager;

	private AddItemBatchController _addItemBatchController;
	
	private ItemFacade(){
		_config = Configuration.getInstance();
		_itemsManager = ItemsManager.getInstance();
	}
	
	public static ItemFacade getInstance(){
		if (_instance == null){
			_instance = new ItemFacade();
		}
		return _instance;
	}
	
	/**
	 * Add the given item
	 * @param toAdd
	 */
	public Item addItem(Product product, Date entryDate, ProductContainer container){
		
		Barcode newBarcode = new Barcode();
		Item itemToAdd = new Item(product, newBarcode, entryDate, container);
		
		addItemToTree(itemToAdd);
		addItemToManager(itemToAdd);
		
		setChanged();
		notifyObservers(this);
		
		return itemToAdd;
		
	}
	
	public void removeItem(){
		
	}
	
	/**
	 * Add the given item to the tree
	 * @param toAdd
	 */
	private void addItemToTree(Item toAdd){
		toAdd.getContainer().addItem(toAdd);
	}
	/**
	 * add the Item to the manager
	 * @param toAdd
	 */
	private void addItemToManager(Item toAdd){
		_itemsManager.addItem(toAdd);
	}
	/**
	 * removes the item from the tree
	 * @param toRemove
	 */
	private void removeItemFromTree(Item toRemove){
		
	}
	/**
	 * removes the item from the manager
	 * @param toRemove
	 */
	private void removeItemFromManager(Item toRemove){
		
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
	
	/* (non-Javadoc)
	 * @see java.util.Observable#addObserver(java.util.Observer)
	 */
	@Override
	public synchronized void addObserver(Observer o) {
		// TODO Auto-generated method stub
		super.addObserver(o);
	}

	/* (non-Javadoc)
	 * @see java.util.Observable#notifyObservers()
	 */
	@Override
	public void notifyObservers() {
		// TODO Auto-generated method stub
		super.notifyObservers();
	}

	/* (non-Javadoc)
	 * @see java.util.Observable#notifyObservers(java.lang.Object)
	 */
	@Override
	public void notifyObservers(Object arg) {
		// TODO Auto-generated method stub
		super.notifyObservers(arg);
	}

	/* (non-Javadoc)
	 * @see java.util.Observable#setChanged()
	 */
	@Override
	protected synchronized void setChanged() {
		// TODO Auto-generated method stub
		super.setChanged();
	}
	
}
