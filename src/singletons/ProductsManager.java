package singletons;

import java.util.ArrayList;
import java.util.List;

import data_structures.Item;
import data_structures.Product;


/**
 * 
 * @author edwardekstrom
 * Singleton:  This class puts all products into one place for 
 * easy access throughout the whole program.
 */
public class ProductsManager {
	
	private static ProductsManager _instance = null;
	private List<Product> _allProductsList;
	
	
	/**
	 * This method instantiates the instance of ProductsManager.
	 * It will only ever be called one time.
	 */
	private ProductsManager(){
		_allProductsList = new ArrayList<Product>();
	}
	
	/**
	 * This method returns the ProductsManger singleton
	 * @return ProductsManager, the only instance of it
	 */
	public static ProductsManager getInstance(){
		if (_instance == null){
			_instance = new ProductsManager();
		}
		return _instance;
	}
	
	/**
	 * Returns true if the product can be added,
	 * false otherwise
	 * @param product check if this product can be added
	 * @return true if the product can be added, false otherwise.
	 */
	public boolean canAddProduct(Product product){
		return true;
	}
	
	/**
	 * Adds the product to the list of all products.
	 * @param product the product to be added
	 */
	public void addProduct(Product product){
		_allProductsList.add(product);
	}
	
	/**
	 * Removes the product from the list of all products.
	 * @param product the product to be removed
	 */
	public void removeProduct(Product product){
		_allProductsList.remove(product);
	}
}
