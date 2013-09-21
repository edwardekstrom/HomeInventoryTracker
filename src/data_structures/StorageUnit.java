package data_structures;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**\
 * 
 * 
 * @author Capchu
 *An interface for StorageUnits and ProductGroups. These objects can “contain”
  Products and Items, and are referred to generically as “product containers”.
 */
public class StorageUnit implements ProductContainer{

	/**
	 * @return A Unmodifiable List of Products this StorageUnit contains.
	 */
	@Override
	public List<Product> getProductList() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @return A Unmodifiable Tree of Products this StorageUnit contains.
	 */
	@Override
	public TreeMap<Product, String> getProductTree() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @return A Unmodifiable List of Items this StorageUnit contains.
	 */
	@Override
	public List<Item> getItemList() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @return A Unmodifiable Map of Items this StorageUnit contains.
	 */
	@Override
	public TreeMap<Item, String> getItemTree() {
		// TODO Auto-generated method stub
		return null;
	}

}
