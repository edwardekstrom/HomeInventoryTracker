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
import gui.inventory.ProductContainerData;
import gui.product.*;
import gui.batches.AddItemBatchController;
import hit_exceptions.InvalidAmountException;
import hit_exceptions.InvalidShelfLifeException;
import hit_exceptions.InvalidThreeMonthSupplyException;
import hit_exceptions.InvalidUnitException;
import singletons.ProductsManager;

/**
 * @author Capchu
 *
 */
public class ProductFacade extends Observable {


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

		AddItemBatchController aibc = ItemFacade.getInstance().getAddItemBatchController();
		aibc.addProduct(p);
		aibc.addCurrentItems(p);
		aibc.reset();
	
	}

	public boolean canAddProduct(String shelfLife, String threeMonthSupply, 
			                          String amount, String unit,String desc){
		return Product.willBeValidProduct(shelfLife,threeMonthSupply,amount,unit,desc);
	}

	public void editProduct(Product p, String desc, String sizeValue, String sizeUnit, String shelfLife, String supply){
		
		try {
			p.setDescription(desc);
			p.setSizeAmount(sizeValue);
			p.setSizeUnit(sizeUnit);
			p.setShelfLife(shelfLife);
			p.setThreeMonthSupply(supply);
			
			ProductData pd = p.getTagData();
			
			pd.setDescription(desc);
			pd.setCount(sizeValue);
			pd.setSize(sizeUnit);
			pd.setSupply(supply);
			pd.setShelfLife(shelfLife);
			
		} catch (InvalidAmountException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidUnitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidShelfLifeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidThreeMonthSupplyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void romoveProduct(Product toRemove, ProductContainer removeFrom){
		removeProductFromManager(toRemove);
		removeProductFromTree(toRemove, removeFrom);
		
		//toRemove.getTagData();
		
		setChanged();
		notifyObservers(this);
	}

	//PRIVATE METHODS
	/**
	 * add the Product to the manager
	 * @param toAdd
	 */
	private void addProductToManager(Product toAdd){
		ProductsManager.getInstance().addProduct(toAdd);
	}
	/**
	 * removes the Product from the tree
	 * @param toRemove
	 * @param removeFrom 
	 */
	private void removeProductFromTree(Product toRemove, ProductContainer removeFrom){
		removeFrom.removeProduct(toRemove);
	}
	/**
	 * removes the Product from the manager
	 * @param toRemove
	 */
	private void removeProductFromManager(Product toRemove){
		ProductsManager.getInstance().removeProduct(toRemove);
	}
	
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
}
