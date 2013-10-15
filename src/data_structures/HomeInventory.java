package data_structures;

import gui.inventory.ProductContainerData;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

import singletons.Configuration;

import com.sun.tools.internal.jxc.gen.config.Config;

public class HomeInventory implements Serializable{
	private List<StorageUnit> _storageUnits;
	private ProductContainerData _rootData;
	
	/**
	 * @precondition none
	 * @postcondition There is a new HomeInventory
	 */
	public HomeInventory(){
		_storageUnits = new ArrayList<StorageUnit>();
		_rootData = new ProductContainerData();
		_rootData.setTag(this);
		
	}
	
//	public void setConfigurationsHomeInventory(){
//		Configuration config = Configuration.getInstance();
//	}
	/**
	 * @precondition none
	 * @postcondition the HomeInventory has the new StorageUnit in it
	 * @param storageUnit
	 * @return true if the storage unit was added, false otherwise
	 */
	public boolean addStorageUnit(StorageUnit storageUnit){
		return _storageUnits.add(storageUnit);
	}
	
	/**
	 * @precondition none
	 * @postcondition none
	 * @return the count of all StorageUnits in the HomeInventory
	 */
	public int getStorageUnitsCount(){
		return _storageUnits.size();
	}
	
	public boolean isValidHomeInventoryName(String name){
		return !_storageUnits.contains(name);
	}
	
	public List<StorageUnit> getStorageUnits(){
		return Collections.unmodifiableList(this._storageUnits);
	}
	
	public List<ProductContainerData> getIsoRoots(){
		List<ProductContainerData> pcdList = new ArrayList<ProductContainerData>();
		for(ProductContainer c: this._storageUnits){
			pcdList.add(c.getTagData());
		}
		return pcdList;
	}
}
