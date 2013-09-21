package data_structures;

import java.util.List;
import java.util.TreeMap;

/**
 * @author Capchu
 *An interface for StorageUnits and ProductGroups. These objects can “contain”
  Products and Items, and are referred to generically as “product containers”.
 */
public interface ProductContainer {

	/**
	 * @return the An Unmodifiable List of products
	 */
	public List<Product> getProductList();
	
	/**
	 * @return the An Unmodifiable Map of products
	 */
	public TreeMap<Product, String> getProductTree();
	
	/**
	 * @return the An Unmodifiable List of items
	 */
	public List<Item> getItemList();
	
	/**
	 * @return the An Unmodifiable Map of products
	 */
	public TreeMap<Item, String> getItemTree();
}
