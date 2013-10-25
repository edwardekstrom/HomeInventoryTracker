package gui.productgroup;

import ui_interaction.ProductGroupFacade;
import ui_interaction.StorageUnitFacade;
import data_structures.Item;
import data_structures.ProductContainer;
import data_structures.ProductGroup;
import data_structures.StorageUnit;
import data_structures.UnitSize;
import gui.common.*;
import gui.inventory.*;

/**
 * Controller class for the edit product group view.
 */
public class EditProductGroupController extends Controller 
										implements IEditProductGroupController {
	ProductGroupFacade _productGroupFacade;
	private ProductGroup _target;
	
	/**
	 * Constructor.
	 * 
	 * @param view Reference to edit product group view
	 * @param target Product group being edited
	 */
	public EditProductGroupController(IView view, ProductContainerData target) {
		super(view);

		construct();

		_productGroupFacade = ProductGroupFacade.getInstance();
		_target = (ProductGroup) target.getTag();
		getView().setProductGroupName(_target.getName());
		
		for( SizeUnits v : SizeUnits.values()){
			String unit = _target.getThreeMonthSup().getUnit();
			if(v.toString().equals(unit)){
				getView().setSupplyUnit(v);
				break;
			}
		}
		String value = Float.toString(_target.getThreeMonthSup().getAmount());
		getView().setSupplyValue(value);
		
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
	protected IEditProductGroupView getView() {
		return (IEditProductGroupView)super.getView();
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
	// IEditProductGroupController overrides
	//

	/**
	 * This method is called when any of the fields in the
	 * edit product group view is changed by the user.
	 */
	@Override
	public void valuesChanged() {
		String name = getView().getProductGroupName();
		
		boolean validName = _target.getContainer().canAddProductGroupWithName(name);
		//check if the name hasn't been changed
		if (name.equals(_target.getName())){
			validName = true;
		}
		//TODO fix when jay and chris get their crap together
		String amount = getView().getSupplyValue();
		String supplyUnit = getView().getSupplyUnit().toString();
		boolean validUnitSize = UnitSize.isValid(amount, supplyUnit);
		
		if(validName && validUnitSize){
			getView().enableOK(true);
		}else{
			getView().enableOK(false);
		}
	}
	
	/**
	 * This method is called when the user clicks the "OK"
	 * button in the edit product group view.
	 */
	@Override
	public void editProductGroup() {
		String newProductGroupName = getView().getProductGroupName();
		String supplyValue = getView().getSupplyValue();
		SizeUnits supplyUnit = getView().getSupplyUnit();
		_target.setName(newProductGroupName);
		UnitSize tmSupply = null;
		try{
		tmSupply = new UnitSize(supplyValue, supplyUnit.toString());
		}catch(Exception e){
			System.out.println(supplyUnit.toString());
		}
		_target.setThreeMonthSup(tmSupply);
		_target.getTagData().setName(newProductGroupName);
	}

}

