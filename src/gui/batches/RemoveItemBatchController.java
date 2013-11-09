package gui.batches;

import facade.*;
import gui.common.*;
import gui.product.*;
import singletons.*;
import gui.product.ProductData;
import gui.item.ItemData;

import java.util.ArrayList;
import java.util.Observer;
import java.util.Observable;

import command.*;
import command.commands.*;

import model.*;

/**
 * Controller class for the remove item batch view.
 */
public class RemoveItemBatchController extends Controller implements
		IRemoveItemBatchController, Observer {
	
	private ArrayList<ItemData> _items;
	private ArrayList<ProductData> _products;
	private boolean _updatingView = false;
	private CommandCenter _commandCenter;

	/**
	 * Constructor.
	 * 
	 * @param view Reference to the remove item batch view.
	 */
	public RemoveItemBatchController(IView view) {
		super(view);

		_items = new ArrayList<ItemData>();
		_products = new ArrayList<ProductData>();
		
		_commandCenter = new CommandCenter(this);
		construct();


	}
	
	/**
	 * Returns a reference to the view for this controller.
	 */
	@Override
	protected IRemoveItemBatchView getView() {
		return (IRemoveItemBatchView)super.getView();
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
		IRemoveItemBatchView v = getView();
		setDefaultValues();
		v.setUseScanner(true);
	}

	/**
	 * This method is called when the "Item Barcode" field is changed
	 * in the remove item batch view by the user.
	 */
	@Override
	public void barcodeChanged() {
		IRemoveItemBatchView v = getView();
		if(!v.getUseScanner())
			v.enableItemAction(!v.getBarcode().equals(""));
		else{
			v.enableItemAction(false);
			if(!v.getBarcode().equals(""))
				removeItem();
			setDefaultValues();
		}
	}
	
	/**
	 * This method is called when the "Use Barcode Scanner" setting is changed
	 * in the remove item batch view by the user.
	 */
	@Override
	public void useScannerChanged() {
	}
	
	/**
	 * This method is called when the selected product changes
	 * in the remove item batch view.
	 */
	@Override
	public void selectedProductChanged() {
		if(!_updatingView)
			this.updateView();
	}
	
	/**
	 * This method is called when the user clicks the "Remove Item" button
	 * in the remove item batch view.
	 */
	@Override
	public void removeItem() {
		IRemoveItemBatchView v = getView();
		Item item = ItemsManager.getInstance().getItem(v.getBarcode());
		if(item == null){
			setDefaultValues();
			v.displayErrorMessage("The specified item does not exist.");
		}
		else{
			RemoveItemCommand ric = new RemoveItemCommand(
				item.getTagData(),
				item.getProduct().getTagData(),
				this);
			_commandCenter.doIt(ric);

			setDefaultValues();
		}
	}
	
	/**
	 * This method is called when the user clicks the "Redo" button
	 * in the remove item batch view.
	 */
	@Override
	public void redo() {
		_commandCenter.redo();
		setDefaultValues();
	}

	/**
	 * This method is called when the user clicks the "Undo" button
	 * in the remove item batch view.
	 */
	@Override
	public void undo() {
		_commandCenter.undo();
		setDefaultValues();
	}

	/**
	 * This method is called when the user clicks the "Done" button
	 * in the remove item batch view.
	 */
	@Override
	public void done() {
		getView().close();
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
	
		ItemData[] items = _items.toArray(new ItemData[_items.size()]);
		getView().setItems(items);

		ProductData[] products = _products.toArray(new ProductData[_products.size()]);
		getView().setProducts(products);
		getView().selectProduct(selected);


		this.enableDisableDos();
		this.enableDisableItemAction();

		_updatingView = false;
	}

	//_____________________________  Private Methods  ________________________________

	/**
	 * Enables or disables the Remove Item button as appropriate
	 * @return whether Add Item is enabled or disabled
	 */
	private boolean enableDisableItemAction(){
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

	private void setDefaultValues(){
		getView().setBarcode("");
		getView().giveBarcodeFocus();
		this.enableDisableDos();
		this.enableDisableItemAction();
	}


}

