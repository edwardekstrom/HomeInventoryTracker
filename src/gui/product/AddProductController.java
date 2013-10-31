

package gui.product;

import model.Barcode;
import model.Date;
import model.Product;
import facade.ProductFacade;
import gui.common.*;

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

		
		setDefaults(barcode);

		// Ours
		_productFacade = ProductFacade.getInstance();
		valuesChanged();
	}


	private void setDefaults(String barcode){
		IAddProductView v = getView();
		v.setBarcode(barcode);
		v.setSizeUnit(SizeUnits.Count);
		v.enableBarcode(false);
		v.setShelfLife("0");
		v.setSupply("0");
		countDefaults();
	}

	private void countDefaults(){
		IAddProductView v = getView();
		v.setSizeValue("1");
		v.enableSizeValue(false);
	}

	private void notCountDefaults(){
		IAddProductView v = getView();
		v.enableSizeValue(true);
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

		changedUnit();
		validateCurrentProduct();
	}

	private void changedUnit(){
		IAddProductView v = getView();
		SizeUnits su = v.getSizeUnit();
		if (su == SizeUnits.Count)
			countDefaults();
		else
			notCountDefaults();
	}

	private void validateCurrentProduct(){
		String shelfLife = getView().getShelfLife();
		String threeMonthSupply = getView().getSupply();
		String amount = getView().getSizeValue();
		String unit = getView().getSizeUnit().toString();
		String desc = getView().getDescription();


		boolean canAdd = _productFacade.canAddProduct(shelfLife,threeMonthSupply,amount,unit,desc);
		// System.out.println(canAdd);
		getView().enableOK(canAdd);
	}

	/**
	 * This method is called when the user clicks the "OK"
	 * button in the add product view.
	 */
	@Override
	public void addProduct() {

		try{
			String shelfLife = getView().getShelfLife();
			String threeMonthSupply = getView().getSupply();
			String amount = getView().getSizeValue();
			String unit = getView().getSizeUnit().toString();
			String barcode = getView().getBarcode();
			String desc = getView().getDescription();

			Integer sl = Integer.parseInt(shelfLife);
			Integer tms = Integer.parseInt(threeMonthSupply);

			Product p = new Product(new Date(),new Barcode(barcode),desc,sl,tms,amount,unit);
			ProductData pd = new ProductData();

			pd.setBarcode(barcode);
			pd.setSize(amount + " " + unit);
			pd.setShelfLife(shelfLife);
			pd.setSupply(threeMonthSupply);
			//pd.setCount(unit);
			pd.setDescription(desc);
			pd.setTag(p);
			p.setTagData(pd);
			_productFacade.addProduct(p);
		}catch (Exception e){}
	}

}

