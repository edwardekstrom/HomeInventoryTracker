/**
 * 
 */
//package data_structures_tests;

import static org.junit.Assert.*;
import hit_exceptions.NullContainerException;
import hit_exceptions.NullEntryDateException;
import hit_exceptions.NullExitDateException;
import model.Barcode;
import model.Date;
import model.DateTime;
import model.Item;
import model.Product;
import model.ProductContainer;
import model.ProductGroup;
import model.UnitSize;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

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
		_testProduct = new Product(new Date(), new Barcode("5565"), "TestProduct", 5, 1, "1", "count");
		_testItem = new Item(_testProduct, new Barcode("55"), new Date(), new ProductGroup());
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

//	@Test
//	public void makeItemTest() {
//		Date entryDate = new Date();
//		Barcode barcode = new Barcode("88934");
//		ProductGroup prodGroup = new ProductGroup();
//		
//		_testItem = new Item(_testProduct, barcode, entryDate, prodGroup);
//		
//		assertTrue(_testItem.getProduct() == _testProduct);
//		assertTrue(_testItem.getBarcode() == barcode);
//		assertTrue(_testItem.getContainer() == prodGroup);
//		assertTrue(_testItem.getEntryDate() == entryDate);
//		assertFalse(_testItem.getExpirationDate().getDate().compareTo(entryDate.getDate()) < 0 );
//		assertFalse(_testItem.getExpirationDate().getDate().compareTo(entryDate.getDate()) == 0 );
//		assertTrue(_testItem.getExpirationDate().getDate().compareTo(entryDate.getDate()) > 0 );
//	}
//	
//	@Test
//	public void changeProductGroupTest() {
//		Date entryDate = new Date();
//		Barcode barcode = new Barcode("88934");
//		ProductGroup prodGroup = new ProductGroup();
//		
//		_testItem = new Item(_testProduct, barcode, entryDate, prodGroup);
//		//make sure productGroup is right
//		assertTrue(_testItem.getContainer() == prodGroup);
//		
//		//change ProductGroup
//		ProductGroup prodGroup2 = new ProductGroup();
//		try {
//			_testItem.setContainer(prodGroup2);
//		} catch (NullContainerException e) {
//			e.printStackTrace();
//		}
//		
//		//Make sure change was made
//		assertFalse(_testItem.getContainer() == prodGroup);
//		assertTrue(_testItem.getContainer() == prodGroup2);
//	}
//	
//	@Test
//	public void changeEntryDateTest() {
//		Date entryDate = new Date();
//		Barcode barcode = new Barcode("88934");
//		ProductGroup prodGroup = new ProductGroup();
//		
//		_testItem = new Item(_testProduct, barcode, entryDate, prodGroup);
//		//make sure productGroup is right
//		assertTrue(_testItem.getEntryDate() == entryDate);
//		
//		//change ProductGroup
//		Date entryDate2 = new Date();
//		try {
//			_testItem.setEntryDate(entryDate2);
//		} catch (NullEntryDateException e) {
//			e.printStackTrace();
//		}
//		
//		//Make sure change was made
//		assertFalse(_testItem.getEntryDate() == entryDate);
//		assertTrue(_testItem.getEntryDate() == entryDate2);
//	}
//	
//	@Test
//	public void changeExitDateTest() {
//		Date entryDate = new Date();
//		Barcode barcode = new Barcode("88934");
//		ProductGroup prodGroup = new ProductGroup();
//		
//		_testItem = new Item(_testProduct, barcode, entryDate, prodGroup);
//		//make sure productGroup is right
//		assertTrue(_testItem.getExitTime() == null);
//		
//		//change ProductGroup
//		DateTime exitDate = new DateTime();
//		try {
//			_testItem.setExitTime(exitDate);
//		} catch (NullExitDateException e) {
//			e.printStackTrace();
//		}
//		
//		//Make sure change was made
//		assertFalse(_testItem.getExitTime() == null);
//		assertTrue(_testItem.getExitTime() == exitDate);
//	}
//
}
