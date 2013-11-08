/**
 * 
 */
package facade;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import model.Barcode;
import model.Date;
import model.DateTime;
import model.Item;
import model.Product;
import model.ProductContainer;
import singletons.Configuration;
import singletons.ItemsManager;
import gui.batches.AddItemBatchController;
import gui.inventory.InventoryController;
import hit_exceptions.NullExitDateException;

/**
 * @author Capchu
 *
 */
public class ItemFacade extends Observable{

	

	private static ItemFacade _instance = null;
	private Configuration _config;
	private ItemsManager _itemsManager;
	public InventoryController _inventoryController;

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
	

	public void registerInventoryController(InventoryController ic){
		_inventoryController = ic;
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
		
		// setChanged();
		// notifyObservers(this);
		_inventoryController.productContainerSelectionChanged();


		return itemToAdd;
		
	}
	
	public void removeItem(Item item){
		removeItemFromTree(item);
		removeItemFromManager(item);

		// setChanged();
		// notifyObservers(this);
		_inventoryController.productContainerSelectionChanged();
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
	private void removeItemFromTree(Item item){
		ProductContainer pc = item.getContainer();
		pc.removeItem(item);
	}
	/**
	 * removes the item from the manager
	 * @param toRemove
	 */
	private void removeItemFromManager(Item toRemove){
		try{
			toRemove.setExitTime(new DateTime());
			ItemsManager iMgr = ItemsManager.getInstance();
			iMgr.removeItem(toRemove);
			iMgr.addToDeletedItems(toRemove);
		}catch(NullExitDateException n){
			
		}
	}
	/**
	 * Moves the Item from start container to finish
	 * @param start
	 * @param finish
	 */
	public void moveItemInTree(Item item, ProductContainer destination ){
		removeItem(item);
		destination.addItem(item);

		_inventoryController.productContainerSelectionChanged();
		// setChanged();
		// notifyObservers(this)an;
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
