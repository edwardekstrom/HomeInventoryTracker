/**
 * 
 */
package data_structures_tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import data_structures.Barcode;
import data_structures.Date;
import data_structures.Item;
import data_structures.Product;
import data_structures.ProductContainer;
import data_structures.ProductGroup;
import data_structures.UnitSize;

/**
 * @author Capchu
 *
 */
public class ItemTest {
	Item _testItem;
	Product _testProduct;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		_testProduct = new Product(new Date(), new Barcode("5565"), "TestProduct", 5, 1);
		_testItem = new Item(_testProduct, new Barcode("55"), new Date(), new ProductGroup());
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void makeItemTest() {
		Date entryDate = new Date();
		Barcode barcode = new Barcode("88934");
		ProductGroup prodGroup = new ProductGroup();
		
		_testItem = new Item(_testProduct, barcode, entryDate, prodGroup);
		
		assertTrue(_testItem.getProduct() == _testProduct);
		assertTrue(_testItem.getBarcode() == barcode);
		assertTrue(_testItem.getContainer() == prodGroup);
		assertTrue(_testItem.getEntryDate() == entryDate);
		assertFalse(_testItem.getExpirationDate().getDate().compareTo(entryDate.getDate()) < 0 );
		assertFalse(_testItem.getExpirationDate().getDate().compareTo(entryDate.getDate()) == 0 );
		assertTrue(_testItem.getExpirationDate().getDate().compareTo(entryDate.getDate()) > 0 );
	}
	
	@Test
	public void changeProductGroupTest() {
		Date entryDate = new Date();
		Barcode barcode = new Barcode("88934");
		ProductGroup prodGroup = new ProductGroup();
		
		_testItem = new Item(_testProduct, barcode, entryDate, prodGroup);
		//make sure productGroup is right
		assertTrue(_testItem.getContainer() == prodGroup);
		
		//change ProductGroup
		ProductGroup prodGroup2 = new ProductGroup();
		assertTrue(_testItem.setContainer(prodGroup2));
		
		//Make sure change was made
		assertFalse(_testItem.getContainer() == prodGroup);
		assertTrue(_testItem.getContainer() == prodGroup2);
	}
	
	@Test
	public void changeEntryDateTest() {
		Date entryDate = new Date();
		Barcode barcode = new Barcode("88934");
		ProductGroup prodGroup = new ProductGroup();
		
		_testItem = new Item(_testProduct, barcode, entryDate, prodGroup);
		//make sure productGroup is right
		assertTrue(_testItem.getEntryDate() == entryDate);
		
		//change ProductGroup
		Date entryDate2 = new Date();
		assertTrue(_testItem.setEntryDate(entryDate2));
		
		//Make sure change was made
		assertFalse(_testItem.getEntryDate() == entryDate);
		assertTrue(_testItem.getEntryDate() == entryDate2);
	}
	
	@Test
	public void changeExitDateTest() {
		Date entryDate = new Date();
		Barcode barcode = new Barcode("88934");
		ProductGroup prodGroup = new ProductGroup();
		
		_testItem = new Item(_testProduct, barcode, entryDate, prodGroup);
		//make sure productGroup is right
		assertTrue(_testItem.getExitTime() == null);
		
		//change ProductGroup
		Date exitDate = new Date();
		assertTrue(_testItem.setExitTime(exitDate));
		
		//Make sure change was made
		assertFalse(_testItem.getExitTime() == null);
		assertTrue(_testItem.getExitTime() == exitDate);
	}

}
