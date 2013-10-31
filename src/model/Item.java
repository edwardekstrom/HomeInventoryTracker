package model;

import gui.item.ItemData;
import hit_exceptions.NullContainerException;
import hit_exceptions.NullEntryDateException;
import hit_exceptions.NullExitDateException;

import java.io.Serializable;

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
	private ItemData _tagData;
	
	/**@precondition none
	 * @postcondition creates a new Item with the given data
	 * 
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
		
		_tagData = new ItemData();
		_tagData.setTag(this);
		_tagData.setBarcode(barcode.getBarcode());
		_tagData.setEntryDate(new java.util.Date(entryDate.getDate().getTimeInMillis()));
		_tagData.setExpirationDate(new java.util.Date(_expirationDate.getDate().getTimeInMillis()));
		//TODO Make sure these work
		_tagData.setStorageUnit(container.getStorageUnit().getName());
		// _tagData.setProductGroup(container.getName());
	}

	/**@precondition expDate is not null
	 * @postcondition sets the expiration date using the entryDate
	 * 
	 */
	private void setExpirationDate() {
		assert (_entryDate != null);
		_expirationDate = _entryDate.generateExperationDate(_product.getShelfLife());
	}
	
	/**@precondition none
	 * @postcondition gives the product
	 * @return the _product
	 */
	public Product getProduct() {
		return _product;
	}
	
	public ItemData getTagData(){
		return _tagData;
	}

	/**@precondition none
	 * @postcondition returns the barcode
	 * @return the _barcode
	 */
	public Barcode getBarcode() {
		return _barcode;
	}

	/**@precondition none
	 * @postcondition gives the entryDate
	 * @return the _entryDate
	 */
	public Date getEntryDate() {
		return _entryDate;
	}

	/**@precondition a non null entryDate
	 * @postcondition sets the entry date and generates and 
	 * sets the expiration date based on the new date
	 *
	 * @param _entryDate the _entryDate to set
	 */
	public void setEntryDate(Date entryDate) throws NullEntryDateException{
		if(entryDate == null){
			throw new NullEntryDateException();
		}else{
			_entryDate = entryDate;
			setExpirationDate();
			
		}

	}

	/**@precondition none
	 * @postcondition gives the exit time
	 * @return the _exitTime
	 */
	public Date getExitTime() {
		return _exitTime;
	}

	/**@precondition a non null exitTime
	 * @postcondition sets the _exitTime
	 * @param _exitTime the _exitTime to set
	 */
	public void setExitTime(Date exitTime) throws NullExitDateException{
		if(exitTime == null){
			throw new NullExitDateException();
		}else{
			_exitTime = exitTime;
		}
	}

	/**@precondition none
	 * @postcondition gives the expiration date
	 * @return the _expirationDate
	 */
	public Date getExpirationDate() {
		return _expirationDate;
	}

	/**@precondition none
	 * @postcondition gives the container
	 * @return the _container
	 */
	public ProductContainer getContainer() {
		return _container;
	}

	/**@precondition passed a non null container
	 * @postcondition sets the container
	 * @param _container the _container to set
	 */
	public void setContainer(ProductContainer container) throws NullContainerException{
		if(container == null){
			throw new NullContainerException();
		}else{
			_container = container;
		}
	}
	
	
}
