package model;

import gui.common.Tagable;
import gui.inventory.ProductContainerData;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

import visitor.ReportVisitor;

import java.util.Collections;

/**
 * @author nRitchie
 * An abstract class for StorageUnits and ProductGroups. These objects can "contain"
 * Products and Items, and are referred to generically as "product containers".
 */
public abstract class ProductContainer implements Serializable{
	
	protected String _name;
	protected List<Product> _products;
	protected List<Item> _items;
	protected List<ProductGroup> _productGroups;
	
	protected ProductContainerData _tagData = new ProductContainerData();
	protected StorageUnit _storageUnit;

	public boolean _changed = true;



	public boolean changed(){
		return _changed;
	}
	public void setChanged(boolean t){
		_changed = t;
	}


	public ProductContainer getChangedPC(){
		if (this._changed)
			return this;
		for(int i = 0; i < _productGroups.size(); i++){
			ProductContainer current = _productGroups.get(i);
			if(current.changed()){
				return current;
			}
		}
		return null;
	}

	/**
	 * finds the productGroup with the item's product in it
	 * adds an Item to the list of items in the productGroup
	 * 
	 * if there is none, it will add the product and item to itself
	 * 
	 * @param item the item to be added
	 * @precondition none
	 * @postcondition the item will be added into the list _items
	 */
	public void addItem(Item item){

		// If it contains the product
		if(_products.contains(item.getProduct())){
			try{
				item.setContainer(this);

			}catch(Exception e){System.out.println("could not set container");}
			_items.add(item);

		} 
		// Traverses itself and below in tree
		else if(this.containsProduct(item.getProduct())){

			this.productGroupWithProduct(item.getProduct()).addItem(item);

		}
		// if the product is not already in the tree
		else{
			this.addProduct(item.getProduct());
			_items.add(item);
			try{
				item.setContainer(this);

			}catch(Exception e){System.out.println("could not set container");}
		}
	}
	
	
	/**
	 * Checks if a product is in this object or in any of it's productGroup subtrees
	 * 
	 * @param product
	 * @return if product is in the subtree
	 * 
	 * @precondition none
	 * @postcondition none
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
	 * Checks if a product with barcode is in this object or in any of it's productGroup subtrees
	 * 
	 * @param barcode 
	 * @return if product is in the subtree
	 * 
	 * @precondition none
	 * @postcondition none
	 */
	public boolean containsProduct(String barcode) {
		for(Product p:_products){
			if(p.getBarcode().getBarcode().equals(barcode)){
				return true;
			}
		}
		
		for(int i = 0; i < _productGroups.size(); i++){
			if(_productGroups.get(i).containsProduct(barcode)){
				return true;
			}
		}
		
		return false;
	}


	/**
	 * Get the product by barcode if it exists
	 * @param barcode (string) - the barcode we want for the product
	 * @return Product or null if not found
	 */
	public Product getProduct(String barcode){
		for(Product p:_products){
			if(p.getBarcode().getBarcode().equals(barcode))
				return p;
		}
		for(int i = 0; i < _productGroups.size(); i++){
			Product p = _productGroups.get(i).getProduct(barcode);
			if(p != null)
				return p;
		}

		return null;
	}



