package data_structures;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Capchu
 *
 */
public interface ProductContainer {

	/**
	 * @return the a List of products
	 */
	public List<Product> getProductList();
	
	/**
	 * @return the a Map of products
	 */
	public TreeMap<Product, String> getProductTree();
	
	/**
	 * @return the a List of items
	 */
	public List<Item> getItemList();
	
	/**
	 * @return the a Map of products
	 */
	public TreeMap<Item, String> getItemTree();
}
