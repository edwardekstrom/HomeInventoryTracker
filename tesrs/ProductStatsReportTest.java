import static org.junit.Assert.*;
import model.HomeInventory;
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

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		HomeInventory root = new HomeInventory();
		
		StorageUnit su = new StorageUnit();
		su.setName("SU1");
		
		ProductGroup pg = new ProductGroup();
		pg.setName("PG1");
		
		root.addStorageUnit(su);
		su.addProductGroup(pg);
		
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		ProductStatsReport pStatsReport = new ProductStatsReport(0);
		assertTrue(true);
	}
	
	@Test
	public void testThreeMonthSupply(){
		
	}

}
