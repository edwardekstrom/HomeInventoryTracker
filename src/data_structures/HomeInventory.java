package data_structures;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class HomeInventory implements Serializable{
	private List<StorageUnit> _storageUnits;
	
	public HomeInventory(){
		_storageUnits = new ArrayList<StorageUnit>();
	}
	
	public boolean addStorageUnit(StorageUnit storageUnit){
		return _storageUnits.add(storageUnit);
	}
	
	public int getStorageUnitsCount(){
		return _storageUnits.size();
	}
	
}
