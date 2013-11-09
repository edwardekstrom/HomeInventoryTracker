package gui.batches;

import facade.*;
import gui.common.*;
import gui.inventory.*;
import gui.item.ItemData;
import gui.product.*;

import java.lang.reflect.Array;
import java.util.*;


import model.*;
import command.*;
import command.commands.*;



/**
 * Controller class for the add item batch view.
 */
public class AddItemBatchController extends Controller implements
		IAddItemBatchController, Observer{

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


		_commandCenter = new CommandCenter(this);

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
		// The product exists only in the model
		else if(modelProduct != null && viewProduct == null){
			ImportProductCommand ipCommand = new ImportProductCommand(
				modelProduct.getTagData(),
				this);
			performAction(ipCommand);
		}
		// The product exists in 
		else{
			AddItemBatchCommand aibCommand = new AddItemBatchCommand(
				viewProduct,
				this
			);
			performAction(aibCommand);
		}
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

	//__________________________________ Observer functions ________________________________

	@Override
	public void update(Observable o, Object arg) {
		this.updateView();	
	}

	//_____________________________ Public "US IMPLEMENTED" methods _______________________

	/**
	 *	Add a ItemData to the local list
	 *  @param item (ItemData ) the ___ to be added to the list
	 */
	public void addItem(ItemData id){
		_items.add(id);
	}

	/**
	 *	Add a ProductData to the local list
	 *  @param product (ProductData ) the ___ to be added to the list
	 */
	public void addProduct(ProductData pd){
		_products.add(pd);
	}

	/**
	  *		Gets the information needed for an addItemBatchCommand
	  *  	@return a map of relevant info
	  */
	public Map<String,Object> getAIBCInfo(){
		Map<String,Object> info = new HashMap<String,Object>();
		

		model.Date entryDate = new model.Date(getView().getEntryDate());
		info.put("entryDate",entryDate);
		info.put("count",Integer.parseInt(getView().getCount()));
		info.put("storageUnit",_storageUnit);

		return info;
	}

	/**
	 *	Performs the specified command
	 */ 
	public void performAction(Command c){
		_commandCenter.doIt(c);
	}

	/**
	 *	Remove a ItemData from the local list
	 *  @param item (ItemData ) the ___ to be added removed from list
	 */
	public void removeItem(ItemData id){
		_items.remove(id);
	}

	/**
	 *	Remove a ProductData to the local list
	 *  @param product (ProductData ) the ___ to be removed from the the list
	 */
	public void removeProduct(ProductData pd){
		_products.remove(pd);
	}

	/**
	 *  Updates the view to reflect the current state
	 */
	public void updateView(){
		_updatingView = true;

		ProductData selected = getView().getSelectedProduct();
		
		loadProducts();
		getView().selectProduct(selected);

		loadItems();
		this.enableDisableDos();
		this.enableDisableAddItem();
		_updatingView = false;
	}

	//_____________________________  Private Methods  ________________________________

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
		ArrayList<ItemData> items = new ArrayList<ItemData>();

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
}

