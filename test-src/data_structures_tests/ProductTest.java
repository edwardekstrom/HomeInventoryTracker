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
import data_structures.Product;
import data_structures.UnitSize;

/**
 * @author Capchu
 *
 */
public class ProductTest {
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
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void makeProductTest() {
		Date creationDate = new Date();
		Barcode barcode = new Barcode("88934");
		String description = "This is an Ant";
		int shelfLife = 10;
		int threeMoSup = 2;
		
		_testProduct = new Product(creationDate, barcode, description, shelfLife, threeMoSup);
		
		assertTrue(_testProduct.getBarcode() == barcode);
		assertTrue(_testProduct.getCreationDate() == creationDate);
		assertTrue(_testProduct.getDescription() == description);
		assertTrue(_testProduct.getShelfLife() == 10);
		assertTrue(_testProduct.getSizeAmount() == 1);
		assertTrue(_testProduct.getSizeUnit().equals("count"));
		assertTrue(_testProduct.getThreeMonthSupply() == 2);
		
		assertFalse(_testProduct.getThreeMonthSupply() == 25);
	}
	
	@Test
	public void changeDescriptionTest() {
		Date creationDate = new Date();
		Barcode barcode = new Barcode("88934");
		String description = "This is an Ant";
		int shelfLife = 10;
		int threeMoSup = 2;
		
		_testProduct = new Product(creationDate, barcode, description, shelfLife, threeMoSup);
		
		//Change the Description
		assertTrue(_testProduct.setDescription("This is a Mech Warrior"));
		//Make sure change happened
		assertTrue(_testProduct.getDescription().compareToIgnoreCase("This is a Mech Warrior") == 0);
		
		//Change the Description
		assertTrue(_testProduct.setDescription("This is the Serenity"));
		//Make sure change happened
		assertTrue(_testProduct.getDescription().compareToIgnoreCase("This is the Serenity") == 0);
		assertFalse(_testProduct.getDescription().compareToIgnoreCase("This is a Mech Warrior") == 0);
		
	}
	
	@Test
	public void changeSizeTest() {
		Date creationDate = new Date();
		Barcode barcode = new Barcode("88934");
		String description = "This is an Ant";
		int shelfLife = 10;
		int threeMoSup = 2;
		
		_testProduct = new Product(creationDate, barcode, description, shelfLife, threeMoSup);
		
		//if unit is count, cannot change amount
		//TODO assertFalse(_testProduct.setSizeAmount("10"));
/*
		//if unit is count, cannot change amount
		assertFalse(_testProduct.setSizeUnit("bob"));
		
		//change unit to pounds
		assertTrue(_testProduct.setSizeUnit("pounds"));
		assertTrue(_testProduct.getSizeUnit().equals("pounds"));
		
		//set size can't be words
		assertFalse(_testProduct.setSizeAmount("pounds"));
		
		//set Amount to 23
		assertTrue(_testProduct.setSizeAmount("23"));
		assertTrue(_testProduct.getSizeAmount() == 23);
		*/
	}
	
	@Test
	public void changeShelfLifeTest() {
		Date creationDate = new Date();
		Barcode barcode = new Barcode("88934");
		String description = "This is an Ant";
		int shelfLife = 10;
		int threeMoSup = 2;
		
		_testProduct = new Product(creationDate, barcode, description, shelfLife, threeMoSup);
		
		//Try to change shelf life to a float
		//TODO assertFalse(_testProduct.setShelfLife("Curse your sudden but inevitable betrayal"));
		
		//Try to change shelf life to a float
		//assertFalse(_testProduct.setShelfLife("4.2"));
		assertFalse(_testProduct.getShelfLife() == 4.2);

		//Change supply to another integer
		//assertTrue(_testProduct.setShelfLife("8"));
		assertFalse(_testProduct.getShelfLife() == 10);
		assertTrue(_testProduct.getShelfLife() == 8);
		
	}
	
	@Test
	public void changeThreeMonthSupplyTest() {
		Date creationDate = new Date();
		Barcode barcode = new Barcode("88934");
		String description = "This is an Ant";
		int shelfLife = 10;
		int threeMoSup = 2;
		
		_testProduct = new Product(creationDate, barcode, description, shelfLife, threeMoSup);
		
		//Try to change shelf life to a float
		assertFalse(_testProduct.setThreeMonthSupply("Boom"));
		
		//Try to change shelf life to a float
		assertFalse(_testProduct.setThreeMonthSupply("44.5"));
		assertFalse(_testProduct.getThreeMonthSupply() == 44.5);

		//Change supply to another integer
		assertTrue(_testProduct.setThreeMonthSupply("12"));
		assertFalse(_testProduct.getThreeMonthSupply() == 10);
		assertTrue(_testProduct.getThreeMonthSupply() == 12);
		
	}

}
