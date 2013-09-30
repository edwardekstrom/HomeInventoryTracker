package data_structures;


/**\
 * @author nRitchie
 * Composite: An implementation of ProductContainer 
 * to organize products into groups
 */
public class ProductGroup extends ProductContainer{
	
	private StorageUnit _storageUnit;

		
	/**
	 * @return the _storageUnit
	 */
	public StorageUnit getStorageUnit() {
		return _storageUnit;
	}

	/**
	 * @param _storageUnit the _storageUnit to set
	 */
	public void setStorageUnit(StorageUnit _storageUnit) {
		this._storageUnit = _storageUnit;
	}

	@Override
	public void moveProduct(Product product, ProductContainer productContainer) {
//		_storageUnit.moveProduct(product, productContainer);
	}

}
