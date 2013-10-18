package gui.product;

import gui.common.*;
import ui_interaction.ProductFacade;

/**
 * Controller class for the add item view.
 */
public class AddProductController extends Controller implements
		IAddProductController {
	
	private ProductFacade _productFacade;

	/**
	 * Constructor.
	 * 
	 * @param view Reference to the add product view
	 * @param barcode Barcode for the product being added
	 */
	public AddProductController(IView view, String barcode) {
		//Theirs
		super(view);
		construct();

		// Ours
		_productFacade = ProductFacade.getInstance();
		valuesChanged();
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
	protected IAddProductView getView() {
		return (IAddProductView)super.getView();
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
	// IAddProductController overrides
	//
	
	/**
	 * This method is called when any of the fields in the
	 * add product view is changed by the user.
	 */
	@Override
	public void valuesChanged() {
		String shelfLife = getView().getShelfLife();
		String threeMonthSupply = getView().getSupply();
		String amount = getView().getSizeValue();
		String unit = getView().getSizeUnit().toString();
		String desc = getView().getDescription();


		boolean canAdd = _productFacade.canAddProduct(shelfLife,threeMonthSupply,amount,unit,desc);
		getView().enableOK(canAdd);



	}
	
	/**
	 * This method is called when the user clicks the "OK"
	 * button in the add product view.
	 */
	@Override
	public void addProduct() {
		String shelfLife = getView().getShelfLife();
		String threeMonthSupply = getView().getSupply();
		String amount = getView().getSizeValue();
		String unit = getView().getSizeUnit().toString();
		String barcode = getView().getBarcode();
		String desc = getView().getDescription();

		_productFacade.addProduct(shelfLife,threeMonthSupply,amount,unit,barcode,desc);
	}

}

