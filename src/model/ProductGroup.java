package model;

import java.io.Serializable;
import java.util.ArrayList;


/**\
 * @author nRitchie
 * Composite: An implementation of ProductContainer 
 * to organize products into groups
 */
public class ProductGroup extends ProductContainer implements Serializable{
	private ProductContainer _container;
	private UnitSize _threeMonthSup;
	
	/**
	 * Constructor
	 *  @precondition none
	 * @postcondition object will be created with all defaults
	 */
	public ProductGroup(){
		_name = "DEFAULT NAME";
		_storageUnit = null;
		
		_products = new ArrayList<Product>();
		_items = new ArrayList<Item>();
		_productGroups = new ArrayList<ProductGroup>();
	}
	
	/**
	 * 
	 * @param name that will be set to name
	 * @precondition none
	 * @postcondition object will be created with all defaults 
	 */
	public ProductGroup(String name){
		_name = name;
		_storageUnit = null;
		
		_products = new ArrayList<Product>();
		_items = new ArrayList<Item>();
		_productGroups = new ArrayList<ProductGroup>();
	}

	/**
	 * @return the _container
	 * @precondition none
	 * @postcondition none
	 */
	public ProductContainer getContainer() {
		return _container;
	}

	/**
	 * @param _container the _container to set
	 * @precondition none
	 * @postcondition none
	 */
	public void setContainer(ProductContainer _container) {
		this._container = _container;
	}

	/**
	 * @return the _threeMonthSup
	 * @precondition none
	 * @postcondition none
	 */
	public UnitSize getThreeMonthSup() {
		return _threeMonthSup;
	}

	/**
	 * @param _threeMonthSup the _threeMonthSup to set
	 * @precondition none
	 * @postcondition none
	 */
	public void setThreeMonthSup(UnitSize _threeMonthSup) {
		this._threeMonthSup = _threeMonthSup;
	}
	

	
}
