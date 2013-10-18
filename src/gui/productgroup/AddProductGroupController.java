package gui.productgroup;

import data_structures.ProductContainer;
import data_structures.ProductGroup;
import data_structures.UnitSize;
import ui_interaction.ProductGroupFacade;
import gui.common.*;
import gui.inventory.*;

/**
 * Controller class for the add product group view.
 */
public class AddProductGroupController extends Controller implements
		IAddProductGroupController {
	
	private ProductGroupFacade _productGroupFacade;
	private ProductContainer _parent;
	
	/**
	 * Constructor.
	 * 
	 * @param view Reference to add product group view
	 * @param container Product container to which the new product group is being added
	 */
	public AddProductGroupController(IView view, ProductContainerData container) {
		super(view);
		
		construct();
		_productGroupFacade  = ProductGroupFacade.getInstance();
		_parent = (ProductContainer) container.getTag();
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
	protected IAddProductGroupView getView() {
		return (IAddProductGroupView)super.getView();
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
	// IAddProductGroupController overrides
	//

	/**
	 * This method is called when any of the fields in the
	 * add product group view is changed by the user.
	 */
	@Override
	public void valuesChanged() {
		String name = getView().getProductGroupName();
		
		boolean validName = _productGroupFacade.canCreateChildWithName(_parent,name);
		
		//TODO Check unit size when chris and jay get their crap together
		boolean validUnitSize = true;
		if(validName && validUnitSize){
			getView().enableOK(true);
		}else{
			getView().enableOK(false);
		}
		
		
	}
	
	/**
	 * This method is called when the user clicks the "OK"
	 * button in the add product group view.
	 */
	@Override
	public void addProductGroup() {
		String newProductGroupName = getView().getProductGroupName();
		String supplyValue = getView().getSupplyValue();
		SizeUnits supplyUnit = getView().getSupplyUnit();
		
		ProductGroup pg = new ProductGroup();
		pg.setName(newProductGroupName);
		
//		TODO uncomment this
//		UnitSize threeMounthSup = new UnitSize(supplyValue, supplyUnit.toString() );
//		pg.setThreeMonthSup(threeMounthSup);
		
		pg.setContainer(_parent);
		
		_productGroupFacade.addProductGroup(pg);
	}

}

