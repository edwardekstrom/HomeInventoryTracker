/**
 * 
 */
package data_structures_tests;

import static org.junit.Assert.*;
import hit_exceptions.InvalidAmountException;
import hit_exceptions.InvalidUnitException;
import model.Barcode;
import model.Date;
import model.Item;
import model.Product;
import model.ProductGroup;
import model.StorageUnit;
import model.UnitSize;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author nritchie
 *
 */
public class ProductGroupTest {
	ProductGroup _group1;
	ProductGroup _group2;
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
		StorageUnit unit1 = new StorageUnit("BOX1");
		StorageUnit unit2 = new StorageUnit("BOX2");
		
		
		_group1 = new ProductGroup("Stable");
		_group2 = new ProductGroup("Cage");
		
		//add groups to units
		unit1.addProductGroup(_group1);
		unit2.addProductGroup(_group2);
		
		//create product
		Date creationDate = new Date();
		Barcode barcodeProduct = new Barcode("88934");
		String description = "This is a Ho-o-o-orse";
		int shelfLife = 10;
		int threeMoSup = 2;
		
		_product1 = new Product(creationDate, barcodeProduct, description, shelfLife, threeMoSup, "1", "count");
		
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
	public void makeProductGroupTest() {
		String name = "Box";
		
		_group1 = new ProductGroup(name);
		assertTrue(_group1.getName() == name);
		assertTrue(_group1.getItems().size() == 0);
		assertTrue(_group1.getProducts().size() == 0);
		assertTrue(_group1.getProductGroups().size() == 0);
		
		_group1 = new ProductGroup();
		assertTrue(_group1.getName() == "DEFAULT NAME");
		assertTrue(_group1.getItems().size() == 0);
		assertTrue(_group1.getProducts().size() == 0);
		assertTrue(_group1.getProductGroups().size() == 0);

	}
	
	@Test
	public void addItemTestBasic() {
		
		_group1.addItem(_item1);
		
		assertTrue(_group1.getItems().size() == 1);
		assertTrue(_group1.getItems().get(0) == _item1);

		assertTrue(_group1.getProducts().size() == 1);
		assertTrue(_group1.getProducts().get(0) == _item1.getProduct());
		
	}
	
	@Test
	public void addItemTestMuiltiple() {
		
		_group1.addItem(_item1);
		_group1.addItem(_item1);
		
		assertTrue(_group1.getItems().size() == 2);
		assertTrue(_group1.getItems().get(0) == _item1);
		assertTrue(_group1.getItems().get(1) == _item1);

		assertTrue(_group1.getProducts().size() == 1);
		assertTrue(_group1.getProducts().get(0) == _item1.getProduct());
		
	}
	
	@Test
	public void addItemTestWhenProductIsDeepInTree(){
		_group1.addProductGroup(_productGroup1);
		_productGroup1.addItem(_item1);
		
		_group1.addItem(_item1);
		
		assertTrue(_group1.getItems().size() == 0);
		assertTrue(_productGroup1.getItems().size() == 2);
	}
	
	@Test
	public void addProductGroupTest() {
		assertTrue(_group1.getProductGroups().size() == 0);

		_group1.addProductGroup(_productGroup1);
		
		assertTrue(_group1.getProductGroups().size() == 1);
		assertTrue(_group1.getProductGroups().get(0) == _productGroup1);
	}
	
	@Test
	public void removeItemTest(){
		_group1.addItem(_item1);
		
		assertTrue(_group1.getItems().size() == 1);

		_group1.removeItem(_item1);
		_group2.removeItem(_item1);
		
		assertTrue(_group1.getItems().size() == 0);
		assertTrue(_group2.getItems().size() == 0);
		
	}
	
	@Test
	public void moveItemTest(){
		_group1.addItem(_item1);
		_group1.moveItem(_item1, _group2);
		
		assertTrue(_group1.getItems().size() == 0);
		
		assertTrue(_group2.getProducts().size() == 1);

		assertTrue(_group2.getItems().size() == 1);
	}
	
	@Test
	public void moveItemTestWithMovingProduct(){
		_group1.addProductGroup(_productGroup1);
		_productGroup1.addItem(_item1);
		
		_group2.addItem(_item1);
		_group2.moveItem(_item1, _group1);
		
		assertTrue(_group1.getItems().size() == 2);
		
		assertTrue(_group2.getProducts().size() == 0);

		assertTrue(_group2.getItems().size() == 0);
	}
	
	@Test
	public void setNameTest(){
		_group1.setName("Franklin");
		
		assertTrue(_group1.getName() == "Franklin");
	}
	
	@Test
	public void setContainerTest(){
		_group1.setContainer(_group2);
		assertTrue(_group1.getContainer() == _group2);
	}
	
	@Test
	public void setThreeMonthSupTest(){
		UnitSize uSize;
		try {
			uSize = new UnitSize("1", "count");
			_group1.setThreeMonthSup(uSize);
			assertTrue(_group1.getThreeMonthSup() == uSize);
		} catch (InvalidAmountException | InvalidUnitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
