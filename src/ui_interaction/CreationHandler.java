/**
 * 
 */
package ui_interaction;

import data_structures.Barcode;
import data_structures.Date;
import data_structures.Item;
import data_structures.Product;
import data_structures.ProductContainer;
import data_structures.ProductGroup;

/**
 * @author Capchu
 * @version Used to make items for the program
 */
public class CreationHandler {
	
	private Product _testProd = new Product(new Date(), new Barcode(""), "", 0, 0);
	private Item _testItem = new Item(_testProd, new Barcode("1"), new Date(), new ProductGroup());
	private Date _testDate = new Date();
	
	/**
	 * 
	 * @param description
	 * @param shelfLife
	 * @param threeMonthSupply
	 * @param amount
	 * @param unit
	 * @return true is the given data can make a valid product
	 */
	public boolean isValidProductData(String description, String shelfLife, 
			            String threeMonthSupply, String amount, String unit){
		return false;
	}
	
	/**
	 * 
	 * @param description
	 * @param shelfLife
	 * @param threeMonthSupply
	 * @param amount
	 * @param unit
	 * @return a Product made from the given data
	 */
	public Product createProduct(String description, String shelfLife, 
                  String threeMonthSupply, String amount, String unit){
		return _testProd;
	}
	
	/**
	 * 
	 * @param product
	 * @param barcode
	 * @param entryDate
	 * @param container
	 * @return an Item created with the given data
	 */
	public Item createItem(Product product, Barcode barcode, Date entryDate,
			               ProductContainer container){
		return _testItem;
		
	}

	/**
	 * 
	 * @param dateString
	 * @return true is data can make a valid date
	 */
	public boolean isValidDateData(String dateString){
		return false;
	}
	
	/**
	 * 
	 * @param product
	 * @param barcode
	 * @param entryDate
	 * @param container
	 * @return a Date using the given data
	 */
	public Date createDate(Product product, Barcode barcode, 
			               Date entryDate, ProductContainer container){
		return new Date();
		
	}
	
}
