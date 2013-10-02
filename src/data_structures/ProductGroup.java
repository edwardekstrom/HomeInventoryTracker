package data_structures;

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
	
	public ProductGroup(){
		_name = "DEFAULT NAME";
		_storageUnit = null;
		
		_products = new ArrayList<Product>();
		_items = new ArrayList<Item>();
		_productGroups = new ArrayList<ProductGroup>();
	}
	
	public ProductGroup(String name){
		_name = name;
		_storageUnit = null;
		
		_products = new ArrayList<Product>();
		_items = new ArrayList<Item>();
		_productGroups = new ArrayList<ProductGroup>();
	}
	public ProductContainer getContainer() {
		return _container;
	}
	public void setContainer(ProductContainer _container) {
		this._container = _container;
	}
	
	public UnitSize getThreeMonthSup() {
		return _threeMonthSup;
	}
	public void setThreeMonthSup(UnitSize threeMonthSurprise) {
		this._threeMonthSup = threeMonthSurprise;
	}


}
