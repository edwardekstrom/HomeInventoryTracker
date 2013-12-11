import static org.junit.Assert.*;
import model.Barcode;
import model.Date;
import model.HomeInventory;
import model.Item;
import model.Product;
import model.ProductGroup;
import model.StorageUnit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.sun.source.tree.AssertTree;

import reports.ProductStatsReport;


public class ProductStatsReportTest {
	HomeInventory root = new HomeInventory();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		
		StorageUnit su = new StorageUnit();
		su.setName("SU1");
		
		ProductGroup pg = new ProductGroup("PG@");
		pg.setName("PG1");
		
		/*
		Date creationDate, Barcode barcode, String description, 
	       Integer shelfLife, Integer threeMonthSupply,String amount,
	        String unit
	        */
		Product p1 = new Product(new Date(), new Barcode("12345"), "description", 2, 10, "1", "count");
//		Product p2 = new Product(new Date(), new Barcode(), "description 22 22", 0, 0, "1", "count");
//		Product p3 = new Product(new Date(), new Barcode(), "description 33333", 0, 0, "1", "count");
		try{
		Item item1 = new Item(p1, new Barcode("2"), new Date(new java.util.Date()), pg);
		}catch(Exception e){
			e.printStackTrace();
		}
//		Item item2 = new Item(p2, new Barcode(), new Date(), pg);
//		Item item3 = new Item(p3, new Barcode(), new Date(), pg);
//		
//		
//		
//		root.addStorageUnit(su);
//		su.addProductGroup(pg);
//		
//		pg.addItem(item1);
//		pg.addItem(item2);
//		pg.addItem(item3);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		ProductStatsReport pStatsReport = new ProductStatsReport(0, root);
		assertTrue(true);
	}
	
	@Test
	public void testThreeMonthSupply(){
		
	}

}
