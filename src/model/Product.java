package data_structures;

import hit_exceptions.InvalidAmountException;
import hit_exceptions.InvalidShelfLifeException;
import hit_exceptions.InvalidThreeMonthSupplyException;
import hit_exceptions.InvalidUnitException;

import java.io.Serializable;
import java.util.List;
import java.util.TreeMap;

import gui.product.*;

/**
 * @author Capchu
 * This class is for storing objects
 */
public class Product implements Serializable{

	private Date _creationDate;
	private Barcode _barcode;
	private String _description;
	private UnitSize _size;
	private Integer _shelfLife;
	private Integer _threeMonthSupply;
	private List<ProductContainer> _containersList;
	private TreeMap<ProductContainer, String> _containersTree;

	private ProductData _tagData;
	
	/**@precondition none
	 * @postcondition creates a new Product with the given data, and creates a UnitSize
	 * @param _creationDate
	 * @param _barcode
	 * @param _description
	 * @param _size
	 * @param _shelfLife
	 * @param _threeMonthSupply
	 */
	public Product(Date creationDate, Barcode barcode, String description, 
			       Integer shelfLife, Integer threeMonthSupply,String amount,
			        String unit) throws InvalidUnitException, InvalidAmountException{
		_creationDate = creationDate;
		_barcode = barcode;
		_description = description;
		_size = new UnitSize(amount,unit);
		_shelfLife = shelfLife;
		_threeMonthSupply = threeMonthSupply;
	}
	
	/**@precondition none
	 * @postcondition returns the date of creation
	 * @return the _creationDate
	 */
	public Date getCreationDate() {
		return _creationDate;
	}

	/**@precondition none
	 * @postcondition sets the date of creation 
	 * @param _creationDate the _creationDate to set
	 */
	public void setCreationDate(Date creationDate) {
		_creationDate = creationDate;
	}

	/**@precondition none
	 * @postcondition gives the barcode
	 * @return the _barcode
	 */
	public Barcode getBarcode() {
		return _barcode;
	}

	/**@precondition none
	 * @postcondition gives the description string
	 * @return the _description
	 */
	public String getDescription() {
		return _description;
	}

	/**@precondition none
	 * @postcondition sets the description
	 * @param _description the _description to set
	 */
	public boolean setDescription(String description) {
		_description = description;
		return true;
	}

	/**@precondition none
	 * @postcondition gives you the size
	 * @return the amount of _size
	 */
	public float getSizeAmount() {
		return _size.getAmount();
	}

	/**@precondition a valid amount string
	 * @postcondition sets the Amount of _size to the given amount
	 * @param String amount
	 */
	public void setSizeAmount(String amount) throws InvalidAmountException{
		_size.setAmount(amount);
	}
	
	/**@precondition none
	 * @postcondition returns the Unit
	 * @return the unit of _size
	 */
	public String getSizeUnit() {
		return _size.getUnit();
	}

	/**@precondition a valid unit string is passed in
	 * @postcondition sets the _size unit to the given unit
	 * @param String unit the unit to set for _size
	 */
	public void setSizeUnit(String unit) throws InvalidUnitException{
		_size.setUnit(unit);
	}

	/**@precondition none
	 * @postcondition given the shelf life
	 * @return the _shelfLife
	 */
	public Integer getShelfLife() {
		return _shelfLife;
	}

	/**@precondition given a valid shelfLife string
	 * @postcondition sets the shelf life to the given value
	 * @param _shelfLife the _shelfLife to set
	 */
	public void setShelfLife(String shelfLife) throws InvalidShelfLifeException{
		if(isValidShelfLife(shelfLife)){
			int newShelfLife = Integer.parseInt(shelfLife);
			if(newShelfLife >= 0){
				_shelfLife = newShelfLife;
			}else{
				throw new InvalidShelfLifeException();
			}
		}else{
			throw new InvalidShelfLifeException();
		}
	}

	/**@precondition none
	 * @postcondition returns the three month supply
	 * @return the _threeMonthSupply
	 */
	public Integer getThreeMonthSupply() {
		return _threeMonthSupply;
	}

	/**@precondition a String representation of the threeMonthSupply
	 * @postcondition sets the three month supply to the given value
	 * @param _threeMonthSupply the _threeMonthSupply to set
	 */
	public void setThreeMonthSupply(String threeMonthSupply) throws InvalidThreeMonthSupplyException {
		if(isValidThreeMonthSupply(threeMonthSupply)){
			int newThreeMonthsupply = Integer.parseInt(threeMonthSupply);
			if(newThreeMonthsupply >= 0){
				_threeMonthSupply = newThreeMonthsupply;
			}else{
				throw new InvalidThreeMonthSupplyException();
			}
		}else{
			throw new InvalidThreeMonthSupplyException();
		}
	}

	/**@precondition none
	 * @postcondition gives you the container list
	 * @return the _containersList
	 */
	public List<ProductContainer> getContainersList() {
		return _containersList;
	}

	/**@precondition none
	 * @postcondition gives the container tree
	 * @return the _containersMap
	 */
	public TreeMap<ProductContainer, String> getContainersTree() {
		return _containersTree;
	}
	
	/**@precondition none
	 * @postcondition returns true if the current product is valid
	 * @return true if the product is valid
	 */
	public boolean isValidProduct(){
		if(isValidShelfLife(_shelfLife+"") && isValidThreeMonthSupply(_threeMonthSupply+"") 
		   && _size.isCurrentlyValid()){
			
			return true;
		}else{
			return false;
		}
	}
	
	/**@precondition none
	 * @postcondition returns true if the current product is valid
	 * @return true if the product is valid
	 */
	public static boolean willBeValidProduct(String shelfLife, String threeMonthSupply, 
			                          String amount, String unit, String desc){
		if(isValidShelfLife(shelfLife) && isValidThreeMonthSupply(threeMonthSupply) 
		   && UnitSize.isValid(amount, unit) &&  !desc.equals("")){
			return true;
		}else{
			return false;
		}
	}
	
	/**@precondition passed a String
	 * @postcondition returns true if the String is a valid shelf life
	 * @return true if the shelfLife is valid
	 */
	public static boolean isValidShelfLife(String shelfLife){
		try{
			int newShelfLife = Integer.parseInt(shelfLife);
			if(newShelfLife < 0){
				return false;
			}else{
				return true;
			}
		}catch(NumberFormatException nfe){
			return false;
		}
	}
	
	/**@precondition passed a string
	 * @postcondition returns True if the passed string is a valid shelf life
	 * @return true if the shelfLife is valid
	 */
	public static boolean isValidThreeMonthSupply(String threeMonthSupply){
		try{
			int newThreeMonthsupply = Integer.parseInt(threeMonthSupply);
			if(newThreeMonthsupply < 0){
				return false;
			}else{
				return true;
			}
		}catch(NumberFormatException nfe){
			return false;
		}
	}

	/**		
	 * sets its tag.
	 * @param pcData
	 */
	public void setTagData(ProductData data) {
		_tagData = data;
	}

	public ProductData getTagData(){
		return this._tagData;
	}

	
}
