package data_structures;

import java.util.Date;
import java.util.List;
import java.util.Map;

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
	public Product(Date creationDate, Barcode barcode, String description, UnitSize size, Integer shelfLife, Integer threeMonthSupply) {
		_creationDate = creationDate;
		_barcode = barcode;
		_description = description;
		_size = size;
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
	 * @param _barcode the _barcode to set
	 */
	public void setBarcode(Barcode barcode) {
		_barcode = barcode;
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
	public void setDescription(String description) {
		_description = description;
	}

	/**
	 * @return the _size
	 */
	public UnitSize getSize() {
		return _size;
	}

	/**
	 * @param _size the _size to set
	 */
	public void setSize(UnitSize size) {
		_size = size;
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
	public void setShelfLife(Integer shelfLife) {
		_shelfLife = shelfLife;
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
	public void setThreeMonthSupply(Integer threeMonthSupply) {
		_threeMonthSupply = threeMonthSupply;
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
}
