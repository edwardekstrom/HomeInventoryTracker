package model;

import gui.inventory.ProductContainerData;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

import singletons.Configuration;

import com.sun.tools.internal.jxc.gen.config.Config;

public class HomeInventory extends ProductContainer implements Serializable{
	private List<StorageUnit> _storageUnits;
	private ProductContainerData _rootData;
	private List<Product> _storeProductManagerList;
	private List<Item> _storeItemManagerList;
	private List<String> _storeBarcodeManagerList;
	
	/**
	 * @precondition none
	 * @postcondition There is a new HomeInventory
	 */
	public HomeInventory(){
		_storageUnits = new ArrayList<StorageUnit>();
		_rootData = new ProductContainerData();
		_rootData.setName("root");
		_rootData.setTag(this);
		_storeBarcodeManagerList = new ArrayList<String>();
		_storeItemManagerList = new ArrayList<Item>();
		_storeProductManagerList = new ArrayList<Product>();
		
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
		_rootData.addChild(storageUnit.getTagData());
		return _storageUnits.add(storageUnit);
	}
	
	public ProductContainerData getRootData(){
		return _rootData;
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
		if(name.equals("")) return false;
			
		for(StorageUnit su: _storageUnits){
			if(su.getName().equals(name)) return false;
		}
		return true;
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
	
	public void removeStorageUnit(StorageUnit su){
		_storageUnits.remove(su);
	}

	public Product getProduct(String barcode){
		for(StorageUnit u :_storageUnits){
			Product p = u.getProduct(barcode);
			if(p != null) 
				return p;
		}
		return null;
	}

	public void recursiveRemoveProduct(Product toRemove){
		for(StorageUnit u :_storageUnits)
			u.recursiveRemoveProduct(toRemove);
	}

	/**
	 * @return the _storeItemManagerList
	 */
	public List<Item> getStoreItemManagerList() {
		return _storeItemManagerList;
	}

	/**
	 * @param _storeItemManagerList the _storeItemManagerList to set
	 */
	public void setStoreItemManagerList(List<Item> _storeItemManagerList) {
		this._storeItemManagerList = _storeItemManagerList;
	}

	/**
	 * @return the _storeProductManagerList
	 */
	public List<Product> getStoreProductManagerList() {
		return _storeProductManagerList;
	}

	/**
	 * @param _storeProductManagerList the _storeProductManagerList to set
	 */
	public void setStoreProductManagerList(List<Product> _storeProductManagerList) {
		this._storeProductManagerList = _storeProductManagerList;
	}

	/**
	 * @return the _storeBarcodeManagerList
	 */
	public List<String> getStoreBarcodeManagerList() {
		return _storeBarcodeManagerList;
	}

	/**
	 * @param _storeBarcodeManagerList the _storeBarcodeManagerList to set
	 */
	public void setStoreBarcodeManagerList(List<String> _storeBarcodeManagerList) {
		this._storeBarcodeManagerList = _storeBarcodeManagerList;
	}
}
