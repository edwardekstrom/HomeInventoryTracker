package singletons;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import visitor.ReportVisitor;
import model.HomeInventory;
import model.Item;
import model.Product;


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
	 * @precondition none
	 * @postcondition a blank _allProductsList
	 */
	private ProductsManager(){
		assert true;
		_allProductsList = new ArrayList<Product>();
	}
	
	/**
	 * This method returns the ProductsManger singleton
	 * @precondition none
	 * @postcondition returns the instance of ProductsManager
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
	 * @precondition none
	 * @postcondition none
	 * @param product check if this product can be added
	 * @return true if the product can be added, false otherwise.
	 */
	public boolean canAddProduct(Product product){
		return !_allProductsList.contains(product);
	}
	
	/**
	 * Adds the product to the list of all products.
	 * @precondition none
	 * @postcondition the list of all products has the new product
	 * @param product the product to be added
	 */
	public void addProduct(Product product){
		_allProductsList.add(product);
	}
	
	/**
	 * Removes the product from the list of all products.
	 * @precondition none
	 * @postcondition the product passed is not in the allProductsList
	 * @param product the product to be removed
	 */
	public void removeProduct(Product product){
		_allProductsList.remove(product);
	}
	
	/**
	 * True if the list contains the product,
	 * false otherwise.
	 * @precondition none
	 * @postcondition none
	 * @param product A product to check for
	 * @return True if the list contains the product, false otherwise.
	 */
	public boolean containsProduct(Product product){
		return _allProductsList.contains(product);
	}

	
	


	/**
	 * Returns an unmodifiable version of the allProductsList.
	 * @precondition none
	 * @postcondition there is a new unmodifiable version of the _allProductsList
	 * @return List unmodifiable all products list.
	 */
	public List<Product> getUnmodifiableList(){
		return Collections.unmodifiableList(_allProductsList);
	}
	
	public List<Product> getAllProducts(){
		return _allProductsList;
	}
	
	public void storeBarcodeList(){
		HomeInventory hi = Configuration.getInstance().getHomeInventory();
		hi.setStoreProductManagerList(_allProductsList);;
	}
	
	public void de_storeBarcodeList(){
		HomeInventory hi = Configuration.getInstance().getHomeInventory();
		_allProductsList = hi.getStoreProductManagerList();
	}
	
	public void accept(ReportVisitor visitor){
		for (Product p : _allProductsList){
			p.accept(visitor);
		}
	}
}
