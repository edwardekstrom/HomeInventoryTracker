package gui.batches;

import gui.common.*;
import gui.inventory.*;
import gui.item.ItemData;
import gui.product.*;
import data_structures.*;
import ui_interaction.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.GregorianCalendar;


/**
 * Controller class for the add item batch view.
 */
public class AddItemBatchController extends Controller implements
		IAddItemBatchController {

	private StorageUnit _storageUnit;
	
	private ArrayList<ProductData> _products;
	private ArrayList<ItemData> _items;

	private ItemFacade _itemFacade;

	/**
	 * Constructor.
	 * 
	 * @param view Reference to the add item batch view.
	 * @param target Reference to the storage unit to which items are being added.
	 */
	public AddItemBatchController(IView view, ProductContainerData target) {
		super(view);
		_storageUnit = (StorageUnit)target.getTag();
		_itemFacade = ItemFacade.getInstance();
		construct();

		ItemFacade.getInstance().registerAddItemBatchController(this);
		_products =  new ArrayList<ProductData>();
		_items = new ArrayList<ItemData>();
		barcodeChanged();
		//loadValues();
		getView().setCount("1");
	}

	/**
	 * Returns a reference to the view for this controller.
	 */
	@Override
	protected IAddItemBatchView getView() {
		return (IAddItemBatchView) super.getView();
	}

	/**
	 * Loads data into the controller's view.
	 * 
	 *  {@pre None}
	 *  
	 *  {@post The controller has loaded data into its view}
	 */
	@Override
	protected void loadValues() {
		//loadProducts();
		//loadItems();
	}
	
	private void loadProducts(){
		ArrayList<ProductData> productsList = new ArrayList<ProductData>();
		
		for(Product product: _storageUnit.getProducts()){
			productsList.add(product.getTagData());
		}
		ProductData[] products = productsList.toArray(new ProductData[productsList.size()]);
		getView().setProducts(products);
	}
	
	private void loadItems(){
		ArrayList<ItemData> itemDatas = new ArrayList<ItemData>();
		
		if(getView().getSelectedProduct()!=null){
			Product product = (Product) getView().getSelectedProduct().getTag();
			
			for(Item item: _storageUnit.getItems()){
				if(item.getProduct() == product){
					itemDatas.add(item.getTagData());
				}
			}
			
			getView().setItems(itemDatas.toArray(new ItemData[itemDatas.size()]));
		}
	}

	/**
	 * Sets the enable/disable state of all components in the controller's view.
	 * A component should be enabled only if the user is currently
	 * allowed to interact with that component.
	 * 
	 * {@pre None}
	 * 
	 * {@post The enable/disable state of all components in the controller's view
	 * have been set appropriately.}
	 */
	@Override
	protected void enableComponents() {
		
	}

	/**
	 * This method is called when the "Entry Date" field in the
	 * add item batch view is changed by the user.
	 */
	@Override
	public void entryDateChanged() {
	}

	/**
	 * This method is called when the "Count" field in the
	 * add item batch view is changed by the user.
	 */
	@Override
	public void countChanged() {
		boolean validCount = false;
		String count = getView().getCount();
		try{
			if(!count.equals("")){
				Integer.parseInt(count);
			}
		}catch(NumberFormatException nfe){
			getView().displayErrorMessage("Invalid Count: Reseting Count to 1");
			//getView().selectProduct(null);
			getView().setCount("1");
		}
		
	}

	/**
	 * This method is called when the "Product Barcode" field in the
	 * add item batch view is changed by the user.
	 */
	@Override
	public void barcodeChanged() {
		boolean legalBarcode = false;
		String barcode = getView().getBarcode();
		if(!barcode.equals("")) legalBarcode = true;
		getView().enableItemAction(legalBarcode);
	}

	/**
	 * This method is called when the "Use Barcode Scanner" setting in the
	 * add item batch view is changed by the user.
	 */
	@Override
	public void useScannerChanged() {
		System.out.println("useScannerChanged");

	}

	/**
	 * This method is called when the selected product changes
	 * in the add item batch view.
	 */
	@Override
	public void selectedProductChanged() {
		loadItems();
	}

	/**
	 * This method is called when the user clicks the "Add Item" button
	 * in the add item batch view.
	 */
	@Override
	public void addItem() {
		
		String barcode = getView().getBarcode();

		Product current = _storageUnit.getProduct(barcode);
		Product batchCurrent = getProduct(barcode);

		if(current == null && batchCurrent == null){
			getView().displayAddProductView();

		}
		else if(current != null){
				addProduct(current);
				addCurrentItems(current);
		}

		
	}



	public  void addCurrentItems(Product current){



		Date entryDate = new Date(getView().getEntryDate()); 
		String count = getView().getCount();


		if(count.equals("")){
			getView().displayErrorMessage("Invalid Count: Reseting Count to 1");
			getView().setCount("1");
		}
		else{
				
			StorageUnit currUnit = _storageUnit;
	
			int numItems = Integer.parseInt(count);
	
			for (int i = 0; i < numItems; i++){
				Item item = _itemFacade.addItem(current, entryDate, _storageUnit);
				_items.add(item.getTagData());


			}

			displayItemsForProduct(current);
		}

	}


	public void displayItemsForProduct(Product p){
		ItemData[] items = getItemsFor(p);
		getView().setItems(items);

	}
	
	private ItemData[] getItemsFor(Product p){
		ArrayList<ItemData> items = new ArrayList<ItemData>();
		for(ItemData i: _items){
			Product product = ((Item)i.getTag()).getProduct();

			if(product == p)
				items.add(i);
		}

		ItemData[] itemsArray = items.toArray(new ItemData[items.size()]);
		return itemsArray;
	}



	/**
	 * This method is called when the user clicks the "Redo" button
	 * in the add item batch view.
	 */
	@Override
	public void redo() {
	}

	/**
	 * This method is called when the user clicks the "Undo" button
	 * in the add item batch view.
	 */
	@Override
	public void undo() {
	}

	/**
	 * This method is called when the user clicks the "Done" button
	 * in the add item batch view.
	 */
	@Override
	public void done() {
		
		getView().close();

	}

	public void addProduct(Product p){
//		_products.add(p.getTagData());
//		ProductData[] products = _products.toArray(new ProductData[_products.size()]);
//		loadValues();
		
		_products.add(p.getTagData());
		ProductData[] products = _products.toArray(new ProductData[_products.size()]);
		getView().setProducts(products);

	}

	public Product getProduct(String barcode){
		for (ProductData pd: _products){
			if(pd.getBarcode().equals(barcode))
				return (Product)pd.getTag();
		}
		return null;
	}


	
}

