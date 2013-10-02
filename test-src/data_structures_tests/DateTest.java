/**
 * 
 */
package data_structures_tests;

import static org.junit.Assert.*;

import java.util.GregorianCalendar;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import data_structures.Date;

public class DateTest {

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
	public void constructorTest() {
		Date date1 = new Date();
		assertTrue(date1.getDate() != null);
		
	}
	
	public void constructor2Test() {

		GregorianCalendar calendar = new GregorianCalendar();
		Date date1 = new Date(calendar);
		
		assertTrue(date1.getDate() != null);
		assertTrue(date1.getDate() == calendar);

		GregorianCalendar calendar2 = new GregorianCalendar(1989, 12, 4);
		date1 = new Date(1989,12,4);
		
		assertTrue(date1.getDate() == calendar2);
		
	}	
	
	public void generateExpDateTest(){
		Date date1 = new Date();
		Date exDate = date1.generateExperationDate(5);
		
		assertTrue(exDate.getDate().compareTo(date1.getDate()) > 0);

		
		
	}

}
