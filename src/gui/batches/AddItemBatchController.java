package gui.batches;

import facade.*;
import gui.common.*;
import gui.inventory.*;
import gui.item.ItemData;
import gui.product.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.GregorianCalendar;

import model.*;
import command.*;
import command.commands.*;



/**
 * Controller class for the add item batch view.
 */
public class AddItemBatchController extends Controller implements
		IAddItemBatchController {

	private StorageUnit _storageUnit;
	
	private ArrayList<ProductData> _products;
	private ArrayList<ItemData> _items;

	private ItemFacade _itemFacade;
	private CommandCenter _commandCenter;

	private boolean _updatingView;

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
		

		ItemFacade.getInstance().registerAddItemBatchController(this);
		_products =  new ArrayList<ProductData>();
		_items = new ArrayList<ItemData>();
		_updatingView = false;


		construct();
		
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
		this.setDefaultValues();
		getView().setUseScanner(true);
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
			if(!count.equals(""))
				Integer.parseInt(count);

		}catch(NumberFormatException nfe){
			getView().displayErrorMessage("Invalid Count: Reseting Count to 1");
			getView().setCount("1");
		}
	}

	/**
	 * This method is called when the "Product Barcode" field in the
	 * add item batch view is changed by the user.
	 */
	@Override
	public void barcodeChanged() {
		boolean addEnabled = this.enableDisableAddItem();

		if(getView().getUseScanner() && addEnabled){
			this.addItem();
		}
	}

	/**
	 * This method is called when the "Use Barcode Scanner" setting in the
	 * add item batch view is changed by the user.
	 */
	@Override
	public void useScannerChanged() {
	}

	/**
	 * This method is called when the selected product changes
	 * in the add item batch view.
	 */
	@Override
	public void selectedProductChanged() {
		if(!_updatingView)
			this.updateView();
	}

	/**
	 * This method is called when the user clicks the "Add Item" button
	 * in the add item batch view.
	 */
	@Override
	public void addItem() {
		
		String barcode = getView().getBarcode();
		String count = getView().getCount();


		Product modelProduct = ProductFacade.getInstance().getProduct(barcode);
		ProductData viewProduct = productInView(barcode);

		if(count.equals("0")){
			this.setDefaultValues();
			return;
		}

		// The product exists in neither the model nor the view
		if(modelProduct == null && viewProduct == null){
			getView().displayAddProductView();
		}
		// else if(modelProduct != null && viewProduct == null){
		// 	addProduct(current);
		// 	addCurrentItems(current);
		// }
		// else{
		// 	addCurrentItems(batchCurrent);
		// }
	}
 

	/**
	 * This method is called when the user clicks the "Redo" button
	 * in the add item batch view.
	 */
	@Override
	public void redo() {
		_commandCenter.redo();
	}

	/**
	 * This method is called when the user clicks the "Undo" button
	 * in the add item batch view.
	 */
	@Override
	public void undo() {
		_commandCenter.undo();
	}

	/**
	 * This method is called when the user clicks the "Done" button
	 * in the add item batch view.
	 */
	@Override
	public void done() {
		
		ArrayList<Item> items = new ArrayList<Item>();

		for (ItemData i: _items){
			items.add((Item)i.getTag());
		}

		getView().close();
		BarcodeLabelPrinter.printLabels(items);

	}

	// Public "US IMPLEMENTED" methods

	/**
	 *  Updates the view to reflect the current state
	 */
	public void updateView(){
		_updatingView = true;

		ProductData selected = getView().getSelectedProduct();
		
		loadProducts();
		getView().selectProduct(selected);

		loadItems();

		_updatingView = false;
	}

	// Private Methods


	/**
	 * Enables or disables the Add Item button as appropriate
	 * @return whether Add Item is enabled or disabled
	 */
	private boolean enableDisableAddItem(){
		boolean legalBarcode = false;
		String barcode = getView().getBarcode();


		if(!barcode.equals("")) 
			legalBarcode = true;
		getView().enableItemAction(legalBarcode);
		return legalBarcode;
	}

	/**
	 * Disable or enable both the do buttons
	 */
	private void enableDisableDos(){
		boolean enableUndo = _commandCenter.canUndo();
		boolean enableRedo = _commandCenter.canRedo();

		getView().enableUndo(enableUndo);
		getView().enableRedo(enableRedo);
	}

	/**
	 *	 Find the product from the View
	 *   @return the productData with the barcode if it's in _products else null
	 */
	private ProductData productInView(String barcode){
		for (ProductData pd: _products){
			if(pd.getBarcode().equals(barcode))
				return pd;
		}
		return null;
	}

	/**
	 *   Loads the correct Items into the view
	 */
	private void loadItems(){
		ProductData pd = getView().getSelectedProduct();
		Product product = null;
		ArrayList<ItemData> items;

		if(pd != null)
			product = (Product) pd.getTag();

		if(product != null){
			items = new ArrayList<ItemData>();
			for(ItemData i : _items){
				Item item = (Item)i.getTag();
				if(item.getProduct() == product)
					items.add(item.getTagData());
			}
		}
		ItemData[] ids = items.toArray(new ItemData[items.size()]);
		getView().setItems(ids);
	}

	/**
	 *   Loads all products currently being used in the view
	 */
	private void loadProducts(){
		ProductData[] products;
		products = _products.toArray(new ProductData[_products.size()]);
		getView().setProducts(products);
	}

	

	/**
	 * Sets the default values in all the fields
	 */ 
	private void setDefaultValues(){
		getView().setCount("1");
		getView().setEntryDate(new java.util.Date());
		getView().setBarcode("");
		this.enableDisableDos();
		this.enableDisableAddItem();
		getView().giveBarcodeFocus();
	}

	


	//_________________________________________________________________________________
	// THE LINE



	public void addProduct(Product p){
		ProductData pd = p.getTagData();
		if(!_products.contains(pd)){
			_products.add(p.getTagData());
			ProductData[] products = _products.toArray(new ProductData[_products.size()]);
			getView().setProducts(products);
		}
	}

	public void resetControls(){
		
		
	}









	
}

