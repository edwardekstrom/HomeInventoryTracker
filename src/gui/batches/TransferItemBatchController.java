package gui.batches;

import gui.common.*;
import gui.inventory.*;
import gui.product.*;

import singletons.*;
import gui.product.ProductData;
import gui.item.ItemData;

import data_structures.*;
import ui_interaction.*;

import java.util.ArrayList;

/**
 * Controller class for the transfer item batch view.
 */
public class TransferItemBatchController extends Controller implements
		ITransferItemBatchController {
	
	private ArrayList<ItemData> _items;
	private ArrayList<ProductData> _products;
	ProductContainerData _target;

	/**
	 * Constructor.
	 * 
	 * @param view Reference to the transfer item batch view.
	 * @param target Reference to the storage unit to which items are being transferred.
	 */
	public TransferItemBatchController(IView view, ProductContainerData target) {
		super(view);
        _target = target;

		_items = new ArrayList<ItemData>();
		_products = new ArrayList<ProductData>();

		construct();

		
	}
	
	/**
	 * Returns a reference to the view for this controller.
	 */
	@Override
	protected ITransferItemBatchView getView() {
		return (ITransferItemBatchView)super.getView();
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
		ITransferItemBatchView v = getView();
		v.enableRedo(false);
		v.enableUndo(false);
		v.enableItemAction(false);
		v.setUseScanner(true);
	}

	/**
	 * This method is called when the "Item Barcode" field in the
	 * transfer item batch view is changed by the user.
	 */
	@Override
	public void barcodeChanged() {
		ITransferItemBatchView v = getView();
		if(!v.getUseScanner())
			v.enableItemAction(!v.getBarcode().equals(""));
		else{
			v.enableItemAction(false);
			if(!v.getBarcode().equals(""))
				transferItem();
		}

	}
	
	/**
	 * This method is called when the "Use Barcode Scanner" setting in the
	 * transfer item batch view is changed by the user.
	 */
	@Override
	public void useScannerChanged() {
	}
	
	/**
	 * This method is called when the selected product changes
	 * in the transfer item batch view.
	 */
	@Override
	public void selectedProductChanged() {
	}
	
	/**
	 * This method is called when the user clicks the "Transfer Item" button
	 * in the transfer item batch view.
	 */
	@Override
	public void transferItem() {
		ITransferItemBatchView v = getView();
		Item item = ItemsManager.getInstance().getItem(v.getBarcode());
		if(item == null){
			v.setBarcode("");
			v.displayErrorMessage("The specified item does not exist.");
		}
		else{
			ItemData empty = item.getTagData();
			Product p = item.getProduct();

			ItemFacade.getInstance().moveItemInTree(item,(ProductContainer)_target.getTag());
			_items.add(empty);
			_products.add(p.getTagData());
			loadValues();
		}
	}
	
	/**
	 * This method is called when the user clicks the "Redo" button
	 * in the transfer item batch view.
	 */
	@Override
	public void redo() {
	}

	/**
	 * This method is called when the user clicks the "Undo" button
	 * in the transfer item batch view.
	 */
	@Override
	public void undo() {
	}

	/**
	 * This method is called when the user clicks the "Done" button
	 * in the transfer item batch view.
	 */
	@Override
	public void done() {
		getView().close();
	}

}

