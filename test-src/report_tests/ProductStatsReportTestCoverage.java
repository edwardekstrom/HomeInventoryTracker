package report_tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import model.*;
import builder.*;

import reports.ProductStatsReport;


public class ProductStatsReportTestCoverage {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGeneralCoverage() {
		ProductStatsReport pStatsReport = new ProductStatsReport(1);
		assertTrue(pStatsReport.getNumTables() == 1);
		assertTrue(pStatsReport.getNumColumns() == 10);
		assertTrue(pStatsReport.getNotices() == null);
		assertTrue(pStatsReport.getHeader().equals("Product Report (1 Months)"));
	}

	@Test
	public void testTestConstructor(){
		try{

		HomeInventory hi = new HomeInventory();
		ProductStatsReport pStatsReport = new ProductStatsReport(1, hi);
		ReportBuilder build = new TestBuilder();
		

		StorageUnit su = new StorageUnit();
		hi.addStorageUnit(su);

		Product p1 = new Product(new Date(new java.util.Date()), new Barcode("1"), "description", 0, 0, "1", "count");
		su.addProduct(p1);
		Item item = new Item(p1,new Barcode("2"),new Date(new java.util.Date()),su );
		su.addItem(item);

		pStatsReport.generateReport(build);

		}catch(Exception e){
			assertTrue(false);
		}

	}

}
