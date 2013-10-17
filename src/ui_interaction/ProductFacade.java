/**
 * 
 */
package ui_interaction;

import singletons.Configuration;
import data_structures.Product;
import data_structures.ProductContainer;

/**
 * @author Capchu
 *
 */
public class ProductFacade {
	
	private static ProductFacade _instance = null;
	private Configuration config;
	
	private ProductFacade(){
		config = Configuration.getInstance();
	}
	
	public static ProductFacade getInstance(){
		if (_instance == null){
			_instance = new ProductFacade();
		}
		return _instance;
	}

	/**
	 * Add the given Product to the tree
	 * @param toAdd
	 */
	public void addProductToTree(Product toAdd){
		
	}
	/**
	 * add the Product to the manager
	 * @param toAdd
	 */
	public void addProductToManager(Product toAdd){
		
	}
	/**
	 * removes the Product from the tree
	 * @param toRemove
	 */
	public void removeProductFromTree(Product toRemove){
		
	}
	/**
	 * removes the Product from the manager
	 * @param toRemove
	 */
	public void removeProductFromManager(Product toRemove){
		
	}
	
}
