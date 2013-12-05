/**
 * 
 */
package facade;

import gui.inventory.InventoryController;
import gui.inventory.ProductContainerData;
import gui.storageunit.StorageUnitView;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import persistance.Persistor;
import model.HomeInventory;
import model.ProductContainer;
import model.StorageUnit;

import com.sun.org.apache.bcel.internal.generic.StoreInstruction;

import singletons.Configuration;
import singletons.ItemsManager;

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
	
	public void addStorageUnit(StorageUnit toAdd){
		HomeInventory hi = config.getHomeInventory();
			
			
			ProductContainerData pcData = new ProductContainerData();
			pcData.setName(toAdd.getName());
			pcData.setTag(toAdd);
			toAdd.setTagData(pcData);
			
			addStorageUnitToTree(toAdd);
			setChanged();
			notifyObservers(this);
		
	}
	
	public void editStorageUnit(StorageUnit storageUnit, String newName){
		storageUnit.setName(newName);
		
		Persistor persistor = Configuration.getInstance().getPersistor();
		persistor.updateProductContainer(storageUnit);
		
		
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
		
		Persistor persistor = Configuration.getInstance().getPersistor();
		persistor.deleteProductContainer(toRemove);
		
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



	public List<ProductContainerData> getIsoRoots(){
		
		return this.config.getHomeInventory().getIsoRoots();
	}

	public ProductContainer getChangedPC(){
		List<StorageUnit> suList = Configuration.getInstance().getHomeInventory().getStorageUnits();

		ProductContainer changed;
		for( StorageUnit s: suList){
			changed = s.getChangedPC();
			if( changed != null)
				return changed;
		}
		return null;

	}
}