	/**
	 * finds the productGroup with the product in it
	 * and returns it
	 * 
	 * @param product the product that is being searched for
	 * 
	 * @return the productGroup that the product is in
	 * 
	 * @precondition none
	 * @postcondition none
	 */
	protected ProductContainer productGroupWithProduct(Product product) {
		if(_products.contains(product)){
			return this;
		}else
		{
			for(int i = 0; i < _productGroups.size(); i++){
				ProductContainer containerWithProduct = 
						_productGroups.get(i).productGroupWithProduct(product);	
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
	 * @precondition none
	 * @postcondition removes item from items list,
	 * 			 if it was the last item of a product it will remove the product
	 */
	public void removeItem(Item item){

		_items.remove(item);
		
	}
	
	/**
	 * @param item that will be moved
	 * @param productContainer that it will be moved to
	 * @precondition none
	 * @postcondition will move item out of this container and into 
	 * 	the other. If the targetContainer has the item's product somewhere
	 * else in it's storageUnit, the it moves all of those items into the 
	 * targetContainer
	 */
	public void moveItem(Item item, ProductContainer productContainer){
		this.moveProduct(item.getProduct(), productContainer);
		
		if(_items.contains(item)){
			this.removeItem(item);
			productContainer.addItem(item);
		}

	}
	

	
/****************Product functions************************/
	
	/**
	 * 
	 * @param product the product that will be added
	 * @precondition none
	 * @postcondition product will be in _products
	 */
	private void addProduct(Product product){
		_products.add(product);
		
		
	}
	
	/**
	 * 
	 * @param product that will be removed
	 * @precondition none
	 * @postcondition product will no longer be in products
	 */
	public void removeProduct(Product product){
//		Boolean productIsEmpty = true;
//		for(int i = 0; i < _items.size(); i++){
//			if(_items.get(i).getProduct() == product){
//				productIsEmpty = false;
//			}
//		}
//		if(productIsEmpty){
			_products.remove(product);
//		}
	}

	/**
	 *	Recursively removes the product from self and all children
	 *  @param product to be removed
	 */
	public void recursiveRemoveProduct(Product product){
		_products.remove(product);
		for(ProductContainer pc: _productGroups)
			pc.recursiveRemoveProduct(product);

	}
	
	/**
	 * 
	 * @param product that will be moved
	 * @param targetProductContainer destination for product
	 * 
	 * @precondition none
	 * @postcondition product will be moved to target and all items of that product that 
	 * were in the target's storageUnit will be moved to target aswell
	 */
	public void moveProduct(Product product, ProductContainer targetProductContainer) {
		/*
		 * if Product is already in a Product Container in
		 *	the Target Storage Unit
		 *		Move the Product and all associated Items from
		 *	their old Product Container to the Target Product Container
		 */
		if(targetProductContainer._storageUnit.containsProduct(product)){
			ProductContainer containerWithProduct = 
					targetProductContainer._storageUnit.productGroupWithProduct(product);
			
			containerWithProduct.removeProduct(product);
			targetProductContainer.addProduct(product);
			
//			for (Item itemToTransfer: containerWithProduct.getItems()){
			int size = containerWithProduct.getItems().size();
			for (int i = 0; i < containerWithProduct.getItems().size(); i++) {
				Item itemToTransfer = containerWithProduct.getItems().get(i);
//				System.out.println(containerWithProduct.getName()+ i +" "+size + "containerWithProduct.getItems().size(): "+ containerWithProduct.getItems().size());
				if(itemToTransfer.getProduct() == product){
					targetProductContainer.addItem(itemToTransfer);
					containerWithProduct.removeItem(itemToTransfer);
					i--;
				}

			}
			System.out.println(containerWithProduct.getName()+ "AFTER" +" "+size);

		}else{
			targetProductContainer.addProduct(product);
		}
		

	}
	
/*/**************Product Groups****************************/
	/**
	 * 
	 * @param productGroup the new group to be added
	 * @precondition none
	 * @postcondition productGroup will be in _productGroups
	 */
	public void addProductGroup(ProductGroup productGroup){
		_productGroups.add(productGroup);
		productGroup._storageUnit = _storageUnit;
	}
	
	public void removeProductGroup(ProductGroup productGroup){
		_productGroups.remove(productGroup);
		ProductContainerData parentPCData = productGroup.getContainer().getTagData();
		parentPCData.removeChildPCData(productGroup.getTagData());
	}

	public boolean canAddProductGroupWithName(String name) {
		if(name.equals("")) return false;
		for(ProductGroup pg: _productGroups){
			if(pg.getName().equals(name)) return false;
		}

		return true;
	}

/*/**************Validation****************************/
	public boolean canBeDeleted(){
		
		if(_items.size() != 0){
			return false;
		}
		
		for(ProductGroup pg : _productGroups){
			if (!pg.canBeDeleted()){
				return false;
			}
		}
		
		return true;
	}
	

/*/**************Getter/Setters****************************/
	
	/**
	 * sets its tag.
	 * @param pcData
	 */
	public void setTagData(ProductContainerData pcData) {
		_tagData = pcData;
	}

	public ProductContainerData getTagData(){
		return this._tagData;
	}
	
	/**
	 * @return the name
	 * @precondition none
	 * @postcondition none
	 */
	public String getName() {
		return _name;
	}
	/**
	 * @param name the name to set
	 *  @precondition none
	 * @postcondition none
	 */
	public void setName(String name) {
		_tagData.setName(name);
		this._name = name;
	}
	
	/**
	 * @return the products
	 *  @precondition none
	 * @postcondition none
	 */
	public List<Product> getProducts() {
		return _products;
	}

	
	/**
	 * @return the items
	 *  @precondition none
	 * @postcondition none
	 */
	public List<Item> getItems() {
		return _items;
	}

	public List<Item> getAllItems(){
		List<Item> items = new ArrayList<Item>();
		items.addAll(_items);
		for(ProductContainer pc: _productGroups)
			items.addAll(pc.getAllItems());
		return items;
	}
	
	/**
	 * @return the productGroups
	 *  @precondition none
	 * @postcondition none
	 */
	public List<ProductGroup> getProductGroups() {
		return _productGroups;
	}

	public StorageUnit getStorageUnit(){
		return this._storageUnit;
	}
	
	
/*/**************Visitor stuff****************************/
	
	public void accept(ReportVisitor visitor){
		visitor.visit(this);

		for(ProductGroup pg : _productGroups){
			pg.accept(visitor);
		}
		for(Product p : _products){
			p.accept(visitor);
		}
		for(Item i : _items){
			i.accept(visitor);
		}
	}
	
	
}
