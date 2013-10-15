/**
 * 
 */
package ui_interaction;

import gui.inventory.ProductContainerData;
import gui.storageunit.StorageUnitView;

import java.util.List;

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
public class StorageUnitFacade {
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
			addStorageUnitToTree(storageUnit);
			
			ProductContainerData pcData = new ProductContainerData();
			pcData.setName(toAdd);
			pcData.setTag(storageUnit);
			storageUnit.setTagData(pcData);
		}
	}
	
	public boolean canAddStorageUnit(String storageUnitName){
		HomeInventory hi = config.getHomeInventory();
		return hi.isValidHomeInventoryName(storageUnitName);
	}
	
	public void removeStorageUnit(StorageUnit toRemove){
		removeStorageUnitFromTree(toRemove);
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
