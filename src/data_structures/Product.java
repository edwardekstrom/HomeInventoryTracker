package data_structures;

import hit_exceptions.InvalidAmountException;
import hit_exceptions.InvalidShelfLifeException;
import hit_exceptions.InvalidUnitException;

import java.io.Serializable;
import java.util.List;
import java.util.TreeMap;
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
			       Integer shelfLife, Integer threeMonthSupply) {
		_creationDate = creationDate;
		_barcode = barcode;
		_description = description;
		_size = new UnitSize();
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
			_shelfLife = Integer.parseInt(shelfLife);
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

	/**
	 * @param _threeMonthSupply the _threeMonthSupply to set
	 */
	public boolean setThreeMonthSupply(String threeMonthSupply) {
		if(isValidThreeMonthSupply(threeMonthSupply)){
			_threeMonthSupply = Integer.parseInt(threeMonthSupply);
			return true;
		}else{
			return false;
		}
	}

	/**
	 * @return the _containersList
	 */
	public List<ProductContainer> getContainersList() {
		return _containersList;
	}

	/**
	 * @return the _containersMap
	 */
	public TreeMap<ProductContainer, String> getContainersTree() {
		return _containersTree;
	}
	
	/**
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
	
	/**
	 * @return true if the shelfLife is valid
	 */
	private boolean isValidShelfLife(String shelfLife){
		try{
			Integer.parseInt(shelfLife);
			return true;
		}catch(NumberFormatException nfe){
			return false;
		}
	}
	
	/**
	 * @return true if the shelfLife is valid
	 */
	private boolean isValidThreeMonthSupply(String threeMonthSupply){
		try{
			Integer.parseInt(threeMonthSupply);
			return true;
		}catch(NumberFormatException nfe){
			return false;
		}
	}
	
}
