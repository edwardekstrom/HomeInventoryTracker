package gui.item;

import gui.common.*;
import data_structures.*;

import java.util.Date;

import ui_interaction.*;

/**
 * Controller class for the edit item view.
 */
public class EditItemController extends Controller 
										implements IEditItemController {
	
	private ItemData _target;
	private int _count;

	/**
	 * Constructor.
	 * 
	 * @param view Reference to edit item view
	 * @param target Item that is being edited
	 */
	public EditItemController(IView view, ItemData target) {
		super(view);
		construct();
		_target = target;

		String barcode = _target.getBarcode();
		Product p = ((Item)_target.getTag()).getProduct();
		String desc = p.getDescription();

		Date entry = _target.getEntryDate();

		getView().setBarcode(barcode);
		getView().setDescription(desc);
		getView().setEntryDate(entry);

		_count = 0;
	}

	//
	// Controller overrides
	//
	
	/**
	 * Returns a reference to the view for this controller.
	 * 
	 * {@pre None}
	 * 
	 * {@post Returns a reference to the view for this controller.}
	 */
	@Override
	protected IEditItemView getView() {
		return (IEditItemView)super.getView();
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
		getView().enableBarcode(false);
		getView().enableDescription(false);
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

	//
	// IEditItemController overrides
	//

	/**
	 * This method is called when any of the fields in the
	 * edit item view is changed by the user.
	 */
	@Override
	public void valuesChanged() {
		Date entryDate = getView().getEntryDate();

		if (entryDate == null){
			getView().enableOK(false);
		}
		else{
			getView().enableOK(true);
		}
	}
	
	/**
	 * This method is called when the user clicks the "OK"
	 * button in the edit item view.
	 */
	@Override
	public void editItem() {
		Date entryDate = getView().getEntryDate();

		Item item = (Item)_target.getTag();
		data_structures.Date entry = new data_structures.Date(entryDate);
		try{
			item.setEntryDate(entry);
			_target.setEntryDate(entryDate);
		}catch(Exception e){}


	}

}

