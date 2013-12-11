package report_tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


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

}
