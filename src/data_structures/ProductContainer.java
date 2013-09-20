package data_structures;

import java.util.List;
import java.util.Map;

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
	public Map<Product, String> getProductMap();
	
	/**
	 * @return the a List of items
	 */
	public List<Item> getItemList();
	
	/**
	 * @return the a Map of products
	 */
	public Map<Item, String> getItemMap();
}
