/**
 * 
 */
package ui_interaction;

import gui.inventory.InventoryController;
import gui.inventory.ProductContainerData;
import gui.storageunit.StorageUnitView;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import com.sun.org.apache.bcel.internal.generic.StoreInstruction;

import singletons.Configuration;
import singletons.ItemsManager;
import data_structures.HomeInventory;
import data_structures.StorageUnit;
import data_structures.ProductContainer;

/**
 * @author Capchu
 *
 */
public class StorageUnitFacade extends Observable {
	@Override
	public synchronized void addObserver(Observer o) {
		// TODO Auto-generated method stub
		super.addObserver(o);
	}

	@Override
	public void notifyObservers() {
		// TODO Auto-generated method stub
		super.notifyObservers();
	}

	private static StorageUnitFacade _instance = null;
	private Configuration config;
	
	private StorageUnitFacade(){
		config = Configuration.getInstance();
	}
	
	public static StorageUnitFacade getInstance(){
		if (_instance == null){
			_instance = new StorageUnitFacade();
		}
		return _instance;
	}
	
	public void addStorageUnit(String toAdd){
		HomeInventory hi = config.getHomeInventory();
		
		if(hi.isValidHomeInventoryName(toAdd)){
			StorageUnit storageUnit = new StorageUnit(toAdd);
			
			
			ProductContainerData pcData = new ProductContainerData();
			pcData.setName(toAdd);
			pcData.setTag(storageUnit);
			storageUnit.setTagData(pcData);
			
			addStorageUnitToTree(storageUnit);
			setChanged();
			notifyObservers(this);
		}
	}
	
	public void editStorageUnit(StorageUnit storageUnit, String newName){
		storageUnit.setName(newName);
		setChanged();
		notifyObservers(this);
	}
	
	public ProductContainerData getRootPCData(){
		return config.getHomeInventory().getRootData();
	}
	
	
	public boolean canAddStorageUnit(String storageUnitName){
		HomeInventory hi = config.getHomeInventory();
		return hi.isValidHomeInventoryName(storageUnitName);
	}
	
	public void removeStorageUnit(StorageUnit toRemove){
		ProductContainerData pcData = toRemove.getTagData();
		ProductContainerData root = Configuration.getInstance().getHomeInventory().getRootData();
		root.removeChildPCData(pcData);
		
		removeStorageUnitFromTree(toRemove);
		setChanged();
		notifyObservers(this);
		
		config.getHomeInventory().removeStorageUnit(toRemove);
	}
	
	/**
	 * Add the given StorageUnit to the tree
	 * @param toAdd
	 */
	private void addStorageUnitToTree(StorageUnit toAdd){
		config.getHomeInventory().addStorageUnit(toAdd);
	}

	/**
	 * removes the StorageUnit from the tree
	 * @param toRemove
	 */
	private void removeStorageUnitFromTree(StorageUnit toRemove){
		
	}

	public List<ProductContainerData> getIsoRoots(){
		
		return this.config.getHomeInventory().getIsoRoots();
	}
}
