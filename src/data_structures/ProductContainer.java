package data_structures;

import java.util.List;
import java.util.TreeMap;

/**
 * @author nRitchie
 *An abstract class for StorageUnits and ProductGroups. These objects can "contain"
  Products and Items, and are referred to generically as "product containers".
 */
public abstract class ProductContainer {
	
	private String _name;
	private List<Product> _products;
	private List<Item> _items;
	private List<ProductGroup> _productGroups;
	
	/**
	 * finds the productGroup with the item's product in it
	 * adds an Item to the list of items in the productGroup
	 * 
	 * if there is none, it will add the product and item to itself
	 * 
	 * @param item the item to be added
	 */
	public void addItem(Item item){
		if(_products.contains(item.getProduct())){
			_items.add(item);
		}else
		if(this.containsProduct(item.getProduct())){

				this.productGroupWithProduct(item.getProduct()).addItem(item);

		}else{
			this.addProduct(item.getProduct());
			_items.add(item);
		}
	}
	
	/**
	 * Checks if a product is in this object or in any of it's productGroup subtrees
	 * 
	 * @param product
	 * @return if product is in the subtree
	 */
	public boolean containsProduct(Product product) {
			if(_products.contains(product)){
				return true;
			}else
			{
				for(int i = 0; i < _productGroups.size(); i++){
					if(_productGroups.get(i).containsProduct(product)){
						return true;
					}
				}
			}
			return false;
	}

	/**
	 * finds the productGroup with the product in it
	 * and returns it
	 * 
	 * @param product the product that is being searched for
	 * 
	 * @return the productGroup that the product is in
	 */
	protected ProductContainer productGroupWithProduct(Product product) {
		if(_products.contains(product)){
			return this;
		}else
		{
			for(int i = 0; i < _productGroups.size(); i++){
				ProductContainer containerWithProduct = _productGroups.get(i).productGroupWithProduct(product);	
				if(containerWithProduct != null){	
					return containerWithProduct;	
				}
			}
		}
		return null;
	}

	/**
	 * removes and item from the container 
	 * if it is not in this, but is in a subtree it will remove it from there
	 * 
	 * @param item the item that will be removed
	 * 
	 */
	public void removeItem(Item item){
		if(_items.contains(item)){
			_items.remove(item);
		}else{

				this.productGroupWithProduct(item.getProduct()).removeItem(item);

		}
		_items.remove(item);
	}
	
	/**
	 * 
	 */
	public void moveItem(Item item, StorageUnit storageUnit){
		storageUnit.addItem(item);
		this.removeItem(item);
	}
	
	/**
	 * 
	 */
	public void moveItem(Item item, ProductGroup productGroup){
		/* TODO If the ItemÕs Product is already in a Product Container in
			the Target Storage Unit
				Move the Product and all associated Items from
			their old Product Container to the Target Product Container
		*/
		StorageUnit targetStorageUnit = productGroup.getStorageUnit();
		if(targetStorageUnit.containsProduct(item.getProduct())){
			targetStorageUnit.moveProduct(item.getProduct(), productGroup);
		}
		
		productGroup.addItem(item);
		this.removeItem(item);
	}
	
/****************Product functions************************/
	
	public void addProduct(Product product){
//		if(this.containsProduct(product)){
//			moveProduct(product,this);
//		}
		_products.add(product);
	}
	
	public void removeProduct(Product product){

		_products.remove(product);
		
	}
public abstract void moveProduct(Product product, ProductContainer productContainer);

/***************Getter/Setters****************************/
	
	/**
	 * @return the name
	 */
	public String getName() {
		return _name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this._name = name;
	}
	
	/**
	 * @return the products
	 */
	public List<Product> getProducts() {
		return _products;
	}
	/**
	 * @param products the products to set
	 */
	public void setProducts(List<Product> products) {
		this._products = products;
	}
	
	/**
	 * @return the items
	 */
	public List<Item> getItems() {
		return _items;
	}
	/**
	 * @param items the items to set
	 */
	public void setItems(List<Item> items) {
		this._items = items;
	}
	
	/**
	 * @return the productGroups
	 */
	public List<ProductGroup> getProductGroups() {
		return _productGroups;
	}
	/**
	 * @param productGroups the productGroups to set
	 */
	public void setProductGroups(List<ProductGroup> productGroups) {
		this._productGroups = productGroups;
	}
	
}
