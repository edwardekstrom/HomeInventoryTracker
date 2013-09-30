package data_structures;

import java.util.Date;
import java.util.List;
import java.util.TreeMap;
/**
 * @author Capchu
 * This class is for storing objects
 */
public class Product {

	private Date _creationDate;
	private Barcode _barcode;
	private String _description;
	private UnitSize _size;
	private Integer _shelfLife;
	private Integer _threeMonthSupply;
	private List<ProductContainer> _containersList;
	private TreeMap<ProductContainer, String> _containersTree;
	
	/**
	 * @param _creationDate
	 * @param _barcode
	 * @param _description
	 * @param _size
	 * @param _shelfLife
	 * @param _threeMonthSupply
	 */
	public Product(Date creationDate, Barcode barcode, String description, Integer shelfLife, Integer threeMonthSupply) {
		_creationDate = creationDate;
		_barcode = barcode;
		_description = description;
		_size = new UnitSize();
		_shelfLife = shelfLife;
		_threeMonthSupply = threeMonthSupply;
	}
	
	/**
	 * @return the _creationDate
	 */
	public Date getCreationDate() {
		return _creationDate;
	}

	/**
	 * @param _creationDate the _creationDate to set
	 */
	public void setCreationDate(Date creationDate) {
		_creationDate = creationDate;
	}

	/**
	 * @return the _barcode
	 */
	public Barcode getBarcode() {
		return _barcode;
	}

	/**
	 * @return the _description
	 */
	public String getDescription() {
		return _description;
	}

	/**
	 * @param _description the _description to set
	 */
	public boolean setDescription(String description) {
		_description = description;
		return true;
	}

	/**
	 * @return the amount of _size
	 */
	public float getSizeAmount() {
		return _size.getAmount();
	}

	/**
	 * @param String amount
	 */
	public boolean setSizeAmount(String amount) {
		return _size.setAmount(amount);
	}
	
	/**
	 * @return the unit of _size
	 */
	public String getSizeUnit() {
		return _size.getUnit();
	}

	/**
	 * @param String unit the unit to set for _size
	 */
	public boolean setSizeUnit(String unit) {
		return _size.setUnit(unit);
	}

	/**
	 * @return the _shelfLife
	 */
	public Integer getShelfLife() {
		return _shelfLife;
	}

	/**
	 * @param _shelfLife the _shelfLife to set
	 */
	public boolean setShelfLife(String shelfLife) {
		if(isValidShelfLife(shelfLife)){
			_shelfLife = Integer.parseInt(shelfLife);
			return true;
		}else{
			return false;
		}
	}

	/**
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
		if(isValidShelfLife(_shelfLife+"") && isValidThreeMonthSupply(_threeMonthSupply+"") && _size.isCurrentlyValid()){
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
