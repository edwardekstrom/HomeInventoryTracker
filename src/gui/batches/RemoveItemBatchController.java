package gui.batches;

import facade.*;
import gui.common.*;
import gui.product.*;
import singletons.*;
import gui.product.ProductData;
import gui.item.ItemData;

import java.util.ArrayList;

import model.*;

/**
 * Controller class for the remove item batch view.
 */
public class RemoveItemBatchController extends Controller implements
		IRemoveItemBatchController {
	
	private ArrayList<ItemData> _items;
	private ArrayList<ProductData> _products;

	/**
	 * Constructor.
	 * 
	 * @param view Reference to the remove item batch view.
	 */
	public RemoveItemBatchController(IView view) {
		super(view);

		_items = new ArrayList<ItemData>();
		_products = new ArrayList<ProductData>();
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
		ItemData[] items = _items.toArray(new ItemData[_items.size()]);
		getView().setItems(items);

		ProductData[] products = _products.toArray(new ProductData[_products.size()]);
		getView().setProducts(products);


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
		v.enableRedo(false);
		v.enableUndo(false);
		v.enableItemAction(false);
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
			reset();
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
			v.setBarcode("");
			v.displayErrorMessage("The specified item does not exist.");
		}
		else{
			ItemData empty = item.getTagData();
			Product p = item.getProduct();

			ItemFacade.getInstance().removeItem(item);
			_items.add(empty);
			_products.add(p.getTagData());
			loadValues();
		}
	}
	
	/**
	 * This method is called when the user clicks the "Redo" button
	 * in the remove item batch view.
	 */
	@Override
	public void redo() {
	}

	/**
	 * This method is called when the user clicks the "Undo" button
	 * in the remove item batch view.
	 */
	@Override
	public void undo() {
	}

	/**
	 * This method is called when the user clicks the "Done" button
	 * in the remove item batch view.
	 */
	@Override
	public void done() {
		getView().close();
	}

	public void reset(){
		getView().setBarcode("");
		getView().giveBarcodeFocus();
	}
}

