/**
 * 
 */
package ui_interaction;

import java.util.List;

import singletons.Configuration;

import data_structures.ProductGroup;
import data_structures.ProductContainer;

/**
 * @author Capchu
 *
 */
public class ProductGroupFacade {

	private static ProductGroupFacade _instance = null;
	private Configuration config;
	
	private ProductGroupFacade(){
		config = Configuration.getInstance();
	}
	
	public static ProductGroupFacade getInstance(){
		if (_instance == null){
			_instance = new ProductGroupFacade();
		}
		return _instance;
	}
	
	/**
	 * Add the given ProductGroup to the tree
	 * @param toAdd
	 */
	public void addProductGroupToTree(ProductGroup toAdd){
		
	}
	/**
	 * add the ProductGroup to the manager
	 * @param toAdd
	 */
	public void addProductGroupToManager(ProductGroup toAdd){
		
	}
	/**
	 * removes the ProductGroup from the tree
	 * @param toRemove
	 */
	public void removeProductGroupFromTree(ProductGroup toRemove){
		
	}
	/**
	 * removes the ProductGroup from the manager
	 * @param toRemove
	 */
	public void removeProductGroupFromManager(ProductGroup toRemove){
		
	}
	/**
	 * Moves the ProductGroup from start container to finish
	 * @param start
	 * @param finish
	 */
	public void moveProductGroupInTree(ProductContainer start, ProductContainer finish){
		
	}
}
