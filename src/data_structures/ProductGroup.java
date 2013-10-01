package data_structures;


/**\
 * @author nRitchie
 * Composite: An implementation of ProductContainer 
 * to organize products into groups
 */
public class ProductGroup extends ProductContainer{
	private ProductContainer _container;
	private UnitSize _threeMonthSurprise;
	
	public ProductContainer getContainer() {
		return _container;
	}
	public void setContainer(ProductContainer _container) {
		this._container = _container;
	}
	
	public UnitSize getThreeMonthSurprise() {
		return _threeMonthSurprise;
	}
	public void setThreeMonthSurprise(UnitSize _threeMonthSurprise) {
		this._threeMonthSurprise = _threeMonthSurprise;
	}


}
