package data_structures;

import java.util.Date;

/**
 * @author Capchu
 * This class manages Items in storage
 */
public class Item {

	private Product _product;
	private Barcode _barcode;
	private Date _entryDate;
	private Date _exitTime;
	private Date _expirationDate;
	private ProductContainer _container;
	
	/**
	 * @param _product
	 * @param _barcode
	 * @param _entryDate
	 * @param _expirationDate
	 * @param _container
	 */
	public Item(Product product, Barcode barcode, Date entryDate, Date expirationDate, ProductContainer container) {
		_product = product;
		_barcode = barcode;
		_entryDate = entryDate;
		_expirationDate = expirationDate;
		_container = container;
	}

	/**
	 * @return the _product
	 */
	public Product getProduct() {
		return _product;
	}

//	/**
//	 * @param _product the _product to set
//	 */
//	public void setProduct(Product product) {
//		_product = product;
//	}

	/**
	 * @return the _barcode
	 */
	public Barcode getBarcode() {
		return _barcode;
	}

//	/**
//	 * @param _barcode the _barcode to set
//	 */
//	private void setBarcode(Barcode barcode) {
//		_barcode = barcode;
//	}

	/**
	 * @return the _entryDate
	 */
	public Date getEntryDate() {
		return _entryDate;
	}

	/**
	 * @param _entryDate the _entryDate to set
	 */
	public void setEntryDate(Date entryDate) {
		_entryDate = entryDate;
	}

	/**
	 * @return the _exitTime
	 */
	public Date getExitTime() {
		return _exitTime;
	}

	/**
	 * @param _exitTime the _exitTime to set
	 */
	public void setExitTime(Date exitTime) {
		_exitTime = exitTime;
	}

	/**
	 * @return the _expirationDate
	 */
	public Date getExpirationDate() {
		return _expirationDate;
	}

	/**
	 * @param _expirationDate the _expirationDate to set
	 */
	public void setExpirationDate(Date expirationDate) {
		_expirationDate = expirationDate;
	}

	/**
	 * @return the _container
	 */
	public ProductContainer getContainer() {
		return _container;
	}

	/**
	 * @param _container the _container to set
	 */
	public void setContainer(ProductContainer container) {
		_container = container;
	}
	
	public boolean canMove(){
		return true;
	}
	
}
