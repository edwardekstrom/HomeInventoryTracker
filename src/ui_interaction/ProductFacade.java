/**
 * 
 */
package ui_interaction;


import java.util.Observable;
import java.util.Observer;

import singletons.Configuration;
import data_structures.Product;
import data_structures.ProductContainer;
import data_structures.Date;
import data_structures.Barcode;

import gui.product.*;

/**
 * @author Capchu
 *
 */
public class ProductFacade extends Observable {
	@Override
	public synchronized void addObserver(Observer o) {
		// TODO Auto-generated method stub
		super.addObserver(o);
	}

	@Override
	public void notifyObservers() {
		// TODO Auto-generated method stub
		super.notifyObservers();
	}

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


	public void addProduct(Product p){
			
			addProductToManager(p);
			setChanged();
			notifyObservers(this);
	
	}



	public boolean canAddProduct(String shelfLife, String threeMonthSupply, 
			                          String amount, String unit,String desc){
		return Product.willBeValidProduct(shelfLife,threeMonthSupply,amount,unit,desc);
	}



	//PRIVATE METHODS
	/**
	 * add the Product to the manager
	 * @param toAdd
	 */
	private void addProductToManager(Product toAdd){
		
	}
	/**
	 * removes the Product from the tree
	 * @param toRemove
	 */
	private void removeProductFromTree(Product toRemove){
		
	}
	/**
	 * removes the Product from the manager
	 * @param toRemove
	 */
	private void removeProductFromManager(Product toRemove){
		
	}
	
}
