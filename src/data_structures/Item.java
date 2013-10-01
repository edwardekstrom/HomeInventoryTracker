package data_structures;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Capchu
 * This class manages Items in storage
 */
public class Item implements Serializable{

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
	public Item(Product product, Barcode barcode, Date entryDate, ProductContainer container) {
		_product = product;
		_barcode = barcode;
		_entryDate = entryDate;
		setExpirationDate();
		_container = container;
	}

	/**
	 * 
	 */
	private boolean setExpirationDate() {
		_expirationDate = new Date();
		return true;
	}
	
	/**
	 * @return the _product
	 */
	public Product getProduct() {
		return _product;
	}

	/**
	 * @return the _barcode
	 */
	public Barcode getBarcode() {
		return _barcode;
	}

	/**
	 * @return the _entryDate
	 */
	public Date getEntryDate() {
		return _entryDate;
	}

	/**
	 * @param _entryDate the _entryDate to set
	 */
	public boolean setEntryDate(Date entryDate) {
		_entryDate = entryDate;
		setExpirationDate();
		return true;
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
	public boolean setExitTime(Date exitTime) {
		_exitTime = exitTime;
		return true;
	}

	/**
	 * @return the _expirationDate
	 */
	public Date getExpirationDate() {
		return _expirationDate;
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
	public boolean setContainer(ProductContainer container) {
		_container = container;
		return true;
	}
	
	public boolean canMove(){
		return true;
	}
	
}
