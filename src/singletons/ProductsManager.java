package singletons;

import java.util.ArrayList;
import java.util.List;

import data_structures.Item;


/**
 * 
 * @author edwardekstrom
 * Singleton:  This class puts all products into one place for 
 * easy access throughout the whole program.
 */
public class ProductsManager {
	
	private static ProductsManager _instance = null;
	private List<Item> _allProductsList;
	
	
	/**
	 * This method instantiates the instance of ProductsManager.
	 * It will only ever be called one time.
	 */
	private ProductsManager(){
		_allProductsList = new ArrayList<Item>();
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
}
