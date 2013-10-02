package data_structures;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class HomeInventory implements Serializable{
	private List<StorageUnit> _storageUnits;
	
	/**
	 * @precondition none
	 * @postcondition There is a new HomeInventory
	 */
	public HomeInventory(){
		_storageUnits = new ArrayList<StorageUnit>();
	}
	
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
	
}
