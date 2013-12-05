package model;

import gui.inventory.ProductContainerData;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**\
 * @author nRitchie
 * An implementation of ProductContainer for storage spaces
 */
public class StorageUnit extends ProductContainer implements Serializable, Comparable{
	public StorageUnit(){
		_name = "DEFAULT NAME";
		_storageUnit = this;
		
		_products = new ArrayList<Product>();
		_items = new ArrayList<Item>();
		_productGroups = new ArrayList<ProductGroup>();
		
	}
	
	public StorageUnit(String name){
		_name = name;
		_storageUnit = this;
		
		_products = new ArrayList<Product>();
		_items = new ArrayList<Item>();
		_productGroups = new ArrayList<ProductGroup>();
		
	}

	@Override
	public int compareTo(Object o) {
		StorageUnit su = (StorageUnit)o;
		return _name.compareTo(su.getName());
	}






}
