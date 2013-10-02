/**
 * 
 */
package data_structures_tests;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import data_structures.*;

/**
 * @author nritchie
 *
 */
public class StorageUnitTest {
	StorageUnit _unit1;
	StorageUnit _unit2;
	Item _item1;
	Product _product1;
	ProductGroup _productGroup1;
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
		_unit1 = new StorageUnit("Stable");
		_unit2 = new StorageUnit("Cage");
		
		//create product
		Date creationDate = new Date();
		Barcode barcodeProduct = new Barcode("88934");
		String description = "This is a Ho-o-o-orse";
		int shelfLife = 10;
		int threeMoSup = 2;
		
		_product1 = new Product(creationDate, barcodeProduct, description, shelfLife, threeMoSup);
		
		//create item
		Date entryDate = new Date();
		Barcode barcode = new Barcode("88934");
		ProductGroup prodGroup = new ProductGroup();
		
		_item1 = new Item(_product1, barcode, entryDate, prodGroup);

		//create productGroup
		_productGroup1 = new ProductGroup("Horses");

	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}


	@Test
	public void makeStorageUnitTest() {
		String name = "Box";
		
		_unit1 = new StorageUnit(name);
		assertTrue(_unit1.getName() == name);

	}
	
	@Test
	public void addItemTestBasic() {
		
		_unit1.addItem(_item1);
		
		assertTrue(_unit1.getItems().size() == 1);
		assertTrue(_unit1.getItems().get(0) == _item1);

		assertTrue(_unit1.getProducts().size() == 1);
		assertTrue(_unit1.getProducts().get(0) == _item1.getProduct());
		
	}
	
	@Test
	public void addItemTestMuiltiple() {
		
		_unit1.addItem(_item1);
		_unit1.addItem(_item1);
		
		assertTrue(_unit1.getItems().size() == 2);
		assertTrue(_unit1.getItems().get(0) == _item1);
		assertTrue(_unit1.getItems().get(1) == _item1);

		assertTrue(_unit1.getProducts().size() == 1);
		assertTrue(_unit1.getProducts().get(0) == _item1.getProduct());
		
	}
	
	@Test
	public void addProductGroupTest() {
		assertTrue(_unit1.getProductGroups().size() == 0);

		_unit1.addProductGroup(_productGroup1);
		
		assertTrue(_unit1.getProductGroups().size() == 1);
		assertTrue(_unit1.getProductGroups().get(0) == _productGroup1);

	}
}
